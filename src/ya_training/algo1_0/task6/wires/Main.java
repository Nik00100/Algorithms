package ya_training.algo1_0.task6.wires;

/*Дано N отрезков провода длиной L1, L2, ..., LN сантиметров. Требуется с помощью разрезания получить из них K равных отрезков
как можно большей длины, выражающейся целым числом сантиметров. Если нельзя получить K отрезков длиной даже 1 см, вывести 0.

Формат ввода
В первой строке находятся числа N и К. В следующих N строках - L1, L2, ..., LN, по одному числу в строке.

Ограничения: 1 ≤ N, K ≤ 10 000, 100 ≤ Li ≤ 10 000 000, все числа целые.

Формат вывода
Вывести одно число - полученную длину отрезков.

Пример
Ввод
4 11
802
743
457
539

Вывод
200*/

import java.io.*;
import java.util.*;

public class Main {

    static boolean check(int[] L, int k, int length) {
        int count = 0;
        for (int i : L) {
            count += i / length;
        }
        return count >= k;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = reader.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);

        int[] L = new int[n];
        for (int i = 0; i < n; i++)
            L[i] = Integer.parseInt(reader.readLine());

        int l = 0;
        // длина максимально возможного отрезка
        int r = 10_000_000;


        // сначала все хорошо, потом плохо - последнее подходящее
        while (l < r) {
            // middle - это длина провода
            // округление вверх
            int middle = (r + l + 1) / 2;
            if (!check(L, k, middle)) {
                r = middle - 1;
            } else {
                l = middle;
            }
        }

        System.out.println(l);

        reader.close();
    }
}
