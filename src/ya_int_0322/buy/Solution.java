package ya_int_0322.buy;

/*
Кузя изучает биографии самых известных трейдеров на бирже. Недавно Кузя прочитал историю о том, как, совершив всего четыре
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

import java.util.*;

public class Solution {
    static class Transaction {
        int buyDay;
        int sellDay;
        double profit;

        public Transaction(int buyDay, int sellDay, double profit) {
            this.buyDay = buyDay;
            this.sellDay = sellDay;
            this.profit = profit;
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
        return (currentStartBudget / buyPrice) * sellPrice;
    }

    static void solve (int[] prices, List<int[]> transactionDays) {
        if (prices == null || transactionDays.size() == 0) {
            System.out.println(0);
            return;
        }
        if (transactionDays.size() == 1) {
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
                int buyDay1 = transactionDays.get(i)[0] + 1;
                int sellDay1 = transactionDays.get(i)[1] + 1;
                double curProfit = calcProfit(prices[buyDay1 - 1], prices[sellDay1 - 1], START_BUDGET);
                if (maxProfit < curProfit) {
                    transactions[i] = new Transaction(buyDay1, sellDay1, curProfit);
                    maxProfit = curProfit;
                } else if (transactions[i - 1] != null) {
                    transactions[i] = transactions[i - 1];
                }
            }

            maxProfit = START_BUDGET;
            int buyDay1 = -1;
            int sellDay1 = -1;
            int buyDay2 = -1;
            int sellDay2 = -1;
            for (int i = length - 1; i > 0; i--) {
                int buyPrice = prices[transactionDays.get(i)[0]];
                int sellPrice = prices[transactionDays.get(i)[1]];
                double startBudget = transactions[i - 1].profit;
                if (calcProfit(buyPrice, sellPrice, startBudget) > maxProfit) {
                    maxProfit = calcProfit(buyPrice, sellPrice, startBudget);
                    buyDay1 = transactions[i - 1].buyDay;
                    sellDay1 = transactions[i - 1].sellDay;
                    buyDay2 = transactionDays.get(i)[0] + 1;
                    sellDay2 = transactionDays.get(i)[1] + 1;
                }
            }
            System.out.println(buyDay1 + " " + sellDay1);
            System.out.println(buyDay2 + " " + sellDay2);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = scanner.nextInt();
        }

        List<int[]> transactionDays = new ArrayList<>();

        getTransactions(prices, transactionDays);

        solve(prices, transactionDays);

        scanner.close();
    }
}
