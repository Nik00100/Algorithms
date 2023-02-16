package ya_training.algo3_0.task1_15_02.task2;

/*12. Правильная скобочная последовательность

Рассмотрим последовательность, состоящую из круглых, квадратных и фигурных скобок. Программа дожна определить,
является ли данная скобочная последовательность правильной. Пустая последовательность явлется правильной.
Если A – правильная, то последовательности (A), [A], {A} – правильные. Если A и B – правильные последовательности,
то последовательность AB – правильная.

Формат ввода
В единственной строке записана скобочная последовательность, содержащая не более 100000 скобок.

Формат вывода
Если данная последовательность правильная, то программа должна вывести строку yes, иначе строку no.

Пример 1
Ввод	Вывод
()[]
yes
Пример 2
Ввод	Вывод
([)]
no
Пример 3
Ввод	Вывод
(
no*/

import java.io.*;
import java.util.*;

public class Main {
    static boolean isValid(String s) {
        if (s == null || s.length() == 0) return true;
        Stack<Character> stack = new Stack<>();
        for (Character ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(')');
            } else if (ch == '[') {
                stack.push(']');
            } else if (ch == '{') {
                stack.push('}');
            } else {
                if (stack.empty() || stack.pop() != ch) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();

        if (isValid(s)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }

        reader.close();
    }
}
