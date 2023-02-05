package ya_training.algo1_0.task3.turtules;

/*Широко известна следующая задача для младших школьников. Три черепахи ползут по дороге. Одна черепаха говорит:
“Впереди меня две черепахи”. Другая черепаха говорит: “Позади меня две черепахи”. Третья черепаха говорит:
“Впереди меня две черепахи и позади меня две черепахи”. Как такое может быть? Ответ: третья черепаха врет!
По дороге одна за другой движутся N черепах. Каждая черепаха говорит фразу вида: “Впереди меня ai черепах,
а позади меня bi черепах”. Ваша задача определить, сколько самое большее количество черепах могут говорить правду.

Формат ввода
В первой строке вводится целое число N (1 ≤ N ≤ 10000) строк, содержащих целые числа ai и bi, по модулю не превосходящие 10000,
описывающие высказывание i-ой черепахи.

Формат вывода
Выведите целое число M – максимальное количество черепах, которые могут говорить правду.

Пример 1
Ввод
3
2 0
0 2
2 2
Вывод
2
Пример 2
Ввод
5
0 4
1 3
2 2
3 1
4 0
Вывод
5
Пример 3
Ввод
10
9 1
8 1
7 2
6 2
5 3
4 4
3 6
2 7
1 9
0 8
Вывод
4*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        Set<Integer> usedBefore = new HashSet<>();
        for (int i=0; i<n; i++) {
            String[] s = reader.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            if (a>=0 && b >=0 && (a + b) == n-1 && !usedBefore.contains(a)) // признаки, что черепаха говорит правду
                usedBefore.add(a);
        }

        System.out.println(usedBefore.size());
        reader.close();
    }
}
