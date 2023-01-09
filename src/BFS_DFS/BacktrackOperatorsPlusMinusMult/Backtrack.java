package BFS_DFS.BacktrackOperatorsPlusMinusMult;

/*Algorithm:
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

import java.util.*;

public class Backtrack {
    List<String> ans = new ArrayList<>();
    String s;
    int target;
    public List<String> addOperators(String s, int target) {
        this.s = s;
        this.target = target;
        backtrack( 0, "", 0, 0);
        return ans;
    }
    void backtrack(int i, String path, long resSoFar, long prevNum) {
        if (i == s.length()) {
            if (resSoFar == target) ans.add(path);
            return;
        }
        for (int j = i; j < s.length(); j++) {
            // Skip leading zero number
            if (j > i && s.charAt(i) == '0') break;
            long num = Long.parseLong(s.substring(i, j + 1));
            if (i == 0) {
                // First num, pick it without adding any operator!
                backtrack(j + 1, path + num, num, num);
            } else {
                backtrack(j + 1, path + "+" + num, resSoFar + num, num);
                backtrack(j + 1, path + "-" + num, resSoFar - num, -num);
                // Can imagine with example: 1-2*3
                backtrack(j + 1, path + "*" + num, resSoFar - prevNum + prevNum * num, prevNum * num);
            }
        }
    }
}
