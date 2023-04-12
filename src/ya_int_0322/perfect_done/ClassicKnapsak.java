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

import java.io.*;
import java.util.*;

/**
 * Решение на основе классической задачи о рюкзаке
 * */
public class ClassicKnapsak {
    public static int knapsack(int maxWeight, int[] weights,  Set<List<Integer>> items) {
        int n = weights.length;
        int[][] dp = new int[n + 1][maxWeight + 1];
        int val = 1; // все скульптуры имеют одинаковую ценность 1
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= maxWeight; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(val + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // восстановление содержимого рюкзака
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

    /**
     * Дополнительный метод восстановления содержимого (в данной задаче не использую, но пригодится в будущем)
     * */
    private static void printItems (int[][] dp, int[] weights, int maxWeight, int val) {
        int weight = maxWeight;
        int n = dp.length - 1;
        int res = dp[n][maxWeight];

        for (int i = n; i > 0 && res > 0; i--) {
            // Результат берем в массиве dp[][] из (dp[i-1][w]) или (val + dp[i-1][weight-weights[i-1]]).
            // Если res == dp[i-1][w], то пропускаем цикл.
            if (res == dp[i - 1][weight]) {
                continue;
            } else {
                // Данный предмет добавлен в рюкзак.
                System.out.print(i + " ");
                // Т.к. предмет добавлен - необходимо вычесть его ценность
                res = res - val;
                weight = weight - weights[i - 1];
            }
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] s1 = reader.readLine().split(" ");
        int n = Integer.parseInt(s1[0]);
        int x = Integer.parseInt(s1[1]); // масса в килограммах идеальной скульптуры
        long t = Integer.parseInt(s1[2]);

        int[] sculptures = new int[n]; // массив времени преобразования i-ой скульптуры в идеальную
        String[] s2 = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            int mass = Integer.parseInt(s2[i]); // масса в килограммах k-ой скульптуры
            int timeToBecamePerfect = Math.abs(mass - x); // время для того, чтобы сделать k-ую скульптуру идеальной
            sculptures[i] = timeToBecamePerfect;
        }

        Set<List<Integer>> items = new HashSet<>();
        int answer = knapsack((int)t, sculptures, items);
        System.out.println(answer);

        List<Integer> resultItems = new ArrayList<>(items).get(0);
        Collections.sort(resultItems);
        for (int num : resultItems)
            System.out.print(num + " ");

        reader.close();
    }
}
