package leetcode3.StringConvertToDecAndReverse;

/*Given a string columnTitle that represents the column title as appears in an Excel sheet,
return its corresponding column number. For example:
A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28
...
Example 1:
Input: columnTitle = "A"
Output: 1
Example 2:
Input: columnTitle = "AB"
Output: 28
Example 3:
Input: columnTitle = "ZY"
Output: 701*/

public class Reverse {
    static int titleToNumber(String columnTitle) {
        int n = columnTitle.length();
        int res = 0;
        for(int i=0; i<n; i++) {
            // берем символы в обратном порядке
            int a = 1 + columnTitle.charAt(n-i-1) - 'A';
            int pow = (int) Math.pow(26,i);
            res += a*pow;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(titleToNumber("Z"));
    }
}
