package ya_int_0322.buy.q;

public class Solution {
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

        System.out.println("Transaction 1:");
        System.out.println("Buy day: " + buyDay1);
        System.out.println("Sell day: " + sellDay1);
        System.out.println("Profit: " + (sell1 - buy1));

        System.out.println("Transaction 2:");
        System.out.println("Buy day: " + buyDay2);
        System.out.println("Sell day: " + sellDay2);
        System.out.println("Profit: " + (sell2 - buy2));

        return sell2;
    }

    public static void main(String[] args) {
        int[] prices = new int[] {10, 11, 1, 15, 1, 2, 3, 4, 5, 6};
        maxProfit(prices);
    }


}