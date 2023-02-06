package ya_training.algo1_0.task6.binSearch_Approx;

/*Для каждого из чисел второй последовательности найдите ближайшее к нему в первой.

Формат ввода
В первой строке входных данных содержатся числа N и K (). Во второй строке задаются N чисел первого массива, отсортированного
по неубыванию, а в третьей строке – K чисел второго массива. Каждое число в обоих массивах по модулю не превосходит 2⋅109.

Формат вывода
Для каждого из K чисел выведите в отдельную строку число из первого массива, наиболее близкое к данному. Если таких несколько,
выведите меньшее из них.

Пример 1
Ввод	Вывод
5 5
1 3 5 7 9
2 4 8 1 6
1
3
7
1
5
Пример 2
Ввод	Вывод
6 11
1 1 4 4 8 120
1 2 3 4 5 6 7 8 63 64 65
1
1
4
4
4
4
8
8
8
8
120
Пример 3
Ввод	Вывод
10 10
-5 1 1 3 5 5 8 12 13 16
0 3 7 -17 23 11 0 11 15 7
1
3
8
-5
16
12
1
12
16
8*/

import java.io.*;

public class Solution {
    static int bSearch (int num, int[] arr) {
        int l = 0;
        var r = arr.length - 1;
        while (r > l) {
            int middle = (l + r) / 2;
            if (num <= arr[middle]) {
                r = middle;
            } else {
                l = middle + 1;
            }
        }
        return r;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = reader.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);

        String[] sN = reader.readLine().split(" ");
        int[] N = new int[n];
        for (int i = 0; i < n; i++) {
            N[i] = Integer.parseInt(sN[i]);
        }

        String[] sK = reader.readLine().split(" ");
        int[] K = new int[k];
        for (int i = 0; i < k; i++) {
            K[i] = Integer.parseInt(sK[i]);
        }

        for (int i=0; i<k; i++) {
            int index = bSearch(K[i],N);
            if (N[index] == K[i]) {
                System.out.println(K[i]);
            } else {
                // найти ближайшее к i
                if (index == 0) {
                    System.out.println(N[0]);
                } else {
                    int that = Math.abs(K[i] - N[index]);
                    int before = Math.abs(K[i] - N[index - 1]);

                    if (before <= that) {
                        System.out.println(N[index-1]);
                    } else {
                        System.out.println(N[index]);
                    }
                }
            }
        }

        reader.close();
    }
}

