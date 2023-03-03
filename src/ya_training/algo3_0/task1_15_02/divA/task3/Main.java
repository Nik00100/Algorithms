package ya_training.algo3_0.task1_15_02.divA.task3;

import java.io.*;
import java.util.*;

public class Main {

    static final Map<Character, Integer> PRIORITY = new HashMap<>();

    static {
        PRIORITY.put('!', 3);
        PRIORITY.put('&', 2);
        PRIORITY.put('|', 1);
        PRIORITY.put('^', 1);
    }

    public static List<Character> infixToPostfix(String infix) {
        List<Character> postfix = new ArrayList<>();
        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : infix.toCharArray()) {
            if (Character.isDigit(ch)) {
                postfix.add(ch);
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.add(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && PRIORITY.getOrDefault(stack.peek(), 0)
                        >= PRIORITY.getOrDefault(ch, 0)) {
                    postfix.add(stack.pop());
                }
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            postfix.add(stack.pop());
        }

        return postfix;
    }

    static int calculate(List<Character> postfix) {
        Stack<Integer> stack = new Stack<>();
        int n = postfix.size();

        for (int i = 0; i < n; i++) {
            if (Character.isDigit(postfix.get(i))) {
                int num = postfix.get(i) - '0';
                stack.push(num);
            } else {
                if (postfix.get(i) == '!') {
                    int argument = stack.pop() == 1 ? 0 : 1;
                    stack.push(argument);
                } else if (stack.size() >= 2) {
                    int secondArgument = stack.pop();
                    int firstArgument = stack.pop();
                    int res;
                    if (postfix.get(i) == '&') {
                        res = firstArgument & secondArgument;
                        stack.push(res);
                    } else if (postfix.get(i) == '^') {
                        res = firstArgument ^ secondArgument;
                        stack.push(res);
                    } else if (postfix.get(i) == '|') {
                        res = firstArgument | secondArgument;
                        stack.push(res);
                    }
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String infix = reader.readLine().trim();
        int n = infix.length();

        List<Character> postfix = infixToPostfix(infix);

        System.out.println(postfix);
        System.out.println(calculate(postfix));

        reader.close();
    }
}
