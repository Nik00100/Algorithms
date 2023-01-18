package DP.DPflipStringToMonoIncreasing;

/*926. Flip String to Monotone Increasing https://leetcode.com/problems/flip-string-to-monotone-increasing/description/

A binary string is monotone increasing if it consists of some number of 0's (possibly none), followed by some number
of 1's (also possibly none). You are given a binary string s. You can flip s[i] changing it from 0 to 1 or from 1 to 0.
Return the minimum number of flips to make s monotone increasing.
Example 1:
Input: s = "00110"
Output: 1
Explanation: We flip the last digit to get 00111.
Example 2:
Input: s = "010110"
Output: 2
Explanation: We flip to get 011111, or alternatively 000111.


Let dp[i] represent the minimum number of flips to make the prefix of s of length i (substring of indices [0, i))
monotone increasing.

The base case is dp[0] = 0, since an empty string is always monotone increasing. Consider dp[i] for i > 0,

If s[i - 1] = '1', then we have dp[i] = dp[i - 1], since we can always append a character '1' to the end of a monotone
increasing string and it's still monotone increasing.

If s[i - 1] = '0', let's consider whether we flip it or not.
    If we don't flip it, we have to flip all the '1's in s before it.
    If we flip it, then we can treat it as the above case where s[i - 1] = '1' with one more flip.

In summary,
Let number num be the number of character 1s in s' prefix of length i:
    dp[i] = dp[i - 1] if s[i - 1] = '1'
    dp[i] = min(num, dp[i - 1] + 1) otherwise.
The final answer should be dp[s.length()]
Since dp[i] only depends on dp[i - 1], we can use a simple int variable instead of an array to reduce the space complexity.*/

public class Main {
    public static int minFlipsMonoIncr(String s) {
        int ans=0, num=0;
        for(int i=0; i<s.length(); i++) {
            if (s.charAt(i) == '0') ans = Math.min(num,ans+1);
            else num++;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(minFlipsMonoIncr("010110"));
    }
}
