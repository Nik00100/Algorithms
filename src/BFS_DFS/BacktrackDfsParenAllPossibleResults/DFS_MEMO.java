package BFS_DFS.BacktrackDfsParenAllPossibleResults;

/*Idea:

    - Base case: If the expression has no symbol, i.e. its a number, then there's nothing to do.
        Just add to result.
    - Recursive case: Split the expression at every symbol and evaluate the parts recursively.
        - Why split at every symbol? Because its analogous to adding parantheses. e.g. 1 + 2 * 3
            - when split at +, parts are 1 and 2 * 3 which can be written as 1 + (2 * 3)
            - when split at *, parts are 1 + 2 and 3 which can be written as (1 + 2) * 3
    - Its possible to be repeat computing an expression, hence inject memoization into brute force DFS.
    - T/S: O(n2ⁿ)/O(2ⁿ) (for interviews).*/

import java.util.*;

public class DFS_MEMO {
    public List<Integer> diffWaysToCompute(String expression) {
        return diffWaysToCompute(expression, new HashMap<>());
    }

    private List<Integer> diffWaysToCompute(String expression, Map<String, List<Integer>> memo) {
        if (memo.containsKey(expression))
            return memo.get(expression);
        var values = new ArrayList<Integer>();

        if (!hasOperator(expression))
            values.add(Integer.parseInt(expression));
        else
            for (var i = 0; i < expression.length(); i++) {
                var symbol = expression.charAt(i);
                if (!Character.isDigit(symbol)) {
                    var leftParts = diffWaysToCompute(expression.substring(0, i), memo);
                    var rightParts = diffWaysToCompute(expression.substring(i + 1), memo);

                    for (var l : leftParts)
                        for (var r : rightParts)
                            switch (symbol) {
                                case '+' -> values.add(l + r);
                                case '-' -> values.add(l - r);
                                case '*' -> values.add(l * r);
                            }
                }
            }

        return memo.compute(expression, (k,v) -> values);
    }

    private boolean hasOperator(String expression) {
        for (var i = 0; i < expression.length(); i++)
            switch (expression.charAt(i)) {
                case '+', '-', '*' -> {
                    return true;
                }
            }
        return false;
    }
}
