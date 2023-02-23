package ya_training.algo3_0.task3_22_02.task5;

/*В дощечке в один ряд вбиты гвоздики. Любые два гвоздика можно соединить ниточкой. Требуется соединить
некоторые пары гвоздиков ниточками так, чтобы к каждому гвоздику была привязана хотя бы одна ниточка, а суммарная длина
всех ниточек была минимальна.

Формат ввода
В первой строке входных данных записано число N — количество гвоздиков (2 ≤ N ≤ 100). В следующей строке заданы
N чисел — координаты всех гвоздиков (неотрицательные целые числа, не превосходящие 10000).

Формат вывода
Выведите единственное число — минимальную суммарную длину всех ниточек.

Пример
Ввод	Вывод
6
3 13 12 4 14 6

5*/

import java.io.*;
import java.util.*;

public class Main {
    static int maximum(int i, int n, int[] input, int[] dp) {
        if (i > n - 3) {
            return 0;
        }
        if (dp[i] == 0) {
            dp[i] = input[i + 1] - input[i] + Math.max(maximum(i + 2, n, input, dp), maximum(i + 3, n, input, dp));
        }
        return dp[i];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] input = new int[n];
        String[] s = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(s[i]);
        }
        Arrays.sort(input);
        int[] dp = new int[n];
        System.out.println(input[n - 1] - input[0] - Math.max(maximum(1, n, input, dp), maximum(2, n, input, dp)));

        reader.close();
    }
}
