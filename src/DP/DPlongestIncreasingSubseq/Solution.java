package DP.DPlongestIncreasingSubseq;

/*Given an integer array nums, return the length of the longest strictly increasing subsequence
Example 1:
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Let's see example nums = [2, 6, 8, 3, 4, 5, 1]:
    Let pick the first element, sub = [2].
    6 is greater than previous number, sub = [2, 6]
    8 is greater than previous number, sub = [2, 6, 8]
    3 is less than previous number, so we can't extend the subsequence sub.
        We need to find the smallest number >= 3 in sub, it's 6. Then we overwrite it, now sub = [2, 3, 8].
    4 is less than previous number, so we can't extend the subsequence sub. We overwrite 8 by 4, so sub = [2, 3, 4].
    5 is greater than previous number, sub = [2, 3, 4, 5].
    1 is less than previous number, so we can't extend the subsequence sub. We overwrite 2 by 1, so sub = [1, 3, 4, 5].
Finally, length of longest increase subsequence = len(sub) = 4.

(1) if x is larger than all subs, append it
(2) if subs[i-1] < x <= subs[i], update subs[i]
*/

import java.util.Arrays;
import java.util.List;

public class Solution {
    // based on binary search dp variant
    int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int size = 0;

        for(int num: nums) {
            // binary search
            int left = 0, right = size, middle = 0;     // right = size
            while(left < right) {
                middle = left + (right - left) / 2;
                if(dp[middle] < num) left = middle + 1;
                else right = middle;
            }

            // left is the right position to 'replace' in dp array
            dp[left] = num;
            if(left == size) size++;
        }
        return size;
    }

    // classic dp
    static int lengthOfLIS_varII(int[] nums) {
        int ans = 1, n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for(int i = 0; i < n; i++)
            for(int j = 0; j < i; j++)
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    ans = Math.max(ans, dp[i]);
                }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS_varII(nums));
    }
}
