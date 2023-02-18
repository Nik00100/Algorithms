package ya_training.algo3_0.task2_17_02.task1;

/*Научитесь пользоваться стандартной структурой данных queue для целых чисел. Напишите программу, содержащую описание очереди и моделирующую работу очереди, реализовав все указанные здесь методы.

Программа считывает последовательность команд и в зависимости от команды выполняет ту или иную операцию. После выполнения каждой команды программа должна вывести одну строчку.

Возможные команды для программы:

push n
Добавить в очередь число n (значение n задается после команды). Программа должна вывести ok.

pop
Удалить из очереди первый элемент. Программа должна вывести его значение.

front
Программа должна вывести значение первого элемента, не удаляя его из очереди.

size
Программа должна вывести количество элементов в очереди.

clear
Программа должна очистить очередь и вывести ok.

exit
Программа должна вывести bye и завершить работу.

Перед исполнением операций front и pop программа должна проверять, содержится ли в очереди хотя бы один элемент.
Если во входных данных встречается операция front или pop, и при этом очередь пуста, то программа должна вместо числового
значения вывести строку error.

Формат ввода
Вводятся команды управления очередью, по одной на строке

Формат вывода
Требуется вывести протокол работы очереди, по одному сообщению на строке

Пример 1
Ввод
push 1
front
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
front
push 2
front
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
1
1
1
2
0
bye*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Queue<Integer> queue = new LinkedList<>();

        while (true) {
            String[] s = reader.readLine().split(" ");
            String command = s[0];

            if (command.equals("exit")) {
                System.out.println("bye");
                break;
            }
            else if (command.equals("push")) {
                queue.offer(Integer.parseInt(s[1]));
                System.out.println("ok");
            }
            else if (command.equals("pop")) {
                if (!queue.isEmpty()) {
                    int num = queue.poll();
                    System.out.println(num);
                } else {
                    System.out.println("error");
                }
            }
            else if (command.equals("front")) {
                if (!queue.isEmpty()) {
                    int num = queue.peek();
                    System.out.println(num);
                } else {
                    System.out.println("error");
                }
            }
            else if (command.equals("size")) {
                System.out.println(queue.size());
            }
            else if (command.equals("clear")) {
                queue.clear();
                System.out.println("ok");
            }
        }

        reader.close();
    }
}
