package BFS_DFS.BacktrackOperatorsPlusMinusMult;

/*Given a string num that contains only digits and an integer target, return all possibilities
to insert the binary operators '+', '-', and/or '*' between the digits of num so that the resultant
expression evaluates to the target value.
Note that operands in the returned expressions should not contain leading zeros.

Example 1:
Input: num = "123", target = 6
Output: ["1*2*3","1+2+3"]
Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.
Example 2:
Input: num = "232", target = 8
Output: ["2*3+2","2+3*2"]
Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.
Example 3:
Input: num = "3456237490", target = 9191
Output: []
Explanation: There are no expressions that can be created from "3456237490" to evaluate to 9191.

    Algorithm:
    - We use backtracking to generate all possible expressions by adding +, -, * to between
      the digits of s string.
    - There is no priority since there are no parentheses ( and ) in our s string,
      so we can evaluate the expression on the fly to save time.
    - There are total 3 operators:
        + operator: newResult = resSoFar + num
        - operator: newResult = resSoFar - num.
        * operator: We need to keep the prevNum so that to calculate newResult we need
        to minus prevNum then plus with prevNum * num.
        So newResult = resSoFar - prevNum + prevNum * num.*/

public class Main {
    public static void main(String[] args) {
        System.out.println(new Backtrack().addOperators("1234481216",16));
    }
}
