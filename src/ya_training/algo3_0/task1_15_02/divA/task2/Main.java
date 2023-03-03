package ya_training.algo3_0.task1_15_02.divA.task2;

import java.util.*;
import java.io.*;

public class Main {

    static final Map<Character, Integer> PRIORITY = new HashMap<>();

    static {
        PRIORITY.put('*', 2);
        PRIORITY.put('+', 1);
        PRIORITY.put('-', 1);
    }

    static boolean checkValidParenthesis(String s) {
        int balance = 0;
        for (char c: s.toCharArray()) {
            balance += c == '(' ? 1 : 0;
            balance += c == ')' ? -1 : 0;
            if (balance < 0) break;
        }
        return balance == 0 ;
    }

    static boolean isValidArithmeticExpression(String s) {
        String expr = s.replaceAll(" ","");
        Stack<Character> stack = new Stack<>();
        boolean valid = true;
        boolean expectOperand = true;

        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);

            if (Character.isDigit(ch) ) {
                int sum = expr.charAt(i) - '0';
                while (i + 1 < expr.length() && Character.isDigit(s.charAt(i + 1))) {
                    sum = sum * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                if (!expectOperand) {
                    valid = false;
                    break;
                }
                expectOperand = false;
            } else if (ch == '+' || ch == '-' || ch == '*' ) {
                if (expectOperand) {
                    valid = false;
                    break;
                }
                expectOperand = true;
            } else if (ch == '(') {
                if (!expectOperand) {
                    valid = false;
                    break;
                }
                stack.push(ch);
                expectOperand = true;
            } else if (ch == ')') {
                if (stack.isEmpty() || stack.peek() != '(' || expectOperand) {
                    valid = false;
                    break;
                }
                stack.pop();
                expectOperand = false;
            } else {
                valid = false;
                break;
            }
        }

        return valid && stack.isEmpty() && !expectOperand;
    }

    static List<String> infixToPostfix(String infix) {
        List<String> postfix = new ArrayList<>();
        Deque<Character> stack = new ArrayDeque<>();

        if (!checkValidParenthesis(infix) || !isValidArithmeticExpression(infix)) return null;
        String s = infix.replaceAll(" ", "");
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                int sum = s.charAt(i) - '0';
                while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) {
                    sum = sum * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                postfix.add(String.valueOf(sum));
            } else if (s.charAt(i) == '(') {
                stack.push(s.charAt(i));
            } else if (s.charAt(i) == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.add(String.valueOf(stack.pop()));
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && PRIORITY.getOrDefault(stack.peek(), 0)
                        >= PRIORITY.getOrDefault(s.charAt(i), 0)) {
                    postfix.add(String.valueOf(stack.pop()));
                }
                stack.push(s.charAt(i));
            }
        }

        while (!stack.isEmpty()) {
            postfix.add(String.valueOf(stack.pop()));
        }
        return postfix;
    }

    static int calculate(List<String> postfix) {
        Stack<Integer> stack = new Stack<>();
        int n = postfix.size();

        for (int i = 0; i < n; i++) {
            String s = postfix.get(i);
            if (!s.equals("*") && !s.equals("+") && !s.equals("-")) {
                int num = Integer.parseInt(s);
                stack.push(num);
            } else if (stack.size() >= 2) {
                int secondArgument = stack.pop();
                int firstArgument = stack.pop();
                int res;
                if (s.equals("*")) {
                    res = firstArgument * secondArgument;
                    stack.push(res);
                } else if (s.equals("+")) {
                    res = firstArgument + secondArgument;
                    stack.push(res);
                } else if (s.equals("-")) {
                    res = firstArgument - secondArgument;
                    stack.push(res);
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String infix = reader.readLine().trim();

        List<String> postfix = infixToPostfix(infix);

        if (postfix != null)
            System.out.println(calculate(postfix));
        else
            System.out.println("WRONG");

        reader.close();
    }

}
