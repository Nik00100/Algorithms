package ya_int_0322.buy;

import java.io.*;
import java.util.*;

public class TL15 {
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

            transactionDays.add(new int[]{buyDay, sellDay});
        }
    }

    static double calcProfit(int buyPrice, int sellPrice, double currentStartBudget) {
        return (currentStartBudget / buyPrice) * sellPrice;
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

        if (transactionDays.size() == 0) {
            System.out.println(0);
        } else if (transactionDays.size() == 1){
            System.out.println(1);
            System.out.println((transactionDays.get(0)[0] + 1) + " " + (transactionDays.get(0)[1] + 1));
        } else {
            double maxProfit = 0;
            int buyDay1 = 0;
            int sellDay1 = 0;
            int buyDay2 = 0;
            int sellDay2 = 0;
            for (int i = 0; i < transactionDays.size(); i++) {
                int firstBuyPrice = prices[transactionDays.get(i)[0]];
                int firstSellPrice = prices[transactionDays.get(i)[1]];
                double firstProfit = calcProfit(firstBuyPrice, firstSellPrice, START_BUDGET);
                for (int j = i + 1; j < transactionDays.size(); j++) {
                    int secondBuyPrice = prices[transactionDays.get(j)[0]];
                    int secondSellPrice = prices[transactionDays.get(j)[1]];
                    double secondProfit = calcProfit(secondBuyPrice, secondSellPrice, firstProfit);
                    if (maxProfit < firstProfit + secondProfit) {
                        maxProfit = firstProfit + secondProfit;
                        buyDay1 = transactionDays.get(i)[0] + 1;
                        sellDay1 = transactionDays.get(i)[1] + 1;
                        buyDay2 = transactionDays.get(j)[0] + 1;
                        sellDay2 = transactionDays.get(j)[1] + 1;
                    }
                }
            }
            System.out.println(2);
            System.out.println(buyDay1 + " " + sellDay1);
            System.out.println(buyDay2 + " " + sellDay2);
        }

        reader.close();
    }
}