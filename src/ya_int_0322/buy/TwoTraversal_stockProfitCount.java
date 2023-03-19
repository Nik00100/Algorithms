package ya_int_0322.buy;

/*Кузя изучает биографии самых известных трейдеров на бирже. Недавно Кузя прочитал историю о том, как, совершив всего четыре
сделки (две покупки и две продажи) акций на бирже, трейдер смог превратить один рубль в целое состояние.
Кузя лучше всех знает, что такой удачи не бывает, а значит у трейдера была инсайдерская информация о ценах на ближайшие дни.
«Да если бы у меня была инсайдерская информация, я бы еще больше денег за четыре сделки получил!» — заявил Кузя перед
своим преподавателем экономики. Преподаватель экономики не растерялся и выдал Кузе данные о ценах акций фирмы Тындекс за тот период.
Для простоты вычислений преподаватель предложил считать, что можно покупать и продавать любую дробную часть акции.
Например, если акция стоит 2 рубля, а у вас в наличии 3 рубля, то вы можете купить 1.5 акции.
Преподаватель ожидает, что Кузя вычислит наибольшее количество денег, которые можно получить из 1 рубля на старте,
совершив не более четырех сделок.
Кузя резко вспомнил о множестве других очень важных дел, поэтому он просит вас, как знатока большинства промокодов и акций в
супермаркетах, вычислить оптимальные дни для сделок на основе исторических данных.

Формат ввода
В первой строке расположено единственное целое число N(1≤N≤3⋅10^5) — количество дней в исторических данных.
Вторая строка содержит N целых чисел через пробел pi(1≤pi≤10^4) — цена одной акции Тындекса в рублях вi-й день.

Формат вывода
В первой строке выведите одно целое число K(0≤K≤2) — количество пар сделок «покупка — продажа» в оптимальной стратегии торговли,
приводящей к наибольшему количеству денег из 1 рубля на старте. Следующие K строк должны содержать по два целых числа через
пробел bi si(1≤bi<si≤N) — дни покупки и продажи акций в i-й паре сделок. Обратите внимание, что вы можете не совершать сделок
вовсе или совершить только одну пару сделок. Если вы считаете оптимальным совершить две пары сделок, то выведенные дни сделок
должны следовать в общем хронологическом порядке, то есть b1<s1<b2<s2.
Если оптимальных ответов, содержащих не более четырех сделок, несколько, выведите любой из оптимальных.

Пример 1
Ввод
6
1 4 2 3 3 5
Вывод
2
1 2
3 6
Пример 2
Ввод
5
10 5 5 7 6
Вывод
1
3 4
Пример 3
Ввод
3
3 2 2
Вывод
0

Примечания
Пояснение к первому тестовому примеру.
В данном примере Кузе выгодно провести
2 сделки:
купить в 1-й день одну акцию за 1 рубль, после чего продать её во 2-й день за 4 рубля;
купить в 3-й день две акции по 2 рубля каждую, после чего продать их в 6-й день по 5 рублей.
Итого Кузя из одного рубля сделал целых 10 рублей. Можно показать, что лучшего результата достичь нельзя.

Пояснение ко второму тестовому примеру.
В данном примере у Кузи есть два взаимоисключающих варианта покупки — купить
0.2 акции во 2-й или в 3-й день (стоимость акции 5 рублей, а у Кузи изначально всего 1 рубль).
Независимо от дня покупки Кузе выгодно продать купленные 0.2 акции в 4-й день при стоимости 7 рублей за акцию.
Итого из одного рубля Кузя сделает целых 1.4 рубля.
Кузя выбрал купить акцию в 3-й день и продать в 4-й.
Обратите внимание, что если вывести «2 4» вместо «3 4», то ответ будет засчитан как верный
(так как итоговое количество денег будет одинаково оптимальным).

Пояснение ко третьему тестовому примеру.
Единственный вариант, не приводящий к потере денег — это купить акции в день 2 и продать в день 3.
Но данная сделка не принесёт никакой выгоды (цены акций равны), поэтому Кузя решает не делать никаких сделок вовсе.
Обратите внимание, что если вывести одну сделку «2 3» вместо нуля сделок, то ответ будет засчитан как верный
(так как итоговое количество денег будет одинаково оптимальным).*/

