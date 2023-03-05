package ya_training.algo3_0.task3_22_02.divA.task5;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int k = 1; k < i; k++) {
                int currMax = Math.max(dp[k] + a, dp[i - k] + b);
                if (currMax < min) {
                    min = currMax;
                }
            }
            dp[i] = min;
        }

        System.out.println(dp[n]);
    }
}