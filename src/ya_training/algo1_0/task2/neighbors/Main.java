package ya_training.algo1_0.task2.neighbors;

/*Дан список чисел. Определите, сколько в этом списке элементов, которые больше двух своих соседей и выведите количество
таких элементов.

Формат ввода
Вводится список чисел. Все числа списка находятся на одной строке.

Формат вывода
Выведите ответ на задачу.

Пример 1
Ввод	Вывод
1 2 3 4 5
0
Пример 2
Ввод	Вывод
5 4 3 2 1
0
Пример 3
Ввод	Вывод
1 5 1 5 1
2*/

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = reader.readLine().split(" ");

        long[] arr = new long[s.length];

        for (int i = 0; i < s.length; i++) {
            arr[i] = Long.parseLong(s[i]);
        }

        int ans =0;

        for (int i = 1; i < s.length-1; i++) {
            if (arr[i] > arr[i-1] && arr[i] > arr[i+1]) ans++;
        }

        System.out.println(ans);

        reader.close();
    }
}
