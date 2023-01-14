package String.StringIsOneEditDistance;

/*Given two strings s and t, determine if they are both one edit distance apart.

 Note:

 There are 3 possibilities to satisfy one edit distance apart:
     Insert a character into s to get t
     Delete a character from s to get t
     Replace a character of s to get t

 Example 1:

 Input: s = "ab", t = "acb"
 Output: true
 Explanation: We can insert 'c' into s to get t.

 Example 2:

 Input: s = "cab", t = "ad"
 Output: false
 Explanation: We cannot get t from s by only one step.*/
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        final int m = s.length();
        final int n = t.length();
        if (m > n) // Make sure len(s) <= len(t)
            return isOneEditDistance(t, s);

        for (int i = 0; i < m; ++i)
            if (s.charAt(i) != t.charAt(i)) {
                if (m == n)
                    return s.substring(i + 1).equals(t.substring(i + 1)); // Replace s[i] with t[i]
                return s.substring(i).equals(t.substring(i + 1));       // Delete t[i]
            }

        return m + 1 == n; // Delete t[-1]
    }
}