package DP.DProbbery;

/*You are a professional robber planning to rob houses along a street. Each house has a certain
amount of money stashed. All houses at this place are arranged in a circle. That means the first
house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected,
and it will automatically contact the police if two adjacent houses were broken into on the same night.
Given an integer array nums representing the amount of money of each house, return the maximum amount
of money you can rob tonight without alerting the police.

Example 1:
Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
Example 2:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Since House[1] and House[n] are adjacent, they cannot be robbed together. Therefore, the problem
becomes to rob either House[1]-House[n-1] or House[2]-House[n], depending on which choice offers
more money. Now the problem has degenerated to the House Robber, which is already been solved.*/

public class Main {
    public static int rob(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;
        if (n == 1)
            return nums[0];
        return Math.max(robInLine(nums, 0, n - 2), robInLine(nums, 1, n-1));
    }

    public static int robInLine(int[] nums, int start, int end) {
        int n = end - start + 1;
        if(n<=0) return 0;
        if(n==1) return nums[start];
        int[] dp = new int[n];
        dp[0] = nums[start];
        dp[1] = Math.max(dp[0], nums[start + 1]);
        int res = 0;
        for (int i = 2; i < n; i++) {
            int num = nums[i+start];
            dp[i] = Math.max(num + dp[i - 2], dp[i - 1]);
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        int[] nums ={1,2,3,1};
        System.out.println(rob(nums));
    }
}
