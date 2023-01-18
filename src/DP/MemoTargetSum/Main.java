package DP.MemoTargetSum;

/*494. Target Sum

You are given an integer array nums and an integer target.
You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums
and then concatenate all the integers. For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1
and concatenate them to build the expression "+2-1". Return the number of different expressions that you can build,
which evaluates to target.

Example 1:
Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3

For every call to calculate(nums, i, sum, S), we store the result obtained in memo[i][sum+total],
where total stands for the sum of all the elements from the input array.
The factor of total has been added as an offset to the sum value to map all the sum possible to positive integer range.
By making use of memoization, we can get the result of each redundant function call in constant time.*/

import java.util.Arrays;

public class Main {
    public class Solution {
        int total;

        public int findTargetSumWays(int[] nums, int S) {
            total = Arrays.stream(nums).sum();

            int[][] memo = new int[nums.length][2 * total + 1];
            for (int[] row : memo) {
                Arrays.fill(row, Integer.MIN_VALUE);
            }
            return calculate(nums, 0, 0, S, memo);
        }

        public int calculate(int[] nums, int i, int sum, int S, int[][] memo) {
            if (i == nums.length) {
                if (sum == S) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                if (memo[i][sum + total] != Integer.MIN_VALUE) {
                    return memo[i][sum + total];
                }
                int add = calculate(nums, i + 1, sum + nums[i], S, memo);
                int subtract = calculate(nums, i + 1, sum - nums[i], S, memo);
                memo[i][sum + total] = add + subtract;
                return memo[i][sum + total];
            }
        }
    }



    public static void main(String[] args) {

    }
}
