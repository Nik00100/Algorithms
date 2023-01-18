package Search_SlidingWindow_QuickSelect_TwoPointer.SlidingWindowUniqueSubstrings;

/*467. Unique Substrings in Wraparound String

We define the string base to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", so base will look like this:
"...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
Given a string s, return the number of unique non-empty substrings of s are present in base.
Example 1:
Input: s = "a"
Output: 1
Explanation: Only the substring "a" of s is in base.
Example 2:
Input: s = "cac"
Output: 2
Explanation: There are two substrings ("a", "c") of s in base.
Example 3:

Input: s = "zab"
Output: 6
Explanation: There are six substrings ("z", "a", "b", "za", "ab", and "zab") of s in base.

Approach
use a sliding window approach to check for the consecutive sequence of letters in the given string
keep a counter variable to keep track of the number of times the substring appears in the string

Complexity
Time complexity: O(n)*/

import java.util.Arrays;

public class Main {
    int findSubstringInWraproundString(String p) {
        int[] count = new int[26];
        int len = 0;
        for (int i = 0; i < p.length(); ++i) {
            if (i > 0 && (p.charAt(i) == p.charAt(i-1) + 1 || p.charAt(i-1) - p.charAt(i) == 25)) ++len;
            else len = 1;
            count[p.charAt(i) - 'a'] = Math.max(count[p.charAt(i) - 'a'], len);
        }
        return Arrays.stream(count).sum();
    }
}
