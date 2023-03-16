package ya_int_0322.buy.q;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = scanner.nextInt();
        }

        List<int[]> transactions = new ArrayList<>();
        int buy1 = Integer.MIN_VALUE, sell1 = 0, buy2 = Integer.MIN_VALUE, sell2 = 0;

        for (int i = 0; i < n; i++) {
            int currentPrice = prices[i];
            if (buy1 < sell1 - currentPrice) {
                buy2 = buy1;
                sell2 = sell1;
                buy1 = sell1 - currentPrice;
                sell1 = currentPrice;
            } else if (buy2 < sell2 - currentPrice) {
                buy2 = sell1 - currentPrice;
                sell2 = currentPrice;
            }

        }
        if (buy2 == Integer.MIN_VALUE) {
            if (buy1 == Integer.MIN_VALUE) {
                System.out.println(0);
            } else {
                System.out.println(1);
                int[] transaction = {0, n - 1};
                transactions.add(transaction);
            }
        } else {
            System.out.println(2);
            int[] transaction1 = {0, n - 1};
            int[] transaction2 = {0, n - 1};
            for (int i = 0; i < n; i++) {
                int currentPrice = prices[i];
                if (i < sell1) {
                    if (currentPrice <= prices[sell1]) {
                        sell1 = i;
                    } else {
                        transaction1[1] = sell1;
                        transactions.add(transaction1);
                        transaction1 = new int[]{i, n - 1};
                        sell1 = i;
                    }
                } else if (i < sell2) {
                    if (currentPrice <= prices[sell2]) {
                        sell2 = i;
                    } else {
                        transaction2[1] = sell2;
                        transactions.add(transaction2);
                        transaction2 = new int[]{i, n - 1};
                        sell2 = i;
                    }
                } else {
                    if (currentPrice >= prices[buy2] + sell2 - transaction2[0]) {
                        transaction2[1] = sell2;
                        transactions.add(transaction2);
                        transaction2 = new int[]{transaction2[0], i};
                        buy2 = sell2 - currentPrice;
                        sell2 = i;
                    }
                }
            }
            transaction1[1] = sell1;
            transaction2[1] = sell2;
            transactions.add(transaction1);
            transactions.add(transaction2);
            for (int[] transaction : transactions) {
                System.out.println((transaction[0] + 1) + " " + (transaction[1] + 1));
            }
        }
    }
}
