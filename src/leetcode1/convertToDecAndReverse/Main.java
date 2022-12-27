package leetcode1.convertToDecAndReverse;

/*Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.
For example:
A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28
...
Example 1:
Input: columnNumber = 1
Output: "A"
Example 2:
Input: columnNumber = 28
Output: "AB"
Example 3:
Input: columnNumber = 701
Output: "ZY"*/

public class Main {
    static String convertToTitle(int number) {
        return convertDecimalToNSystem(number,26);
    }

    static String convertDecimalToNSystem (int decNumber, int n) {
        StringBuilder sb = new StringBuilder();
        while (decNumber > 0) {
            decNumber--;
            int reminder = decNumber % n;
            sb.insert(0,(char)('A' +  reminder));
            decNumber /= n;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(convertToTitle(26));
    }
}
