package String.StringParenCheck;

/*678. Valid Parenthesis String https://leetcode.com/problems/valid-parenthesis-string/description/

Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
The following rules define a valid string:
Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
Example 1:
Input: s = "()"
Output: true
Example 2:
Input: s = "(*)"
Output: true
Example 3:
Input: s = "(*))"
Output: true
*
* Let lo, hi respectively be the smallest and largest possible number of open left brackets after processing the current
* character in the string. If we encounter a left bracket (c == '('), then lo++, otherwise we could write a right bracket,
* so lo--. If we encounter what can be a left bracket (c != ')'), then hi++, otherwise we must write a right bracket,
* so hi--. If hi < 0, then the current prefix can't be made valid no matter what our choices are. Also, we can never have
* less than 0 open left brackets. At the end, we should check that we can have exactly 0 open left brackets.*/

import java.util.*;

public class Main {
    public boolean checkValidString(String s) {
        int lo = 0, hi = 0;
        for (char c: s.toCharArray()) {
            lo += c == '(' ? 1 : -1;
            hi += c != ')' ? 1 : -1;
            if (hi < 0) break;
            lo = Math.max(lo, 0);
        }
        return lo == 0;
    }


}
