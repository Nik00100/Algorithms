package ya_int_0322.buy.q;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    static int maxProfitqwqe(int[] prices) {

        System.out.println(Arrays.stream(prices).boxed().toList());

        int buy1 = Integer.MIN_VALUE, sell1 = 0, buy2 = Integer.MIN_VALUE, sell2 = 0;
        for (int price : prices) {
            buy1 = Math.max(buy1, -price);
            sell1 = Math.max(sell1, buy1 + price);
            buy2 = Math.max(buy2, sell1 - price);
            sell2 = Math.max(sell2, buy2 + price);
            System.out.println(buy1 +" "+sell1+" "+buy2+" "+sell2);
            System.out.println("************");
        }
        return sell2;
    }

    static int maxProfit(int[] prices) {
        int buy1 = Integer.MIN_VALUE, sell1 = 0, buy2 = Integer.MIN_VALUE, sell2 = 0;
        int buyDay1 = -1, sellDay1 = -1, buyDay2 = -1, sellDay2 = -1;
        for (int i = 0; i < prices.length; i++) {
            int price = prices[i];
            if (buy1 < -price) {
                buy1 = -price;
                buyDay1 = i;
            }
            if (sell1 < buy1 + price) {
                sell1 = buy1 + price;
                sellDay1 = i;
            }
            if (buy2 < sell1 - price) {
                buy2 = sell1 - price;
                buyDay2 = i;
            }
            if (sell2 < buy2 + price) {
                sell2 = buy2 + price;
                sellDay2 = i;
            }
        }

        System.out.println("Transaction 1: Buy day: " + buyDay1 + " Sell day: " + sellDay1);
        System.out.println("Transaction 2: Buy day: " + buyDay2 + " Sell day: " + sellDay2);
        System.out.println("Profit: " + (sell2));

        return sell2;
    }

    static int MaxProfitDpCompact1T(int[] prices) {
        if (prices.length == 0) return 0;
        var dp = new int[3][prices.length];
        var min = new int[3];
        Arrays.fill(min, prices[0]);
        for (int i = 1; i < prices.length; i++) {
            for (int k = 1; k <= 2; k++) {
                min[k] = Math.min(min[k], prices[i] - dp[k - 1][i - 1]);
                dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min[k]);
            }
        }

        System.out.println(Arrays.stream(min).boxed().toList());
        printMatrix(dp);

        return dp[2][prices.length - 1];
    }

    static void printMatrix(int[][] matrix) {
        Arrays.stream(matrix)
                .forEach(array -> System.out.println(Arrays.stream(array).boxed().collect(Collectors.toList())));
        System.out.println("*********************");
    }


    static int MaxProfitDpCompact2(int[] prices) {
        int buy1 = Integer.MIN_VALUE, sell1 = 0, buy2 = Integer.MIN_VALUE, sell2 = 0;
        int buyDay1 = -1, sellDay1 = -1, buyDay2 = -1, sellDay2 = -1;
        for (int i = 0; i < prices.length; i++) {
            int price = prices[i];
            if (buy1 < -price) {
                buy1 = -price;
                buyDay1 = i;
            }
            if (sell1 < buy1 + price) {
                sell1 = buy1 + price;
                //sellDay1 = i < buyDay2 || sellDay1 < 0 ? i : sellDay1;
                sellDay1 = i;
            }
            if (buy2 < sell1 - price) {
                buy2 = sell1 - price;
                buyDay2 = i;
            }
            if (sell2 < buy2 + price) {
                sell2 = buy2 + price;
                sellDay2 = i;
            }
        }

        System.out.println("Transaction 1: Buy day: " + buyDay1 + " Sell day: " + sellDay1);
        System.out.println("Transaction 2: Buy day: " + buyDay2 + " Sell day: " + sellDay2);
        System.out.println("Profit: " + (sell2));

        return sell2;
    }


    static  int maxProfit(int k, int[] prices) {
        if (k == 0) {
            return 0;
        }

        int[] buy = new int[k];
        int[] sell = new int[k];
        int[] buyDay = new int[k];
        int[] sellDay = new int[k];
        Arrays.fill(buy, Integer.MIN_VALUE);
        Arrays.fill(sell, 0);

        for (int i = 0; i < prices.length; i++) {
            int price = prices[i];
            buy[0] = Math.max(buy[0], -price);
            sell[0] = Math.max(sell[0], buy[0] + price);
            for (int j = 1; j < k; j++) {
                if (sell[j - 1] - price > buy[j]) {
                    buy[j] = sell[j - 1] - price;
                    buyDay[j] = i;
                }
                if (buy[j] + price > sell[j]) {
                    sell[j] = buy[j] + price;
                    sellDay[j] = i;
                }
            }
        }

        System.out.println(Arrays.stream(buyDay).boxed().toList());
        System.out.println(Arrays.stream(sellDay).boxed().toList());

        // Print the buy and sell days for each transaction
        for (int i = 0; i < k; i++) {
            System.out.println("Transaction " + (i + 1) + ": buy on day " + buyDay[i] + ", sell on day " + sellDay[i]);
        }

        return sell[k - 1];
    }

    static List<int[]> Profit(int k, int[] prices) {
        List<int[]> transactions = new ArrayList<>();
        if (k >= prices.length / 2) {
            int sell = 0;
            int hold = Integer.MIN_VALUE;
            int buyDay = -1;
            for (int i = 0; i < prices.length; i++) {
                if (hold == Integer.MIN_VALUE && i < prices.length - 1 && prices[i] < prices[i + 1]) {
                    buyDay = i;
                }
                if (hold != Integer.MIN_VALUE && (i == prices.length - 1 || prices[i] > prices[i + 1])) {
                    transactions.add(new int[]{buyDay, i});
                    buyDay = -1;
                    hold = Integer.MIN_VALUE;
                }
                sell = Math.max(sell, hold + prices[i]);
                hold = Math.max(hold, sell - prices[i]);
            }
            return transactions;
        }

        int[] sell = new int[k + 1];
        int[] hold = new int[k + 1];
        int[] buyDay = new int[k + 1];
        Arrays.fill(hold, Integer.MIN_VALUE);

        for (int i = 0; i < prices.length; i++)
            for (int j = k; j > 0; j--) {
                if (sell[j - 1] - prices[i] > hold[j]) {
                    hold[j] = sell[j - 1] - prices[i];
                    buyDay[j] = i;
                }
                if (sell[j] < hold[j] + prices[i]) {
                    sell[j] = hold[j] + prices[i];
                    transactions.add(new int[]{buyDay[j], i});
                }
            }

        return transactions;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{10, 11, 1, 15, 1, 2, 3, 4, 5, 6};
        //maxProfit(prices);
        System.out.println("****************");
        int[] prices1 = new int[]{1, 4, 2, 3, 3, 5};
        //maxProfit(prices1);
        System.out.println("****************");
        int[] prices2 = new int[]{10, 5, 5, 7, 7, 5};
        //maxProfit(prices2);
        System.out.println("****************");
        int[] prices3 = new int[]{3, 2, 2, 1, 1, 1};
        //maxProfit(prices3);
        System.out.println("****************");

        //System.out.println(Profit(2, prices).stream().map(a->"{"+a[0]+" "+a[1]+"}").toList());

        //System.out.println(Arrays.stream(maxProfitss(prices)).boxed().toList());
        MaxProfitDpCompact1T(prices);
    }


}