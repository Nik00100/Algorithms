package ya_training.algo3_0.contest_13_02.task3;

/*Диего увлекается коллекционированием наклеек. На каждой из них написано число, и каждый коллекционер мечтает собрать наклейки
со всеми встречающимися числами.

Диего собрал N наклеек, некоторые из которых, возможно, совпадают. Как-то раз к нему пришли K коллекционеров. i-й из них собрал
все наклейки с номерами не меньшими, чем pi. Напишите программу, которая поможет каждому из коллекционеров определить, сколько
недостающих ему наклеек есть у Диего. Разумеется, гостей Диего не интересуют повторные экземпляры наклеек.

Формат ввода
В первой строке содержится единственное число N (0 ≤ N ≤ 100000) — количество наклеек у Диего.

В следующей строке содержатся N целых неотрицательных чисел (не обязательно различных) — номера наклеек Диего. Все номера наклеек
не превосходят 10^9.

В следующей строке содержится число K (0 ≤ K ≤ 100000) — количество коллекционеров, пришедших к Диего. В следующей строке
содержатся K целых чисел pi (0 ≤ pi ≤ 10^9), где pi — наименьший номер наклейки, не интересующий i-го коллекционера.

Формат вывода
Для каждого коллекционера в отдельной строке выведите количество различных чисел на наклейках, которые есть у Диего, но нет у
этого коллекционера.

Пример 1
Ввод
1
5
2
4 6

Вывод
0
1

Пример 2
Ввод
3
100 1 50
3
300 0 75

Вывод
3
0
2*/

import java.io.*;
import java.util.*;

public class Main {
    static int countInterestingSteakers (int num, int[] A) {
        if (num <= A[0]) return 0;
        int l = 0;
        int r = A.length - 1;
        while (l < r) {
            int mid = (l + r + 1)/2;
            if (A[mid] < num) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        return l + 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input8.txt")));

        int n = Integer.parseInt(reader.readLine());
        String[] sn = reader.readLine().split(" ");
        int k = Integer.parseInt(reader.readLine());
        String[] sk = reader.readLine().split(" ");

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i<n; i++) {
            int num = Integer.parseInt(sn[i]);
            set.add(num);
        }

        int[] A = set.stream().mapToInt(Integer::valueOf).sorted().toArray();

        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        for (int i = 0; i<k; i++) {
            int num = Integer.parseInt(sk[i]);
            writer.write(String.valueOf(countInterestingSteakers(num, A)));
            writer.write('\n');
        }

        reader.close();
        writer.close();
    }
}
