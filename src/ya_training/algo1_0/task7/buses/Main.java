package ya_training.algo1_0.task7.buses;

/*Новый Президент Тридевятой республики начал свою деятельность с полной ревизии системы общественного транспорта страны.
В результате на основе социологических опросов населения было составлено идеальное ежедневное расписание движения междугородних
автобусов, утвержденное Парламентом республики.Более того, было решено заменить весь автобусный парк одинаковыми новыми,
очень дорогими, но гораздо более надежными, красивыми и удобными машинами.

Автобусная сеть страны охватывает N городов, занумерованных целыми числами от 1 до N. Идеальное расписание содержит M ежедневных
рейсов, i-й рейс начинается в городе Fi в момент времени Xi и заканчивается в некотором другом городе Gi в момент времени Yi.
Продолжительность каждого рейса ненулевая и строго меньше 24 часов. Рейс i выполняется одним из автобусов, находящихся в момент
времени Xi в городе Fi. Новые автобусы не требуют ремонта и могут работать круглосуточно, поэтому автобус, прибывший в некоторый
момент времени в некоторый город, всегда готов в тот же самый момент времени или позже отправиться в путь для обслуживания любого
другого рейса из данного города. Автобус может выехать из города, только выполняя какой-либо рейс из расписания.

Предполагается, что расписание будет действовать неограниченное время, поэтому может оказаться так, что его невозможно обслужить
никаким конечным числом автобусов.

Определите наименьшее количество новых автобусов, достаточное для обеспечения движения по расписанию в течение неограниченного
периода времени.

Формат ввода
В первой строке задаются целые числа N и М (1 ≤ N, M ≤ 100 000) — количество городов и рейсов автобусов соответственно.

В каждой из следующих M строк содержится описание рейса автобуса: номер города отправления Fi, время отправления Xi, номер города
назначения Gi (Fi ≠ Gi), время прибытия Yi, отделенные друг от друга одним пробелом. Время прибытия и отправления задается в формате
HH:MM, где HH — часы от 00 до 23, MM — минуты от 00 до 59.

Формат вывода
Выведите одно число — минимально необходимое количество автобусов. Если расписание невозможно обслуживать в течение неограниченного
периода времени конечным числом автобусов, выведите число -1.

Пример 1
Ввод
2 2
2 20:00 1 10:00
1 08:00 2 21:00
Вывод
3

Пример 2
Ввод
2 2
1 09:00 2 20:00
2 20:00 1 09:00
Вывод
1

Пример 3
Ввод
3 4
3 03:52 1 08:50
1 18:28 3 21:53
2 03:58 3 09:00
3 14:59 2 21:13
Вывод
2*/

// главная особенность - баланс автобусов в каждом городе в конце дня равен нулю

import java.io.*;
import java.util.*;

public class Main {

    enum Type {
        IN(-1),
        OUT(2000_000_000);
        private int num;
        Type(int num) {
            this.num = num;
        }
        public int getNum() {
            return num;
        }
    }

    static class Event {
        int time;
        Type type;
        int id;
        public Event(int time, Type type, int id) {
            this.time = time;
            this.type = type;
            this.id = id;
        }
        @Override
        public String toString() {return "{" + "time=" + time + ", type=" + type + ", id=" + id + '}';}
    }

    static int getMinutes (String hourMinute) {
        String[] hm = hourMinute.split(":");
        return Integer.parseInt(hm[0]) * 60 + Integer.parseInt(hm[1]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input8.txt")));
        String[] nm = reader.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        int[] cntBuses = new int[n + 1]; // счетчики автобусов в каждом городе
        int[] busBalance = new int[n + 1]; // баланс автобусов для каждого города
        int overMidnight = 0; // кол-во автобусов между городами в момент времени 00:00
        List<Event> events = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String[] route = reader.readLine().split(" ");
            int cityDep = Integer.parseInt(route[0]);
            int cityArr = Integer.parseInt(route[2]);
            int timeDep = getMinutes(route[1]);
            int timeArr = getMinutes(route[3]);

            if (timeArr < timeDep) {
                overMidnight++;
            }

            busBalance[cityDep]--;
            busBalance[cityArr]++;

            events.add(new Event(timeDep,Type.OUT,cityDep));
            events.add(new Event(timeArr,Type.IN,cityArr));
        }

        boolean flagDisbalance = false;
        for (int b : busBalance) {
            if (b != 0) {
                flagDisbalance = true;
                break;
            }
        }

        if (flagDisbalance) {
            System.out.println(-1);
        } else {
            Collections.sort(events, (a,b) -> a.time == b.time ? a.type.getNum() - b.type.getNum() : a.time - b.time);

            for (Event event : events) {
                if (event.type.equals(Type.IN)) { // если автобус приехал, счетчик увеличиваем
                    cntBuses[event.id]++;
                } else { // уменьшаем, если счетчик ненулевой
                    if (cntBuses[event.id] > 0)
                        cntBuses[event.id]--;
                }
            }

            System.out.println(Arrays.stream(cntBuses).sum() + overMidnight);
        }

        reader.close();
    }
}