import java.util.Scanner;

public class TwoTraversal_stockProfitCount {
    private static final double START_BUDGET = 1.0;

    static double calcProfit(int buyPrice, int sellPrice, double currentStartBudget) {
        return (currentStartBudget / buyPrice) * sellPrice;
    }
    static int maxProfitForTwoTransactionPartitionDay(int[] prices) {
        int n = prices.length;
        if (n < 2) {
            return 0;
        }
        // forward traversal: find the maximum profit for one transaction ending at each day
        double[] maxProfits1 = new double[n];
        int minBuyPrice = prices[0];
        for (int i = 1; i < n; i++) {
            maxProfits1[i] = Math.max(maxProfits1[i-1], calcProfit(minBuyPrice, prices[i], START_BUDGET));
            minBuyPrice = Math.min(minBuyPrice, prices[i]);
        }
        // backward traversal: find the maximum profit for one transaction starting at each day
        double[] maxProfits2 = new double[n];
        int maxSellPrice = prices[n-1];
        for (int i = n-2; i >= 0; i--) {
            maxProfits2[i] = Math.max(maxProfits2[i+1], calcProfit(prices[i], maxSellPrice, maxProfits1[i]));
            maxSellPrice = Math.max(maxSellPrice, prices[i]);
        }
        // find the maximum profit for two transactions
        double maxProfit = 0;
        int maxProfitPartitionDay = -1;
        for (int i = 0; i < n; i++) {
            if (maxProfit < maxProfits1[i] + maxProfits2[i]) {
                maxProfit = maxProfits1[i] + maxProfits2[i];
                maxProfitPartitionDay = i;
            }
        }

        //System.out.println(Arrays.stream(maxProfits1).boxed().toList());
        //System.out.println(Arrays.stream(maxProfits2).boxed().toList());

        return maxProfitPartitionDay;
    }

    // find days for one transaction
    static Transaction findBuySellDates(int[] prices, int startSearchDay, int endSearchDay, double currentStartBudget) {
        if (startSearchDay == endSearchDay) {
            return null; // no profit can be obtained
        }

        double maxProfit = 0;
        int buyDate = 0;
        int sellDate = 0;

        int i = startSearchDay;
        while (i < endSearchDay) {
            while (i < endSearchDay && prices[i + 1] <= prices[i]) i++;
            int curBuyDate = i;

            while (i < endSearchDay && prices[i + 1] >= prices[i]) i++;
            int curSellDate = i;

            if (calcProfit(prices[curBuyDate], prices[curSellDate], currentStartBudget) > maxProfit) {
                maxProfit = calcProfit(prices[curBuyDate], prices[curSellDate], currentStartBudget);
                buyDate = curBuyDate; // update buy date
                sellDate = curSellDate; // update sell date
            }
        }

        //System.out.println(maxProfit);

        if (maxProfit == currentStartBudget) {
            return null; // no profit can be obtained
        } else {
            return new Transaction(buyDate, sellDate, maxProfit); // return buy, sell dates and profit
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextInt();
        }

        int partitionDay = maxProfitForTwoTransactionPartitionDay(prices);

        //System.out.println(partitionDay);

        Transaction transaction1 = findBuySellDates(prices, 0, partitionDay, START_BUDGET);
        if (partitionDay >=0 && transaction1 != null) {
            System.out.println(transaction1);
            Transaction transaction2 = findBuySellDates(prices, partitionDay, prices.length - 1, transaction1.profit);
            if (transaction2 != null)
                System.out.println(transaction2);
        } else {
            System.out.println("0");
        }

        sc.close();
    }

    static class Transaction {
        int buyDate;
        int sellDate;
        double profit;

        public Transaction(int buyDate, int sellDate, double profit) {
            this.buyDate = buyDate;
            this.sellDate = sellDate;
            this.profit = profit;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "buyDate=" + buyDate +
                    ", sellDate=" + sellDate +
                    ", profit=" + profit +
                    '}';
        }
    }
}

