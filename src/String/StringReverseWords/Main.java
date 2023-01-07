package String.StringReverseWords;

/*Given an input string s, reverse the order of the words.
A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
Return a string of the words in reverse order concatenated by a single space.
Note that s may contain leading or trailing spaces or multiple spaces between two words.
The returned string should only have a single space separating the words. Do not include any extra spaces.

Example 1:
Input: s = " the sky is blue "
Output: "blue is sky the"*/

public class Main {
    static String reverseWords(String s) {
        String[] split = s.trim().split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i=split.length-1; i>0; i--) {
            if (!split[i].replaceAll(" ","").equals("")) {
                sb.append(split[i].trim()).append(" ");
            }
        }
        sb.append(split[0]);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("["+"  hello     world  "+"]");
        System.out.println("["+reverseWords("  hello     world  ")+"]");
    }
}
