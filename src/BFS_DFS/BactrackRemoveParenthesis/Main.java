package BFS_DFS.BactrackRemoveParenthesis;

/*Given a string s that contains parentheses and letters, remove the minimum number of invalid
parentheses to make the input string valid. Return all the possible results.
Example 1:
Input: s = "()())()"
Output: ["(())()","()()()"]
Example 2:
Input: s = "(a)())()"
Output: ["(a())()","(a)()()"]
Example 3:
Input: s = ")("
Output: [""]

* The state of the recursion is defined by five different variables:
    - index which represents the current character that we have to process in the original string.
    - left_count which represents the number of left parentheses that have been added to the
        expression we are building.
    - right_count which represents the number of right parentheses that have been added to the
        expression we are building.
    - left_rem is the number of left parentheses that remain to be removed.
    - right_rem represents the number of right parentheses that remain to be removed. Overall,
        for the final expression to be valid, left_rem == 0 and right_rem == 0.
* When we decide to not consider a parenthesis i.e. delete a parenthesis, be it a left or a right
    parentheses, we have to consider their corresponding remaining counts as well.
    This means that we can only discard a left parentheses if left_rem > 0 and similarly for the
    right one we will check for right_rem > 0.
* Condition for an expression being valid in the base case left_rem == 0 and right_rem == 0.
    We don't have to check if left_count == right_count anymore, because we would have removed
    all the misplaced or invalid parenthesis by the time the recursion ends.
    So, the only check we need if left_rem == 0 and right_rem == 0.*/

import java.util.*;

public class Main {
    private Set<String> validExpressions = new HashSet<String>();

    private void recurse(
            String s,
            int index,
            int leftCount,
            int rightCount,
            int leftRem,
            int rightRem,
            StringBuilder expression) {

        // If we reached the end of the string, just check if the resulting expression is
        // valid or not and also if we have removed the total number of left and right
        // parentheses that we should have removed.
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0) {
                this.validExpressions.add(expression.toString());
            }

        } else {
            char character = s.charAt(index);
            int length = expression.length();

            // The discard case. Note that here we have our pruning condition.
            // We don't recurse if the remaining count for that parenthesis is == 0.
            if ((character == '(' && leftRem > 0) || (character == ')' && rightRem > 0)) {
                this.recurse(
                        s,
                        index + 1,
                        leftCount,
                        rightCount,
                        leftRem - (character == '(' ? 1 : 0),
                        rightRem - (character == ')' ? 1 : 0),
                        expression);
            }

            expression.append(character);

            // Simply recurse one step further if the current character is not a parenthesis.
            if (character != '(' && character != ')') {

                this.recurse(s, index + 1, leftCount, rightCount, leftRem, rightRem, expression);

            } else if (character == '(') {

                // Consider an opening bracket.
                this.recurse(s, index + 1, leftCount + 1, rightCount, leftRem, rightRem, expression);

            } else if (rightCount < leftCount) {

                // Consider a closing bracket.
                this.recurse(s, index + 1, leftCount, rightCount + 1, leftRem, rightRem, expression);
            }

            // Delete for backtracking.
            expression.deleteCharAt(length);
        }
    }

    public List<String> removeInvalidParentheses(String s) {

        int left = 0, right = 0;

        // First, we find out the number of misplaced left and right parentheses.
        for (int i = 0; i < s.length(); i++) {

            // Simply record the left one.
            if (s.charAt(i) == '(') {
                left++;
            } else if (s.charAt(i) == ')') {
                // If we don't have a matching left, then this is a misplaced right, record it.
                right = left == 0 ? right + 1 : right;

                // Decrement count of left parentheses because we have found a right
                // which CAN be a matching one for a left.
                left = left > 0 ? left - 1 : left;
            }
        }

        this.recurse(s, 0, 0, 0, left, right, new StringBuilder());
        return new ArrayList<String>(this.validExpressions);
    }

}
