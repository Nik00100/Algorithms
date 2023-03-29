package ya_int_0322.buy;

import java.io.*;
import java.util.*;

public class Q {
    static class Transaction {
        int buyDay;
        int sellDay;
        double profit;

        public Transaction(int buyDay, int sellDay, double profit) {
            this.buyDay = buyDay;
            this.sellDay = sellDay;
            this.profit = profit;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "buyDay=" + buyDay +
                    ", sellDay=" + sellDay +
                    ", profit=" + profit +
                    '}';
        }
    }
    private static final double START_BUDGET = 1.0;
    static void getTransactions (int[] prices, List<int[]> transactionDays) {
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

            transactionDays.add(new int[] {buyDay, sellDay});
        }
    }

    static double calcProfit(int buyPrice, int sellPrice, double currentStartBudget) {
        return ( currentStartBudget / (double) buyPrice) * (double) sellPrice;
    }

    static void solve (int[] prices, List<int[]> transactionDays) {
        if (prices == null || transactionDays.size() == 0) {
            System.out.println(0);
        } else if (transactionDays.size() == 1) {
            System.out.println(1);
            int buyDay1 = transactionDays.get(0)[0] + 1;
            int sellDay1 = transactionDays.get(0)[1] + 1;
            System.out.println(buyDay1 + " " + sellDay1);
        } else {
            System.out.println(2);

            int length = transactionDays.size();
            double maxProfit = START_BUDGET;
            Transaction[] transactions = new Transaction[length];
            for (int i = 0; i < length ; i++) {
                int buyDay = transactionDays.get(i)[0];
                int sellDay = transactionDays.get(i)[1];
                double curProfit = calcProfit(prices[buyDay], prices[sellDay], START_BUDGET);
                if (maxProfit < curProfit) {
                    transactions[i] = new Transaction(buyDay + 1, sellDay + 1, curProfit);
                    maxProfit = curProfit;
                } else if (transactions[i - 1] != null) {
                    transactions[i] = transactions[i - 1];
                }
            }

            System.out.println(Arrays.stream(transactions).toList());

            maxProfit = START_BUDGET;
            int buyDay1 = -1;
            int sellDay1 = -1;
            int buyDay2 = -1;
            int sellDay2 = -1;
            for (int i = length - 1; i > 0; i--) {
                int buDay = transactionDays.get(i)[0];
                int sellDay = transactionDays.get(i)[1];
                double startBudget = transactions[i - 1].profit;
                double curProfit = calcProfit(prices[buDay], prices[sellDay], startBudget);
                if (maxProfit < curProfit) {
                    maxProfit = curProfit;
                    buyDay1 = transactions[i - 1].buyDay;
                    sellDay1 = transactions[i - 1].sellDay;
                    buyDay2 = buDay + 1;
                    sellDay2 = sellDay + 1;
                }
            }
            System.out.println(buyDay1 + " " + sellDay1);
            System.out.println(buyDay2 + " " + sellDay2);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] prices = new int[n];
        String[] s = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            prices[i] = Integer.parseInt(s[i]);
        }

        List<int[]> transactionDays = new ArrayList<>();

        getTransactions(prices, transactionDays);

        solve(prices, transactionDays);

        reader.close();
    }
}
