package leetcode.ArrayMaxSubarrayMult;

/*Maximum Product Subarray
Given an integer array nums, find a subarray that has the largest product, and return the product.
The test cases are generated so that the answer will fit in a 32-bit integer.

Example 1:
Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

Example 2:
Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.*/

public class Main {

    // общий максимум
    static int maxProduct(int[] nums) {
        int ans = Integer.MIN_VALUE;
        for (int i=0; i<nums.length; i++) {
           ans = Math.max(ans,currentMaxProduct(nums,i));
        }
        return ans;
    }

    // локальный максимум
    static int currentMaxProduct (int[] nums, int start) {
        if (nums.length==0) return 0;
        int ans = nums[0];
        int current = 1;
        for (int i=start; i<nums.length; i++) {
            current *= nums[i];
            ans = Math.max(ans,current);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,-2,4};
        int[] nums1 = {-2};
        System.out.println(maxProduct(nums));
        System.out.println(maxProduct(nums1));
    }
}
