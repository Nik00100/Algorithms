package ya_training.algo3_0.final_sprint.task3.test;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] times = new int[n][2];
        for (int i = 0; i < n; i++) {
            times[i][0] = scanner.nextInt(); // время доставки первого курьера
            times[i][1] = scanner.nextInt(); // время доставки второго курьера
        }

        int[][] dp = new int[n + 1][2]; // таблица динамического программирования

        // вычисляем время доставки всех заказов для первого курьера
        for (int i = 1; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]) + times[i - 1][0];
        }

        // вычисляем время доставки всех заказов для второго курьера
        for (int i = 1; i <= n; i++) {
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]) + times[i - 1][1];
        }

        // восстанавливаем оптимальный порядок доставки заказов
        int[] ans = new int[n];
        int cur = dp[n][0] < dp[n][1] ? 0 : 1;
        for (int i = n - 1; i >= 0; i--) {
            if (cur == 0 && dp[i][0] >= dp[i][1]) {
                ans[i] = 1;
                cur = 1;
            } else if (cur == 1 && dp[i][1] >= dp[i][0]) {
                ans[i] = 2;
                cur = 2;
            } else if (cur == 1 && dp[i][1] < dp[i][0]) {
                ans[i] = 1;
            } else {
                ans[i] = 2;
            }
        }

        // выводим ответ
        for (int i = 0; i < n; i++) {
            System.out.print(ans[i] + " ");
        }
    }
}
