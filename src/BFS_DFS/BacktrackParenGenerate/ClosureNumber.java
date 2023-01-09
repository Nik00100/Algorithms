package BFS_DFS.BacktrackParenGenerate;

/*Intuition
To enumerate something, generally we would like to express it as a sum of disjoint subsets
that are easier to count.

Consider the closure number of a valid parentheses sequence S: the least index >= 0 so that
S[0], S[1], ..., S[2*index+1] is valid. Clearly, every parentheses sequence has a unique closure number.
We can try to enumerate them individually.

Algorithm
For each closure number c, we know the starting and ending brackets must be at index 0 and 2*c + 1.
Then, the 2*c elements between must be a valid sequence, plus the rest of the elements must be
a valid sequence.*/

import java.util.*;

public class ClosureNumber {
    static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c)
                for (String left : generateParenthesis(c))
                    for (String right : generateParenthesis(n - 1 - c))
                        ans.add("(" + left + ")" + right);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }
}
