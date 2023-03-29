package ya_int_0322.perfect_done;

/*Пример 1
Ввод
3 5 2
5 10 6
Вывод
2
1 3
Пример 2
Ввод
5 19 32
36 10 72 4 50
Вывод
2
2 4
Пример 3
Ввод
4 25 10
1 10 42 9
Вывод
0*/

import java.util.*;

public class ClassicKnapsak {
    public static int knapsack(int maxWeight, int[] weights,  Set<List<Integer>> items) {
        int n = weights.length;
        int[][] dp = new int[n + 1][maxWeight + 1];
        int val = 1; // assuming all items have the same value of 1
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= maxWeight; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(val + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // retrieve items
        for (int i = 1; i <= n; i++) {
            if (dp[i][maxWeight] == dp[n][maxWeight]) {
                int weight = maxWeight;
                int item = i;
                List<Integer> currentSet = new ArrayList<>();
                while (item > 0 && weight > 0) {
                    if (weight - weights[item-1] >= 0 && dp[item][weight] == dp[item-1][weight-weights[item-1]] + val) {
                        currentSet.add(item);
                        weight = weight - weights[item-1];
                        item--;
                    } else {
                        item--;
                    }
                }
                items.add(currentSet);
            }
        }

        return dp[n][maxWeight];
    }

    private static void printItems (int[][] dp, int[] weights, int maxWeight, int val) {
        int weight = maxWeight;
        int n = dp.length - 1;
        int res = dp[n][maxWeight];

        for (int i = n; i > 0 && res > 0; i--) {
            // either the result comes from the top
            // (dp[i-1][w]) or from (val + dp[i-1][weight-weights[i-1]]) as in Knapsack table.
            // If it comes from the latter one it means the item is included.
            if (res == dp[i - 1][weight]) continue;
            else {

                // This item is included.
                System.out.print(i + " ");

                // Since this weight is included its value is deducted
                res = res - val;
                weight = weight - weights[i - 1];
            }
        }
        System.out.println();
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

        //System.out.println(Arrays.stream(a).boxed().toList() + " - difference a[i] - x, started from index 1");

        Set<List<Integer>> items = new HashSet<>();
        int answer = knapsack((int)t, a, items);
        System.out.println(answer);

        List<Integer> resultItems = new ArrayList<>(items).get(0);
        Collections.sort(resultItems);
        for (int num : resultItems)
            System.out.print(num + " ");

        scanner.close();
    }
}
