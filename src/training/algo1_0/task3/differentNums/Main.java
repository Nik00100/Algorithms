package training.algo1_0.task3.differentNums;

/*Дан список чисел, который может содержать до 100000 чисел. Определите, сколько в нем встречается различных чисел.

Формат ввода
Вводится список целых чисел. Все числа списка находятся на одной строке.

Формат вывода
Выведите ответ на задачу.

Пример 1
Ввод	Вывод
1 2 3 2 1
3
Пример 2
Ввод	Вывод
1 2 3 4 5 6 7 8 9 10
10
Пример 3
Ввод	Вывод
1 2 3 4 5 1 2 1 2 7 3
6*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = reader.readLine().split(" ");

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < s.length; i++) {
            set.add(Integer.parseInt(s[i]));
        }

        System.out.println(set.size());

        reader.close();
    }
}
