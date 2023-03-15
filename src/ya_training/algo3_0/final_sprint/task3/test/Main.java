package ya_training.algo3_0.final_sprint.task3.test;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] times = new int[n][2];
        for (int i = 0; i < n; i++) {
            times[i][0] = scanner.nextInt(); // время доставки первым курьером
            times[i][1] = scanner.nextInt(); // время доставки вторым курьером
        }
        int[][] dp = new int[n][201]; // dp[index][first] = second
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 201; j++) {
                dp[i][j] = Integer.MAX_VALUE; // заполняем массив максимальными значениями
            }
        }
        dp[0][times[0][0]] = times[0][1]; // инициализируем dp[0][first] = second для первого заказа
        dp[0][times[0][1]] = times[0][0]; // инициализируем dp[0][first] = second для первого заказа
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 201; j++) {
                for (int k = 0; k < 201; k++) {
                    if (j != k) {
                        int first = Math.max(j, dp[i - 1][k]);
                        int second = Math.max(k, dp[i - 1][j]);
                        dp[i][j] = Math.min(dp[i][j], second + times[i][1]); // курьер 2 забирает заказ
                        dp[i][k] = Math.min(dp[i][k], first + times[i][0]); // курьер 1 забирает заказ
                    }
                }
            }
        }
        int[] ans = new int[n];
        int minTime = Integer.MAX_VALUE;
        int courier = 0;
        for (int j = 0; j < 201; j++) {
            int time = Math.max(j, dp[n - 1][j]);
            if (time < minTime) {
                minTime = time;
                courier = j;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            if (minTime - times[i][0] == dp[i - 1][courier]) {
                ans[i] = 1;
                courier = Math.max(courier, times[i][0]);
            } else {
                ans[i] = 2;
                courier = Math.max(courier, times[i][1]);
            }
            minTime = Math.max(minTime, courier);
        }
        for (int i = 0; i < n; i++) {
            System.out.print(ans[i] + " ");
        }
    }
}
