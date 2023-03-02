package ya_training.algo3_0.task3_22_02.divA.task2;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int[] dp = new int[n];
        int[] prev = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] > a[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
        }

        int maxIndex = 0;
        for (int i = 1; i < n; i++) {
            if (dp[i] > dp[maxIndex]) {
                maxIndex = i;
            }
        }

        List<Integer> lis = new ArrayList<>();
        while (maxIndex != -1) {
            lis.add(a[maxIndex]);
            maxIndex = prev[maxIndex];
        }
        Collections.reverse(lis);

        StringBuilder sb = new StringBuilder();
        for (int num : lis) {
            sb.append(num).append(" ");
        }
        System.out.print(sb.toString().trim());
        sc.close();
    }
}
