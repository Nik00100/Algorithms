package ya_training.algo3_0.task1_15_02.divA.task3;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String expr = sc.nextLine();
        boolean result = eval(expr);
        System.out.println(result ? "1" : "0");
    }

    private static boolean eval(String expr) {
        Stack<Boolean> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);
            switch (ch) {
                case '0':
                case '1':
                    boolean operand = ch == '1';
                    if (!operators.isEmpty() && operators.peek() == '!') {
                        operators.pop();
                        operand = !operand;
                    }
                    operands.push(operand);
                    break;
                case '!':
                    operators.push('!');
                    break;
                case '&':
                case '|':
                case '^':
                    operators.push(ch);
                    break;
                case '(':
                    int j = findClosingBracket(expr, i);
                    boolean subExpr = eval(expr.substring(i+1, j));
                    if (!operators.isEmpty() && operators.peek() == '!') {
                        operators.pop();
                        subExpr = !subExpr;
                    }
                    operands.push(subExpr);
                    i = j;
                    break;
                default:
                    break;
            }
            while (!operators.isEmpty() && !operands.isEmpty() && precedence(operators.peek()) >= precedence(ch)) {
                char op = operators.pop();
                boolean op2 = operands.pop();
                boolean op1 = operands.pop();
                operands.push(evaluate(op1, op2, op));
            }
        }

        while (!operators.isEmpty() && !operands.isEmpty()) {
            char op = operators.pop();
            boolean op2 = operands.pop();
            boolean op1 = operands.pop();
            operands.push(evaluate(op1, op2, op));
        }

        return operands.pop();
    }

    private static int precedence(char op) {
        switch (op) {
            case '!':
                return 3;
            case '&':
                return 2;
            case '|':
            case '^':
                return 1;
            default:
                return 0;
        }
    }

    private static boolean evaluate(boolean op1, boolean op2, char op) {
        switch (op) {
            case '&':
                return op1 && op2;
            case '|':
                return op1 || op2;
            case '^':
                return op1 ^ op2;
            default:
                return false;
        }
    }

    private static int findClosingBracket(String expr, int i) {
        int level = 1;
        while (level > 0 && i < expr.length()-1) {
            i++;
            char ch = expr.charAt(i);
            if (ch == '(') {
                level++;
            } else if (ch == ')') {
                level--;
            }
        }
        return i;
    }
}
