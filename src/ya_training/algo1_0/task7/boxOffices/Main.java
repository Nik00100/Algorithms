package ya_training.algo1_0.task7.boxOffices;

/*На одном из московских вокзалов билеты продают N касс. Каждая касса работает без перерыва определенный промежуток времени по
фиксированному расписанию (одному и тому же каждый день). Требуется определить, на протяжении какого времени в течение суток
работают все кассы одновременно.

Формат ввода
Сначала вводится одно целое число N (0 < N ≤ 1000).

В каждой из следующих N строк через пробел расположены 4 целых числа, первые два из которых обозначают время открытия кассы в
 и минутах (часы — целое число от 0 до 23, минуты — целое число от 0 до 59), оставшиеся два — время закрытия в том же формате.
 Числа разделены пробелами.

Время открытия означает, что в соответствующую ему минуту касса уже работает, а время закрытия — что в соответствующую минуту
касса уже не работает. Например, касса, открытая с 10 ч. 30 мин. до 18 ч. 30 мин., ежесуточно работает 480 минут.

Если время открытия совпадает с временем закрытия, то касса работает круглосуточно. Если первое время больше второго, то касса
начинает работу до полуночи, а заканчивает — на следующий день.

Формат вывода
Требуется вывести одно число — суммарное время за сутки (в минутах), на протяжении которого работают все N касс.

Пример 1
Ввод
3
1 0 23 0
12 0 12 0
22 0 2 0
Вывод
120

Пример 2
Ввод
2
9 30 14 0
14 15 21 0
Вывод
0

Пример 3
Ввод
2
14 00 18 00
10 00 14 01
Вывод
1
Примечания
1) Первая касса работает с часу до 23 часов, вторая – круглосуточно, третья – с 22 часов до 2 часов ночи следующего дня.
Таким образом, все три кассы одновременно работают с 22 до 23 часов и с часу до двух часов, то есть 120 минут.

2) Первая касса работает до 14 часов, а вторая начинает работать в 14 часов 15 минут, то есть одновременно кассы не работают.

3) Вместе кассы работают лишь одну минуту – с 14:00 до 14:01 (в 14:01 вторая касса уже не работает).*/

import java.io.*;
import java.util.*;

public class Main {

    enum Type {
        START(-1),
        END(2000_000_000);

        private int num;

        Type(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }
    }

    static class Time {
        int time;
        int type;
        int id;

        public Time(int time, int type) {
            this.time = time;
            this.type = type;
            //this.id = id;
        }

        @Override
        public String toString() {
            return "{" + "time=" + time + ", type=" + type + '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        List<Time> times = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] t = reader.readLine().split(" ");
            int begin = 60 * Integer.parseInt(t[0]) + Integer.parseInt(t[1]);
            int end = 60 * Integer.parseInt(t[2]) + Integer.parseInt(t[3]);

            if (begin < end) {
                times.add(new Time(begin, Type.START.getNum()));
                times.add(new Time(end, Type.END.getNum()));
            } else {
                times.add(new Time(begin, Type.START.getNum()));
                times.add(new Time(24 * 60, Type.END.getNum()));
                times.add(new Time(0, Type.START.getNum()));
                times.add(new Time(end, Type.END.getNum()));
            }
        }

        Collections.sort(times, (a, b) -> a.time == b.time ? a.type - b.type : a.time - b.time);

        int ans = 0;
        int count = 0;
        int tBegin = 0;

        for (Time time : times) {
            if (time.type == Type.START.getNum()) {
                count += 1;
                if (n == count) {
                    tBegin = time.time;
                }
            } else if (time.type == Type.END.getNum()) {
                if (n == count) {
                    ans += time.time - tBegin;
                }
                count -= 1;
            }
        }

        System.out.println(ans);

        reader.close();
    }
}
