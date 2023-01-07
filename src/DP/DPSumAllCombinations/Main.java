package DP.DPSumAllCombinations;

/*Given an array of distinct integers nums and a target integer target, return the number of
possible combinations that add up to target.
The test cases are generated so that the answer can fit in a 32-bit integer.
Example 1:
Input: nums = [1,2,3], target = 4
Output: 7
Explanation:
The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)
Note that different sequences are counted as different combinations.
Example 2:
Input: nums = [9], target = 3
Output: 0

*/

import java.util.Arrays;

public class Main {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++)
            for (int num : nums)
                if (num <= i) dp[i] += dp[i-num];
        return dp[target];
    }

    class Recursive {
        int total = 0;

        public int combinationSum4(int[] nums, int target) {
            total = 0;
            Arrays.sort(nums);
            backtrack(nums, target);
            return total;
        }

        private void backtrack(int[] nums, int remain) {
            if (remain == 0) {
                total++;
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > remain) {
                    break;
                }

                backtrack(nums, remain - nums[i]);
            }
        }
    }
}
