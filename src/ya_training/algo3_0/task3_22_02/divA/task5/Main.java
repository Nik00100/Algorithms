package ya_training.algo3_0.task3_22_02.divA.task5;

import java.util.*;

public class Main {
    static int n, a, b;
    static int[] dp = new int[1001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        a = sc.nextInt();
        b = sc.nextInt();
        int ans = solve(n);
        System.out.println(ans);
    }

    static int solve(int x) {
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= x; i++) {
            for (int k = 1; k <= i; k++) {
                int candies = 0;

                // Check if the number is equal to k
                candies = a + dp[i-k];
                dp[i] = Math.min(dp[i], candies);

                // Check if the number is even
                if (k % 2 == 0) {
                    candies = a + dp[k/2];
                    dp[i] = Math.min(dp[i], candies);
                }

                // Check if the number is a prime number
                if (isPrime(k)) {
                    candies = a + dp[i-k];
                    dp[i] = Math.min(dp[i], candies);
                }

                // Add more questions here...
            }
            dp[i] += b;
        }
        return dp[x];
    }

    static boolean isPrime(int x) {
        if (x < 2) return false;
        for (int i = 2; i <= Math.sqrt(x); i++) {
            if (x % i == 0) return false;
        }
        return true;
    }
}
