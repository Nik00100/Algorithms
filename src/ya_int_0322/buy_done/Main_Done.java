package ya_int_0322.buy_done;

import java.util.*;

public class Main_Done {
    private static final double START_BUDGET = 1.0;

    static List<int[]> getTransactions (int[] prices) {
        List<int[]> transactionDays = new ArrayList<>();
        int n = prices.length;
        int i = 0;
        while (i < n - 1) {
            while (i < n - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            if (i == n - 1) {
                break;
            }
            int buyDay = i;
            i++;


            while (i < n && prices[i] >= prices[i - 1]) {
                i++;
            }
            int sellDay = i - 1;

            transactionDays.add(new int[] {buyDay + 1, sellDay + 1});
        }
        return transactionDays;
    }

    static void twoTProfit(int[] prices) {
        double buy1 = 0, sell1 = START_BUDGET, buy2 = 0, sell2 = START_BUDGET;

        double[] b1 = new double[prices.length];
        double[] s1 = new double[prices.length];
        double[] s2 = new double[prices.length];
        double[] b2 = new double[prices.length];
        b1[0] = buy1;
        s1[0] = sell1;
        b2[0] = buy2;
        s2[0] = sell2;

        int buyDay1 = 0, sellDay1 = 0, buyDay2 = 0, sellDay2 = 0;
        for (int i = 0; i < prices.length; i++) {
            int price = prices[i];
            buy1 = Math.max(buy1, START_BUDGET / price);
            b1[i] = buy1;
            sell1 = Math.max(sell1, buy1 * price);
            s1[i] = sell1;
            buy2 = Math.max(buy2, sell1 / price);
            b2[i] = buy2;
            sell2 = Math.max(sell2, buy2 * price);
            s2[i] = sell2;
        }

        /*System.out.println(Arrays.stream(b1).boxed().toList());
        System.out.println(Arrays.stream(s1).boxed().toList());
        System.out.println(Arrays.stream(b2).boxed().toList());
        System.out.println(Arrays.stream(s2).boxed().toList());*/

        for (int i = 0; i < s2.length; i++) {
            if (s2[i] == sell2) {
                sellDay2 = i + 1;
                break;
            }
        }

        double b2Max = 0;
        for (int i = 0; i < sellDay2 - 1; i++) {
            if (b2[i] > b2Max) {
                b2Max = b2[i];
                buyDay2 = i + 1;
            }
        }

        double s1Max = 0;
        for (int i = 0; i < buyDay2 - 1; i++) {
            if (s1[i] > s1Max) {
                s1Max = s1[i];
                sellDay1 = i + 1;
            }
        }

        double b1Max = 0;
        for (int i = 0; i < sellDay1 - 1; i++) {
            if (b1[i] > b1Max) {
                b1Max = b1[i];
                buyDay1 = i + 1;
            }
        }

        System.out.println(2);
        System.out.println(buyDay1 + " " + sellDay1);
        System.out.println(buyDay2 + " " + sellDay2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = scanner.nextInt();
        }

        List<int[]> transactions = getTransactions(prices);

        if (transactions.size() == 0) {
            System.out.println(0);
        } else if (transactions.size() == 1) {
            System.out.println(1);
            System.out.println(transactions.get(0)[0] + " " + transactions.get(0)[1]);
        } else {
            twoTProfit(prices);
        }

        scanner.close();
    }
}
