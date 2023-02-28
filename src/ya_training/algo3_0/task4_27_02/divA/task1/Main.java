package ya_training.algo3_0.task4_27_02.divA.task1;

/*Дана прямоугольная доска N × M (N строк и M столбцов). В левом верхнем углу находится шахматный конь, которого необходимо
переместить в правый нижний угол доски.

При этом конь может ходить следующим образом:


Необходимо определить, сколько существует различных маршрутов, ведущих из левого верхнего в правый нижний угол.

Формат ввода
Входной файл содержит два натуральных числа N и M (, ).

Формат вывода
В выходной файл выведите единственное число — количество способов добраться конём до правого нижнего угла доски.

Пример 1
Ввод	Вывод
4 4

2
Пример 2
Ввод	Вывод
2 3

1*/

import java.util.*;
import java.math.*;

public class Main {
    static BigInteger solve(BigInteger[][] dp, int i, int j, int n, int m) {
        if (i < 0 || j < 0 || i >= n || j >= m) {
            return BigInteger.ZERO;
        }
        if (!dp[i][j].equals(BigInteger.ZERO)) {
            return dp[i][j];
        }

        int[] dx = {-2, -2, -1, 1};
        int[] dy = {1, -1, -2, -2};

        BigInteger res = BigInteger.ZERO;
        for (int k = 0; k < 4; k++) {
            int ni = i + dx[k];
            int nj = j + dy[k];
            res = res.add(solve(dp, ni, nj, n, m));
        }
        dp[i][j] = res;
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        BigInteger[][] dp = new BigInteger[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = BigInteger.ZERO;
            }
        }

        dp[0][0] = BigInteger.ONE;

        System.out.println(solve(dp, n - 1, m - 1, n, m));

        sc.close();
    }
}
