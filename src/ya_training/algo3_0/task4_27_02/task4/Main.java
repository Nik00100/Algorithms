package ya_training.algo3_0.task4_27_02.task4;

/*Около Петиного университета недавно открылось новое кафе, в котором действует следующая система скидок: при каждой покупке
более чем на 100 рублей покупатель получает купон, дающий право на один бесплатный обед (при покупке на сумму 100 рублей и
меньше такой купон покупатель не получает).

Однажды Пете на глаза попался прейскурант на ближайшие N дней. Внимательно его изучив, он решил, что будет обедать в этом
кафе все N дней, причем каждый день он будет покупать в кафе ровно один обед. Однако стипендия у Пети небольшая, и поэтому
он хочет по максимуму использовать предоставляемую систему скидок так, чтобы его суммарные затраты были минимальны.
Требуется найти минимально возможную суммарную стоимость обедов и номера дней, в которые Пете следует воспользоваться купонами.

Формат ввода
В первой строке входного файла записано целое число N (0 ≤ N ≤ 100). В каждой из последующих N строк записано одно целое число,
обозначающее стоимость обеда в рублях на соответствующий день. Стоимость — неотрицательное целое число, не превосходящее 300.

Формат вывода
В первой строке выдайте минимальную возможную суммарную стоимость обедов. Во второй строке выдайте два числа K1 и K2 — количество
купонов, которые останутся неиспользованными у Пети после этих N дней и количество использованных им купонов соответственно.

В последующих K2 строках выдайте в возрастающем порядке номера дней, когда Пете следует воспользоваться купонами. Если существует
несколько решений с минимальной суммарной стоимостью, то выдайте то из них, в котором значение K1 максимально (на случай, если
Петя когда-нибудь ещё решит заглянуть в это кафе). Если таких решений несколько, выведите любое из них.

Пример
Ввод	Вывод
5
35
40
101
59
63

235
0 1
5*/

import java.io.*;
import java.util.*;

public class Main {
    private static final int INFINITY = 2_000_000_000;
    private static int[][] dp;
    private static int[] costs;

    static int getMinCost(int i, int j) {
        if (j > i) {
            return INFINITY;
        }
        if (j <= 0) {
            if (i >= 1) {
                int cost = costs[i];
                if (cost <= 100) {
                    return Math.min(getMinCost(i - 1, j + 1), getMinCost(i - 1, j) + cost);
                } else {
                    return getMinCost(i - 1, j + 1);
                }
            } else {
                return 0;
            }
        } else {
            if (dp[i][j] != -1) {
                return dp[i][j];
            }
            int cost = costs[i];
            if (cost > 100) {
                return dp[i][j] = Math.min(getMinCost(i - 1, j + 1), getMinCost(i - 1, j - 1) + cost);
            } else {
                return dp[i][j] = Math.min(getMinCost(i - 1, j + 1), getMinCost(i - 1, j) + cost);
            }
        }
    }

    static Deque<Integer> findDaysForUsingCoupons(int i, int j) {
        Deque<Integer> used = new ArrayDeque<>();
        if (j < i) {
            int cost = costs[i];
            if (j <= 0) {
                if (i >= 1) {
                    if (cost > 100) {
                        used.add(i);
                        used.addAll(findDaysForUsingCoupons(i - 1, j + 1));
                    } else {
                        boolean addi = (getMinCost(i, j) == getMinCost(i - 1, j + 1));
                        if (addi) {
                            used.add(i);
                            used.addAll(findDaysForUsingCoupons(i - 1, j + 1));
                        } else {
                            used.addAll(findDaysForUsingCoupons(i - 1, j));
                        }
                    }
                }
            } else {
                if (cost <= 100) {
                    boolean addi = (getMinCost(i - 1, j + 1) == getMinCost(i, j));
                    if (addi) {
                        used.add(i);
                        used.addAll(findDaysForUsingCoupons(i - 1, j + 1));
                    } else {
                        used.addAll(findDaysForUsingCoupons(i - 1, j));
                    }
                } else {
                    boolean addi = (getMinCost(i - 1, j + 1) == getMinCost(i, j));
                    if (addi) {
                        used.add(i);
                        used.addAll(findDaysForUsingCoupons(i - 1, j + 1));
                    } else {
                        used.addAll(findDaysForUsingCoupons(i - 1, j - 1));
                    }
                }
            }
        }
        return used;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine().trim());

        costs = new int[n+1];
        for (int i = 1; i <= n; i++) {
            costs[i] = Integer.parseInt(reader.readLine());
        }

        dp = new int[n + 1][n + 2];
        for(int[] arr : dp) {
            Arrays.fill(arr, -1);
        }

        int k1 = 0;
        int k2 = 0;
        int ans = INFINITY;

        for (int i = 0; i <= n; i++) {
            if (ans >= getMinCost(n, i)) {
                ans = getMinCost(n, i);
                k1 = i;
            }
        }

        System.out.println(ans);

        Deque<Integer> used = findDaysForUsingCoupons(n, k1);
        k2 = used.size();

        System.out.println(k1 + " " + k2);

        while (!used.isEmpty()) {
            System.out.println(used.removeLast());
        }

        reader.close();
    }
}
