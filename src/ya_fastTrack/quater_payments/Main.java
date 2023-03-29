package ya_fastTrack.quater_payments;

/*Требуется реализовать метод оценки затрат по кварталам.

Дано n счетов за услуги в 2022 год. Каждый счет характеризуется тремя параметрами:
dateFromi, dateFinishi, amount – начало оказание услуги, последний день оказания услуги, размер оплаты.
Необходимо платежи разложить пропорционально дням внутри интервала оказания услуги и просуммировать по кварталам
(деление до копеек необходимо выполнять с округлением вниз, т.е. сумма по кварталам может быть меньше общей суммы по счетам).
Сначала необходимо определить сумму платежа за один день, а затем округленную сумму прибавить к каждому из дней квартала.

Обратите внимание: 2022 год – не високосный.

Формат ввода
В первой строке находится одно число n (1≤n≤10^5) – количество счетов.
В следующих n строках заданы описания счетов: amount (1≤amount≤10^6) в рублях, dateFromi, dateFinishi. Даты заданы в формате dd.mm (день, месяц).

Формат вывода
Выведите 4 числа, каждое в новой строке, ровно с 2 десятичным знаками – сумма платежей в 1-м, 2-м, 3-м и 4-м кварталах соответственно.

Пример 1
Ввод
4
10 10.02 11.05
10 11.12 23.12
100 24.05 30.06
4342 10.07 12.09
Вывод
5.00
104.04
4342.00
9.88

Пример 2
Ввод
1
1000000 01.01 31.12
Вывод
246574.80
249314.52
252054.24
252054.24
Пример 3
Ввод
2
1000 01.01 01.01
1234 30.06 01.07
Вывод
1000.00
617.00
617.00
0.00*/

import java.text.DecimalFormat;
import java.util.*;

public class Main {
    private final static int[] DAYS_IN_QUARTERS = {90, 181, 273, 365};
    private final static int[] DAYS_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static Map<Integer, Integer> daysBeforeCurrentMonth = new HashMap<>();
    private static int[] quarterPayments = new int[4];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        initMap();

        for (int i = 0; i < n; i++) {
            int amount = scanner.nextInt();

            String[] dates = scanner.next().split("\\.");
            int day = Integer.parseInt(dates[0]);
            int month = Integer.parseInt(dates[1]);
            int startPaymentQuarter = getQuarter(month);
            int dateFrom = getDays(day, month);

            dates = scanner.next().split("\\.");
            day = Integer.parseInt(dates[0]);
            month = Integer.parseInt(dates[1]);
            int finishPaymentQuarter = getQuarter(month);
            int dateFinish = getDays(day, month);

            int days = dateFinish - dateFrom + 1;
            //double paymentPerDay = Math.floor((double) amount / days * 100.0) / 100.0;
            int paymentPerDay = (int) Math.floor((double) amount / days * 100.0);

            fillPayments(startPaymentQuarter, dateFrom, finishPaymentQuarter, dateFinish, paymentPerDay);
        }

       /* for (int pay : quarterPayments) {
            double payment = (double) pay / 100.0;
            System.out.println(payment);
        }*/

        DecimalFormat df = new DecimalFormat("#0.00");
        for (int pay : quarterPayments) {
            double payment = (double) pay / 100.0;
            System.out.println(df.format(payment));
        }

    }

    private static void initMap() {
        daysBeforeCurrentMonth.put(1, 0);
        for (int i = 2; i <= 12; i++) {
            int sumOfDaysBeforeCurrentMonth = 0;
            sumOfDaysBeforeCurrentMonth = daysBeforeCurrentMonth.get(i - 1) + DAYS_IN_MONTH[i - 2];
            daysBeforeCurrentMonth.put(i, sumOfDaysBeforeCurrentMonth);
        }
    }

    private static int getDays(int day, int month) {
        return day + daysBeforeCurrentMonth.get(month);
    }

    private static int getQuarter(int month) {
        if (month < 4) {
            return 0;
        } else if (month < 7) {
            return 1;
        } else if (month < 10) {
            return 2;
        } else {
            return 3;
        }
    }

    static void fillPayments(int startPaymentQuarter, int dateFrom, int finishPaymentQuarter, int dateFinish, int paymentPerDay) {
        if (finishPaymentQuarter == startPaymentQuarter) {
            int daysForPayment = dateFinish - dateFrom + 1;
            quarterPayments[startPaymentQuarter] += daysForPayment * paymentPerDay;
        } else if (finishPaymentQuarter > startPaymentQuarter) {
            int daysInStartQuarter = DAYS_IN_QUARTERS[startPaymentQuarter] - dateFrom + 1;
            quarterPayments[startPaymentQuarter] += daysInStartQuarter * paymentPerDay;
            int daysInFinishQuarter = dateFinish - DAYS_IN_QUARTERS[finishPaymentQuarter - 1];
            quarterPayments[finishPaymentQuarter] += daysInFinishQuarter * paymentPerDay;

            int i = startPaymentQuarter + 1;
            while (i < finishPaymentQuarter) {
                quarterPayments[i] = (DAYS_IN_QUARTERS[i] - DAYS_IN_QUARTERS[i - 1]) * paymentPerDay;
                i++;
            }
        }
    }
}