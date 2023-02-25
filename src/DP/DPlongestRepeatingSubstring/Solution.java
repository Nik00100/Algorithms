package DP.DPlongestRepeatingSubstring;

/*1062. Longest Repeating Substring
Description
Given a string S, find out the length of the longest repeating substring(s). Return 0 if no repeating substring exists.
Example 1:
Input: “abcd” Output: 0
Explanation: There is no repeating substring.
Example 2:
Input: “abbaba” Output: 2 Explanation: The longest repeating substrings are “ab” and “ba”, each of which occurs twice.
Example 3:
Input: “aabcaabdaab” Output: 3 Explanation: The longest repeating substring is “aab”, which occurs 3 times.
Example 4:
Input: “aaaaa” Output: 4 Explanation: The longest repeating substring is “aaaa”, which occurs twice.
Note:
The string S consists of only lowercase English letters from 'a' - 'z'.
1 <= S.length <= 1500*/

class Solution {

    static class TrieNode {
        TrieNode[] next;

        public TrieNode() {
            next = new TrieNode[26];
        }
    }

    static int longestRepeatingSubstring(String S) {
        int maxLength = 0;
        TrieNode root = new TrieNode();
        int length = S.length();
        for (int i = 0; i < length; i++) {
            TrieNode node = root;
            for (int j = i; j < length; j++) {
                int index = S.charAt(j) - 'a';
                if (node.next[index] != null)
                    maxLength = Math.max(maxLength, j - i + 1);
                else
                    node.next[index] = new TrieNode();
                node = node.next[index];
            }
        }
        return maxLength;
    }
}