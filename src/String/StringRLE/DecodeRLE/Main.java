package String.StringRLE.DecodeRLE;

/*Given an encoded string, return its decoded string.
The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
Note that k is guaranteed to be a positive integer.
You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc.
Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
For example, there will not be input like 3a or 2[4].
The test cases are generated so that the length of the output will never exceed 105.

Example 1:
Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:
Input: s = "3[a2[c]]"
Output: "accaccacc"*/

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static class Solution {
        public String decodeString(String s) {
            Queue<Character> queue = new LinkedList<>();
            for (char ch : s.toCharArray()) {
                queue.add(ch);
            }
            return helper(queue);
        }

        private String helper(Queue<Character> queue) {
            int count = 0;
            StringBuilder sb = new StringBuilder();
            while (!queue.isEmpty()) {
                char ch = queue.poll();
                if (Character.isDigit(ch)) {
                    count = count*10 + ch - '0';
                } else if (ch == '[') {
                    String substr = helper(queue);
                    for (int i = 0; i < count; i++) sb.append(substr);
                    count = 0;
                } else if (ch == ']') {
                    break;
                } else {
                    sb.append(ch);
                }
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().decodeString("3[a2[c]]"));
    }
}
