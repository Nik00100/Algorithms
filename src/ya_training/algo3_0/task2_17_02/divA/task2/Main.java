package ya_training.algo3_0.task2_17_02.divA.task2;

/*Гоблины Мглистых гор очень любях ходить к своим шаманам. Так как гоблинов много, к шаманам часто образуются очень длинные очереди.
 А поскольку много гоблинов в одном месте быстро образуют шумную толку, которая мешает шаманам проводить сложные медицинские
 манипуляции, последние решили установить некоторые правила касательно порядка в очереди.
Обычные гоблины при посещении шаманов должны вставать в конец очереди. Привилегированные же гоблины, знающие особый пароль,
встают ровно в ее середину, причем при нечетной длине очереди они встают сразу за центром.
Так как гоблины также широко известны своим непочтительным отношением ко всяческим правилам и законам, шаманы попросили вас
написать программу, которая бы отслеживала порядок гоблинов в очереди.

Формат ввода
В первой строке входных данный записано число N (1≤N≤10^5) — количество запросов к программе. Следующие N строк содержат описание
запросов в формате:
”+ i” — гоблин с номером i (1≤i≤N) встает в конец очереди.
”* i” — привилегированный гоблин с номером i встает в середину очереди.
”-” — первый гоблин из очереди уходит к шаманам. Гарантируется, что на момент такого запроса очередь не пуста.

Формат вывода
Для каждого запроса типа ”-” программа должна вывести номер гоблина, который должен зайти к шаманам.*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        int n = Integer.parseInt(reader.readLine());
        Deque<Integer> queue1 = new ArrayDeque<>();
        Deque<Integer> queue2 = new ArrayDeque<>();

        char sign;
        int x;
        while (n-- > 0) {
            String input = reader.readLine();
            sign = input.charAt(0);
            if (sign == '+') {
                x = Integer.parseInt(input.substring(2));
                queue2.addLast(x);
            } else if (sign == '*') {
                x = Integer.parseInt(input.substring(2));
                queue2.addFirst(x);
            } else {
                System.out.println(queue1.peekFirst());
                queue1.removeFirst();
            }
            if (queue1.size() < queue2.size()) {
                queue1.addLast(queue2.peekFirst());
                queue2.removeFirst();
            }
        }

        reader.close();
    }
}
