package ya_training.algo3_0.task3_22_02.divA.task5;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int[] minCandies = new int[n + 1];
        boolean[] known = new boolean[n + 1];

        minCandies[1] = 0;
        known[1] = true;

        for (int i = 2; i <= n; i++) {
            minCandies[i] = Integer.MAX_VALUE;

            for (int j = 1; j < i; j++) {
                if (i % j == 0) {
                    minCandies[i] = Math.min(minCandies[i], minCandies[j] + a);
                } else {
                    minCandies[i] = Math.min(minCandies[i], minCandies[j] + b);
                }
            }

            for (int j = i + i; j <= n; j += i) {
                minCandies[j] = Math.min(minCandies[j], minCandies[i] + b);
            }

            known[minCandies[i]] = true;
        }

        int result = Integer.MAX_VALUE;

        for (int i = 2; i <= n; i++) {
            if (!known[i]) {
                result = Math.min(result, minCandies[i]);
            }
        }

        System.out.println(result);
    }
}
