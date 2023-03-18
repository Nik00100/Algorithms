package ya_int_0322.perfect;

import java.util.*;

public class ClassicKnapsak {
    static int[] findItems(int[] weights, int maxWeight) {
        int[][] dp = new int[weights.length+1][maxWeight+1];

        for (int i = 1; i <= weights.length; i++) {
            for (int j = 1; j <= maxWeight; j++) {
                if (weights[i-1] <= j) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weights[i-1]]+1);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        int[] items = new int[dp[weights.length][maxWeight]];
        int index = items.length-1;
        int j = maxWeight;
        for (int i = weights.length; i > 0 && index >= 0; i--) {
            if (dp[i][j] != dp[i-1][j]) {
                items[index] = i-1;
                index--;
                j -= weights[i-1];
            }
        }

        return items;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = scanner.nextInt();
        long t = scanner.nextLong();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            a[i] = Math.abs(num - x);
        }

        System.out.println(Arrays.stream(a).boxed().toList());

        int[] answer = findItems(a, (int)t);
        System.out.println(answer.length);
        StringBuilder sb = new StringBuilder();
        for (int num : answer) {
            sb.append(num).append(" ");
        }
        System.out.println(sb.toString().trim());


        scanner.close();
    }
}
