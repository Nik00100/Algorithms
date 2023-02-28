package DP.DPstickersToSpell;

/*691. Stickers to Spell Word https://leetcode.com/problems/stickers-to-spell-word/description/
We are given n different types of stickers. Each sticker has a lowercase English word on it.
You would like to spell out the given string target by cutting individual letters from your collection of stickers
and rearranging them. You can use each sticker more than once if you want, and you have infinite quantities of each sticker.
Return the minimum number of stickers that you need to spell out target. If the task is impossible, return -1.

Note: In all test cases, all words were chosen randomly from the 1000 most common US English words, and target was chosen
as a concatenation of two random words.
Example 1:
Input: stickers = ["with","example","science"], target = "thehat"
Output: 3
Explanation:
We can use 2 "with" stickers, and 1 "example" sticker.
After cutting and rearrange the letters of those stickers, we can form the target "thehat".
Also, this is the minimum number of stickers necessary to form the target string.
Example 2:
Input: stickers = ["notice","possible"], target = "basicbasic"
Output: -1
Explanation:
We cannot form the target "basicbasic" from cutting letters from the given stickers.*/

import java.util.Arrays;

class Solution {
    public int minStickers(String[] stickers, String target) {
        int n = target.length(), N = 1 << n;
        int[] dp = new int[N];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            if (dp[i] != -1) {
                for (String s : stickers) {
                    int now = i;
                    for (char c : s.toCharArray()) {
                        for (int r = 0; r < n; r++) {
                            if (target.charAt(r) == c && ((now >> r) & 1) == 0) {
                                now |= 1 << r;
                                break;
                            }
                        }
                    }
                    if (dp[now] == -1 || dp[now] > dp[i] + 1) {
                        dp[now] = dp[i] + 1;
                    }
                }
            }
        }
        return dp[N - 1];
    }

}