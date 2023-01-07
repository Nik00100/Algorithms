package tink.task2Currency;

import java.io.*;
import java.util.*;

public class Solution {
    // Идея заключается в том, что я получаю приведенный общий баланс для трех счетов, используя НОК.
    // Далее, используя bruteforce, перебираю все возможные варианты
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] stringWorth = reader.readLine().split(" ");
        int[] worth = new int[stringWorth.length];
        worth[0] = Integer.parseInt(stringWorth[0]);
        int lcm = worth[0];
        for (int i = 0; i < stringWorth.length; i++) {
            worth[i] = Integer.parseInt(stringWorth[i]);
            lcm = LCM(lcm, worth[i]);
        }

        String[] stringCurrency = reader.readLine().split(" ");

        int[] currency = new int[stringCurrency.length];

        for (int i = 0; i < stringCurrency.length; i++) {
            currency[i] = Integer.parseInt(stringCurrency[i]);
        }

        int balance = getBalance(lcm, currency, worth);

        System.out.println(threeSum(balance, worth, lcm).size());
    }

    private static List<List<Integer>> threeSum(int balance, int[] worth, int lcm) {
        int[] nums = new int[balance + 1];
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[i] = i;
        }
        // результирующий список всех возможных вариантов обмена валюты на счетах
        List<List<Integer>> triplets = new ArrayList<>();
        int a = worth[0];
        int b = worth[1];
        int c = worth[2];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j)
                for (int k = 0; k < n; ++k) {
                    int x = nums[i];
                    int y = nums[j];
                    int z = nums[k];

                    if (x % (lcm / a) == 0 && y % (lcm / b) == 0 && z % (lcm / c) == 0 && x + y + z == balance)
                        triplets.add(Arrays.asList(x / (lcm / a), y / (lcm / b), z / (lcm / c)));
                }
        }
        return triplets;
    }

    // НОК
    private static int LCM(int x, int y) {
        int a = x;
        int b = y;
        while (y != 0) {
            int tmp = y;
            y = x % y;
            x = tmp;
        }
        return Math.abs(a * b) / x;
    }

    // "приведенный" общий баланс с учетом НОК
    private static int getBalance(int lcm, int[] currency, int[] worth) {
        int res = 0;
        for (int i = 0; i < currency.length; i++) {
            res += currency[i] * (lcm / worth[i]);
        }
        return res;
    }

}
