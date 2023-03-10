package ya_training.algo3_0.task3_22_02.divA.task1;

/*Дано натуральное число N. Необходимо представить его в виде суммы точных кубов, содержащей наименьшее число слагаемых.
Программа должна вывести это число слагаемых.
Формат ввода
Программа получает на вход натуральное число N, не превосходящее 10^6
Формат вывода
Программа должна вывести единственное натуральное число
Пример
Ввод	Вывод
9
2*/

import java.util.*;

public class Main {
    static final int MAX = 1000000;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] dp = new int[MAX];
        ArrayList<Integer> coubs = new ArrayList<>();

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        for (int i = 1; i <= 100; ++i) {
            coubs.add(i*i*i);
        }

        for (int i = 4; i <= n; ++i) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < coubs.size(); ++j) {
                if (i >= coubs.get(j) && dp[i] > dp[i - coubs.get(j)] + 1) {
                    dp[i] = dp[i - coubs.get(j)] + 1;
                }
            }
        }

        System.out.println(dp[n]);
    }
}

