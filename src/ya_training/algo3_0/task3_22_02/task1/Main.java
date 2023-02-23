package ya_training.algo3_0.task3_22_02.task1;

/*По данному числу N определите количество последовательностей из нулей и единиц длины N, в которых никакие
три единицы не стоят рядом.

Формат ввода
Во входном файле написано натуральное число N, не превосходящее 35.

Формат вывода
Выведите количество искомых последовательностей. Гарантируется, что ответ не превосходит 2^31.

Пример
Ввод	Вывод
1
2*/

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        long[] dp = new long[36];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;
        dp[3] = 7;
        int i = 3;
        while (i <= n) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
            i++;
        }
        System.out.println(dp[n]);
        reader.close();
    }
}
