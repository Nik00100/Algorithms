package Search_SlidingWindow_QuickSelect.SlidingWindowLongestSubstring;

/*Given a string s and an integer k, return the length of the longest substring of s such that the frequency
of each character in this substring is greater than or equal to k.
Example 1:
Input: s = "aaabb", k = 3
Output: 3
Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:
Input: s = "ababbc", k = 2
Output: 5
Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.

Algorithm

    - Find the number of unique characters in the string s and store the count in variable maxUnique.
        For s = aabcbacad, the unique characters are a,b,c,d and maxUnique = 4.

    - Iterate over the string s with the value of currUnique ranging from 1 to maxUnique.
        In each iteration, currUnique is the maximum number of unique characters that must be present in the sliding window.

    - The sliding window starts at index windowStart and ends at index windowEnd and slides over string s until
        windowEnd reaches the end of string s. At any given point, we shrink or expand the window to ensure that the
        number of unique characters is not greater than currUnique.

    - If the number of unique character in the sliding window is less than or equal to currUnique, expand the window
        from the right by adding a character to the end of the window given by windowEnd

    - Otherwise, shrink the window from the left by removing a character from the start of the window given by windowStart.

    - Keep track of the number of unique characters in the current sliding window having at least k frequency given
    by countAtLeastK. Update the result if all the characters in the window have at least k frequency.
*/

import java.util.Arrays;

public class Main {
    public static class Solution {
        public int longestSubstring(String s, int k) {
            char[] str = s.toCharArray();
            int[] countMap = new int[26];
            int maxUnique = getMaxUniqueLetters(s);
            int result = 0;
            for (int currUnique = 1; currUnique <= maxUnique; currUnique++) {
                // reset countMap
                Arrays.fill(countMap, 0);
                int windowStart = 0, windowEnd = 0, idx = 0, unique = 0, countAtLeastK = 0;
                while (windowEnd < str.length) {
                    // expand the sliding window
                    if (unique <= currUnique) {
                        idx = str[windowEnd] - 'a';
                        if (countMap[idx] == 0) unique++;
                        countMap[idx]++;
                        if (countMap[idx] == k) countAtLeastK++;
                        windowEnd++;
                    }
                    // shrink the sliding window
                    else {
                        idx = str[windowStart] - 'a';
                        if (countMap[idx] == k) countAtLeastK--;
                        countMap[idx]--;
                        if (countMap[idx] == 0) unique--;
                        windowStart++;
                    }
                    if (unique == currUnique && unique == countAtLeastK)
                        result = Math.max(windowEnd - windowStart, result);
                }
            }

            return result;
        }

        // get the maximum number of unique letters in the string s
        int getMaxUniqueLetters(String s) {
            boolean map[] = new boolean[26];
            int maxUnique = 0;
            for (int i = 0; i < s.length(); i++) {
                if (!map[s.charAt(i) - 'a']) {
                    maxUnique++;
                    map[s.charAt(i) - 'a'] = true;
                }
            }
            return maxUnique;
        }
    }

}
