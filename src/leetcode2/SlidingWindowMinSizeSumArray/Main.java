package leetcode2.SlidingWindowMinSizeSumArray;

/*Given an array of positive integers nums and a positive integer target, return the minimal
length of a subarray whose sum is greater than or equal to target.
If there is no such subarray, return 0 instead.

Example 1:
Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.
Example 2:
Input: target = 4, nums = [1,4,4]
Output: 1

Algorithm

- Initialize left pointer to 0 and sum to 0
- Iterate over the nums:
        -Add nums[i] to sum
        -While sum is greater than or equal to sss:
            -Update ans=min(ans,i+1−left), where i+1−left is the size of current subarray
            -It means that the first index can safely be incremented, since, the minimum
               subarray starting with this index with sum≥s has been achieved
            -Subtract nums[left] from sum and increment left*/

public class Main {
    static int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        int left = 0;
        int sum = 0;
        for (int i=0; i<n; i++) {
            sum += nums[i];
            while (sum >= target) {
                ans = Math.min(ans, i + 1 - left);
                sum -= nums[left++];
            }
        }
        return ans==Integer.MAX_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,2,4,3};
        System.out.println(minSubArrayLen(7,nums));
    }
}
