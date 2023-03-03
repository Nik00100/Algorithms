package ya_training.algo3_0.task1_15_02.task3;

/*В постфиксной записи (или обратной польской записи) операция записывается после двух операндов. Например, сумма
двух чисел A и B записывается как A B +. Запись B C + D * обозначает привычное нам (B + C) * D, а запись A B C + D * +
означает A + (B + C) * D. Достоинство постфиксной записи в том, что она не требует скобок и дополнительных соглашений о
приоритете операторов для своего чтения.

Формат ввода
В единственной строке записано выражение в постфиксной записи, содержащее цифры и операции +, -, *.
Цифры и операции разделяются пробелами. В конце строки может быть произвольное количество пробелов.

Формат вывода
Необходимо вывести значение записанного выражения.

Пример
Ввод
8 9 + 1 7 - *

Вывод
-102*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input8.txt")));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String s = reader.readLine().trim();
        int n = s.length();

        Stack<Integer> stack = new Stack<>();
        //int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            if (Character.isDigit(s.charAt(i))) {
                int num = s.charAt(i) - '0';
                stack.push(num);
            } else {
                if (s.charAt(i) == ' ') continue;
                if (stack.size() >= 2) {
                    int secondArgument = stack.pop();
                    int firstArgument = stack.pop();
                    int res;
                    if (s.charAt(i) == '+') {
                        res = firstArgument + secondArgument;
                        stack.push(res);
                    } else if (s.charAt(i) == '-') {
                        res = firstArgument - secondArgument;
                        stack.push(res);
                    } else if (s.charAt(i) == '*') {
                        res = firstArgument * secondArgument;
                        stack.push(res);
                    }
                }
            }
        }

        System.out.println(stack.pop());

        reader.close();
    }
}
