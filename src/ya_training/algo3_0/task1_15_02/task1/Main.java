package ya_training.algo3_0.task1_15_02.task1;

/*Научитесь пользоваться стандартной структурой данных stack для целых чисел. Напишите программу, содержащую описание стека и
моделирующую работу стека, реализовав все указанные здесь методы. Программа считывает последовательность команд и в зависимости
от команды выполняет ту или иную операцию. После выполнения каждой команды программа должна вывести одну строчку.
Возможные команды для программы:

push n
Добавить в стек число n (значение n задается после команды). Программа должна вывести ok.

pop
Удалить из стека последний элемент. Программа должна вывести его значение.

back
Программа должна вывести значение последнего элемента, не удаляя его из стека.

size
Программа должна вывести количество элементов в стеке.

clear
Программа должна очистить стек и вывести ok.

exit
Программа должна вывести bye и завершить работу.

Перед исполнением операций back и pop программа должна проверять, содержится ли в стеке хотя бы один элемент.
Если во входных данных встречается операция back или pop, и при этом стек пуст, то программа должна вместо числового
значения вывести строку error.

Формат ввода
Вводятся команды управления стеком, по одной на строке

Формат вывода
Программа должна вывести протокол работы стека, по одному сообщению на строке

Пример 1
Ввод
push 1
back
exit
Вывод
ok
1
bye

Пример 2
Ввод
size
push 1
size
push 2
size
push 3
size
exit

Вывод
0
ok
1
ok
2
ok
3
bye

Пример 3
Ввод
push 3
push 14
size
clear
push 1
back
push 2
back
pop
size
pop
size
exit

Вывод
ok
ok
2
ok
ok
1
ok
2
2
1
1
0
bye
*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input8.txt")));


        Stack<Integer> stack = new Stack<>();

        while (true) {
            String[] s = reader.readLine().split(" ");
            String command = s[0];

            if (command.equals("exit")) {
                System.out.println("bye");
                break;
            }
            else if (command.equals("push")) {
                stack.push(Integer.parseInt(s[1]));
                System.out.println("ok");
            }
            else if (command.equals("pop")) {
                if (!stack.empty()) {
                    int num = stack.pop();
                    System.out.println(num);
                } else {
                    System.out.println("error");
                }
            }
            else if (command.equals("back")) {
                if (!stack.empty()) {
                    int num = stack.peek();
                    System.out.println(num);
                } else {
                    System.out.println("error");
                }
            }
            else if (command.equals("size")) {
                System.out.println(stack.size());
            }
            else if (command.equals("clear")) {
                stack.clear();
                System.out.println("ok");
            }
        }

        reader.close();
    }
}
