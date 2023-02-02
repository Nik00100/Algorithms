package training.algo1_0.task3.commonNums;

/*Даны два списка чисел, которые могут содержать до 10000 чисел каждый. Выведите все числа, которые входят как в первый,
так и во второй список в порядке возрастания. Примечание. И даже эту задачу на Питоне можно решить в одну строчку.

Формат ввода
Вводятся два списка целых чисел. Все числа каждого списка находятся на отдельной строке.

Формат вывода
Выведите ответ на задачу.

Пример 1
Ввод	Вывод
1 3 2
4 3 2
2 3
Пример 2
Ввод	Вывод
1 2 6 4 5 7
10 2 3 4 8
2 4*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] f = reader.readLine().split(" ");
        String[] s = reader.readLine().split(" ");

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < f.length; i++) {
            set.add(Integer.parseInt(f[i]));
        }

        StringBuilder sb = new StringBuilder();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            int num = Integer.parseInt(s[i]);
            if (set.contains(num))
                list.add(num);

        }
        Collections.sort(list);
        for (Integer num : list) {
            sb.append(num).append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());

        reader.close();
    }
}
