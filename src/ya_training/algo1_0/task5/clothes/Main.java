package ya_training.algo1_0.task5.clothes;

/*Глеб обожает шоппинг. Как-то раз он загорелся идеей подобрать себе майку и штаны так, чтобы выглядеть в них максимально стильно.
В понимании Глеба стильность одежды тем больше, чем меньше разница в цвете элементов его одежды.

В наличии имеется N (1≤N≤100000) маек и M (1≤M≤100000) штанов, про каждый элемент известен его цвет
(целое число от 1 до 10000000). Помогите Глебу выбрать одну майку и одни штаны так, чтобы разница в их цвете была как можно меньше.

Формат ввода
Сначала вводится информация о майках: в первой строке целое число N (1≤N≤100000) и во второй N целых чисел от 1 до 10000000 —
цвета имеющихся в наличии маек. Гарантируется, что номера цветов идут в возрастающем порядке
(в частности, цвета никаких двух маек не совпадают).

Далее в том же формате идёт описание штанов: их количество M (1≤M≤100000) и в следующей строке M целых чисел от 1 до 10000000
в возрастающем порядке — цвета штанов.

Формат вывода
Выведите пару неотрицательных чисел — цвет майки и цвет штанов, которые следует выбрать Глебу. Если вариантов выбора несколько,
выведите любой из них.

Пример 1
Ввод
2
3 4
3
1 2 3
Вывод
3 3
Пример 2
Ввод
2
4 5
3
1 2 3
Вывод
4 3
Пример 3
Ввод
5
1 2 3 4 5
5
1 2 3 4 5
Вывод
1 1*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] N = new int[n];
        String[] sN = reader.readLine().split(" ");
        for (int i=0; i<n; i++) {
            N[i] = Integer.parseInt(sN[i]);
        }

        int m = Integer.parseInt(reader.readLine());
        int[] M = new int[m];
        String[] sM = reader.readLine().split(" ");
        for (int i=0; i<m; i++) {
            M[i] = Integer.parseInt(sM[i]);
        }

        int pN = 0;
        int pM = 0;

        int i = 0;
        int j = 0;
        int diff = Math.abs(N[pN] - M[pM]);
        while (i < n && j < m ) {
            if (Math.abs(N[i] - M[j]) < diff) {
                diff = Math.abs(N[i] - M[j]);
                pN = i;
                pM = j;
            }
            if (N[i] < M[j]) {
                i += 1;
            } else {
                j += 1;
            }
        }

        System.out.println(N[pN]+" "+M[pM]);

        reader.close();
    }
}
