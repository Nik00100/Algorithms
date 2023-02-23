package ya_training.algo3_0.task3_22_02.task3;

/*Имеется калькулятор, который выполняет следующие операции:

умножить число X на 2;
умножить число X на 3;
прибавить к числу X единицу.
Определите, какое наименьшее количество операций требуется, чтобы получить из числа 1 число N.

Формат ввода
Во входном файле написано натуральное число N, не превосходящее 106.

Формат вывода
В первой строке выходного файла выведите минимальное количество операций. Во второй строке выведите числа,
последовательно получающиеся при выполнении операций. Первое из них должно быть равно 1, а последнее N. Если решений несколько,
выведите любое.

Пример 1
Ввод	Вывод
1

0
1
Пример 2
Ввод	Вывод
5

3
1 3 4 5*/

import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] dp = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            int minOps = dp[i - 1] + 1;
            if (i % 2 == 0)
                minOps = Math.min(minOps, dp[i / 2] + 1);
            if (i % 3 == 0)
                minOps = Math.min(minOps, dp[i / 3] + 1);
            dp[i] = minOps;
        }

        List<Integer> operations = new ArrayList<Integer>();
        StringBuilder sb = new StringBuilder();
        int i = n;
        while (i > 1) {
            if (dp[i] == (dp[i - 1] + 1)) {
                operations.add(i);
                i -= 1;
                continue;
            }
            if (i % 2 == 0 && dp[i] == dp[i / 2] + 1) {
                operations.add(i);
                i /= 2;
                continue;
            }
            operations.add( i);
            i /= 3;
        }
        operations.add(1);

        Collections.sort(operations);
        for (int num : operations) {
            sb.append(num).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);

        System.out.println(dp[n]);
        System.out.println(sb.toString());

        reader.close();
    }
}
