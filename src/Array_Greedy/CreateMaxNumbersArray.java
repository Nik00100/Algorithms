package Array_Greedy;

/*You are given two integer arrays nums1 and nums2 of lengths m and n respectively. nums1 and nums2 represent
the digits of two numbers. You are also given an integer k.
Create the maximum number of length k <= m + n from digits of the two numbers. The relative order of the digits
from the same array must be preserved.
Return an array of the k digits representing the answer.

Example 1:
Input: nums1 = [3,4,6,5], nums2 = [9,1,2,5,8,3], k = 5
Output: [9,8,6,5,3]
Example 2:
Input: nums1 = [6,7], nums2 = [6,0,4], k = 5
Output: [6,7,6,0,4]

*/

import java.util.Arrays;
import java.util.Stack;

public class CreateMaxNumbersArray {
    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int m = nums2.length;
        int[] ans = new int[k];
        for (int i = Math.max(0, k - m); i <= k && i <= n; ++i) {
            int[] candidate = merge(maxArray(nums1, i), maxArray(nums2, k - i), k);
            if (greater(candidate, 0, ans, 0)) ans = candidate;
        }
        return ans;
    }
    private static int[] merge(int[] nums1, int[] nums2, int k) {
        int[] ans = new int[k];
        for (int i = 0, j = 0, r = 0; r < k; ++r)
            ans[r] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
        return ans;
    }
    public static boolean greater(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;
            j++;
        }
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }

    // This function is the code of "Find most consecutive subsequence" problem, which finds the len digit maximum number possible
    private static int[] maxArray(int[] nums, int len) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < nums.length; i++) {
            while (stack.size() + nums.length - i > len && !stack.empty() && stack.peek() < nums[i]) {
                stack.pop();
            }
            if (stack.size() < len) {
                stack.push(nums[i]);
            }
        }
        int[] result = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {3,4,6,5}, nums2 = {9,1,2,5,8,3};
        System.out.println(Arrays.toString(maxNumber(nums1,nums2,5)));
    }
}
