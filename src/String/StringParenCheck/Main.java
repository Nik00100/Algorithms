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
считаем открытые скобки, если символ ')', то увеличиваем счетчик, если '(', то уменьшаем если в процессе подсчета
счетчик меньше нуля, то последовательность неверная. Если после полного прохода счетчик не равен нулю,
то последовательность тоже неверная*/

import java.util.*;

public class Main {
    public boolean checkValidString(String s) {
        int balance = 0;
        for (char c: s.toCharArray()) {
            balance += c == '(' ? 1 : 0;
            balance += c == ')' ? -1 : 0;
            if (balance < 0) break;
        }
        return balance == 0 ;
    }
}
