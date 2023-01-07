package BfsDfs.BacktrackDfsAdditiveString;

/*An additive number is a string whose digits can form an additive sequence.
A valid additive sequence should contain at least three numbers. Except for the first two numbers,
each subsequent number in the sequence must be the sum of the preceding two.
Given a string containing only digits, return true if it is an additive number or false otherwise.
Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 invalid.
Example 1:
Input: "112358"
Output: true
Explanation:
The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
Example 2:
Input: "199100199"
Output: true
Explanation:
The additive sequence is: 1, 99, 100, 199.
1 + 99 = 100, 99 + 100 = 199*/

public class Main {
    // dfs + backtracking.
    public static boolean isAdditiveNumber(String num) {
        if (num == null || num.length() < 3) return false;
        return helper(num, 0, null, null, 0);
    }

    // Return true iff. num.substring(idx, num.length()) is an additive sequence, that is,
    // there is a way to split up num.substring(idx, num.length()) into integers such
    // that each integer is the sum of the previous two.
    private static boolean helper(String num, int idx, Long first, Long second, int numNumbers) {
        if (idx == num.length()) return numNumbers >= 3;

        long currNum = 0;
        for (int i = idx; i < num.length(); ++i) {
            currNum = currNum * 10 + (num.charAt(i) - '0');
            if (first == null || second == null || currNum == first + second) {
                if (helper(num, i + 1, second, currNum, numNumbers + 1)) return true;
            }
            if (num.charAt(idx) == '0') break;   // We can't form numbers with leading zeros.
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isAdditiveNumber("199100199"));
    }
}
