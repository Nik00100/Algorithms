package Search_SlidingWindow_QuickSelect.SlidingWindowLongestRepeatingCharacterReplacement;

/*You are given a string s and an integer k. You can choose any character of the string and change it to any other
uppercase English character. You can perform this operation at most k times.
Return the length of the longest substring containing the same letter you can get after performing the above operations.
Example 1:
Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
Example 2:
Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.

Intuition:
We begin with a sliding window of size 0 positioned at the left edge of the string. We consider an empty window as valid.
Array charFreq stores a values of characters to their frequencies in the window.

Our objective is to find the longest valid window. So, whenever we see a valid window, we try to expand its size by
moving the end pointer forward. As we move the pointer forward, we update the charFreq[] as well. It (charFreq[]) helps us keep track
of the character that appears most frequently in the window. We compare the frequency of the newly added character with
the maximum frequency of any character seen - maxFreq. We update maxFreq when we find a new maximum.

The window size increases only when maxFreq finds a new maximum. For this, we always want the following condition to hold true -
            windowSize − maxFreq<=k

We stop moving the endendend pointer forward, or in other words, stop expanding the window when it becomes invalid.
Say the size of the window when it becomes invalid is lll. We know the previous window with the size l−1l - 1l−1 was valid.
So, we move the prior window of length l−1l - 1l−1 toward the right. To do so, the startstartstart pointer moves one step further.
Remember that the endendend pointer had already moved, so we don't need to move the endendend pointer again.

At this point, the last valid window has moved one step to the right, but it might still be invalid. As explained earlier,
we are only interested in larger windows, so we don't need to decrease the window size.
We move the window of size i−1i - 1i−1 further and further to the right until it becomes valid again.

If we come across a valid window, we try to expand it as much as possible, and the process continues until the endendend
pointer reaches the rightmost alphabet of the string. At this point, the size of the window indicates the longest valid
substring seen yet.


Algorithm:
*/

import java.util.ArrayList;
import java.util.List;

public class Main {
    static public int characterReplacement(String s, int k) {
        int ans = 0;
        int start = 0;
        int maxFreq = 0;
        int[] charFreq = new int[26];
        for (int end=0; end < s.length(); end++) {
            char insertedChar = s.charAt(end);
            charFreq[insertedChar - 'A']++;
            // the maximum frequency we have seen in any window yet
            maxFreq = Math.max(maxFreq, charFreq[insertedChar - 'A']);
            // move the start pointer towards right if the current
            // window is invalid
            boolean isValid = end+1 - start - maxFreq <= k;
            if (!isValid) {
                char deletedChar = s.charAt(start);
                charFreq[deletedChar - 'A']--;
                start++;
            }
            // the window is valid at this point, note down the length
            // size of the window never decreases
            ans = end+1 - start;
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(characterReplacement("AABABBA", 1));
    }
}
