package ya_int_0322.buy.test;

import java.util.*;

public class Main {
    static class Pair<T1, T2> {
        public T1 fst;
        public T2 snd;
        public Pair(T1 f, T2 s) {
            fst = f;
            snd = s;
        }

        @Override
        public String toString() {return "Pair{" + "fst=" + fst + ", snd=" + snd + '}';}
    }
    static Pair<Pair<Integer,Integer>, Double> calcProfit(int iBuy, int iSell, int[] prices, double start) {
        if (iBuy == iSell) {
            return null;
        } else {
            double profit = prices[iSell] * (start / prices[iBuy]);
            return new Pair<>(new Pair<>(iBuy + 1, iSell + 1), profit);
        }
    }

    // 2 - transactions
    static Pair<List<Pair<Integer, Integer>>, Double> buysAndSell(int[] prices) {
        int i = 0, iBuy1, iSell1, iBuy2, iSell2, N = prices.length - 1;
        double start = 1;

        List<Pair<Integer, Integer>> buysAndSells = new ArrayList<>();
        Pair<Pair<Integer, Integer>, Double> transaction1 = null;
        Pair<Pair<Integer, Integer>, Double> transaction2 = null;

        while (i < N) {
            while (i < N && prices[i + 1] <= prices[i]) i++;
            iBuy1 = i;

            while (i < N && prices[i + 1] >= prices[i]) i++;
            iSell1 = i;

            transaction1 = calcProfit(iBuy1, iSell1, prices, start);

            if (transaction1 == null) {
                break;
            }

            double startAfterTransaction1 = transaction1.snd;
            while (i < N && prices[i + 1] <= prices[i]) i++;
            iBuy2 = i;

            while (i < N && prices[i + 1] >= prices[i]) i++;
            iSell2 = i;

            transaction2 = calcProfit(iBuy2, iSell2, prices, startAfterTransaction1);

            System.out.println(transaction1 + " " + transaction2);
        }



        if (transaction1 != null) {
            Pair<Integer, Integer> pair1 = transaction1.fst;
            double startAfterTransaction1 = transaction1.snd;
            buysAndSells.add(pair1);

            if (transaction2 != null) {
                Pair<Integer, Integer> pair2 = transaction2.fst;
                double profit = transaction2.snd;
                buysAndSells.add(pair2);
                return new Pair<>(buysAndSells, profit);
            } else {
                return new Pair<>(buysAndSells, startAfterTransaction1);
            }
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextInt();
        }
        Pair<List<Pair<Integer, Integer>>, Double> ans = buysAndSell(prices);

        if (ans == null) {
            System.out.println(0);
        } else {
            System.out.println(ans.fst.size());
            for (Pair<Integer, Integer> pair : ans.fst) {
                System.out.println(pair.fst + " " + pair.snd);
            }
        }

        sc.close();
    }
}
