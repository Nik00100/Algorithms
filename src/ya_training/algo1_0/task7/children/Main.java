package ya_training.algo1_0.task7.children;

/*Организаторы детского праздника планируют надуть для него M воздушных шариков. С этой целью они пригласили N добровольных
помощников, i-й среди которых надувает шарик за Ti минут, однако каждый раз после надувания Zi шариков устает и отдыхает Yi минут.
Теперь организаторы праздника хотят узнать, через какое время будут надуты все шарики при наиболее оптимальной работе помощников,
и сколько шариков надует каждый из них. (Если помощник надул шарик, и должен отдохнуть, но больше шариков ему надувать не придется,
то считается, что он закончил работу сразу после окончания надувания последнего шарика, а не после отдыха).

Формат ввода
В первой строке входных данных задаются числа M и N (0 ≤ M ≤15000, 1 ≤N ≤ 1000). Следующие N строк содержат
по три целых числа - Ti, Zi и Yi соответственно (1 ≤Ti, Yi ≤100, 1 ≤Zi ≤ 1000).

Формат вывода
Выведите в первой строке число T - время, за которое будут надуты все шарики. Во второй строке выведите N чисел - количество шариков,
надутых каждым из приглашенных помощников. Разделяйте числа пробелами. Если распределений шариков несколько, выведите любое из них.

Пример 1
Ввод
1 2
2 1 1
1 1 2
Вывод
1
0 1
Пример 2
Ввод
2 2
1 1 1
1 1 1
Вывод
1
1 1*/

import java.io.*;
import java.util.*;

public class Main {

    static class Helper {
        int totalTime;
        int timeForOneBall;
        int id;
        public Helper(int timeForOneBall, int id) {
            this.totalTime = 0;
            this.timeForOneBall = timeForOneBall;
            this.id = id;
        }
        @Override
        public String toString() {
            return "{" + "totalTime=" + totalTime + ", timeForOneBall=" + timeForOneBall + ", id=" + id + '}';
        }
    }

    static class FinishBallEvent {
        int tEnd;
        int id;
        public FinishBallEvent (int tEnd, int id) {
            this.tEnd = tEnd;
            this.id = id;
        }
        @Override
        public String toString() {
            return "{" + "tEnd=" + tEnd + ", id=" + id + '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input8.txt")));
        String[] mn = reader.readLine().split(" ");

        int m = Integer.parseInt(mn[0]);
        int n = Integer.parseInt(mn[1]);

        int[] ball = new int[n]; // после какого шара следует отдых (timeout) для каждого помощника
        int[] rest = new int[n]; // длительность таймаута для каждого помощника
        int[] count = new int[n]; // количество надутых шаров каждым помощником
        int time = 0; // общее время, затраченное на m шаров помощниками

        if (m != 0) { // если шаров 0, то и времени требуется 0, иначе просчитываем
            // вначале считаем минимальное время требуемое для того, чтобы надуть все шары и максимальное
            // и заполняем список помощников
            int minTime = 0;
            int maxTime = 0;

            List<Helper> helpers = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String[] tzy = reader.readLine().split(" ");

                int t = Integer.parseInt(tzy[0]);
                int bCount = Integer.parseInt(tzy[1]);
                int timeout = Integer.parseInt(tzy[2]);

                helpers.add(new Helper(t, i));

                ball[i] = bCount;
                rest[i] = timeout;

                // просчитываем время для каждого помощника, если бы он надувал шары один
                int userTime = m * t + ((m/bCount) - (m % bCount == 0 ? 1 : 0)) * timeout;
                // вычисляем минимум и максимум
                if (minTime == 0) {
                    minTime = userTime;
                } else {
                    minTime = Math.min(minTime, userTime);
                }
                maxTime = Math.max(maxTime, userTime);

            }

            // если минимальное и максимальное время для накачки шаров равно, то распределяем шары между помощниками
            if (m == 1 || minTime == maxTime) {
                for (int i=0; i<m; i++) {
                    // отсортировали помощников по скорости надувания
                    Collections.sort(helpers, (a, b) -> a.totalTime == b.totalTime ? a.timeForOneBall - b.timeForOneBall : a.totalTime - b.totalTime);
                    // выбираем помощника, который в данный момент свободен - это первый элемент в отсортированном списке
                    int index = 0;
                    int id = helpers.get(index).id;
                    // увеличиваем счетчик шаров этого помощника
                    count[id] += 1;
                    // увеличиваем время работы этого помощника
                    helpers.get(index).totalTime += helpers.get(index).timeForOneBall;
                    // обновляем общее время, которое требуется для того, чтобы надуть все шары
                    time = Math.max(time, helpers.get(index).totalTime);
                    // если помощник надул кол-во шаров после, которого следует отдых, добавляем timeout к времени работы этого помощника
                    if (count[id] % ball[id] == 0) {
                        helpers.get(index).totalTime += rest[id];
                    }
                }
            // если минимальное и максимальное время для накачки шаров не равно
            } else {
                // создадим список, когда будет надут очередной шарик, для каждого помощника
                List<FinishBallEvent> arrEnd = new ArrayList<>();
                // перебираем
                for (Helper helper : helpers) {
                    // первоначальная инициализация
                    int balls = 0; // количество шаров, надутых помощником
                    int tEnd = 0; // общее время работы помощника
                    // индекс и время надутия одного шара данного помощника
                    int id = helper.id;
                    int timeForOneBall = helper.timeForOneBall;
                    // пока не достигли минимального общего времени и общего количества шаров
                    while (tEnd < minTime && balls < m) {
                        // обновили время, когда помощник надует очередной шар и добавили в список это событие
                        tEnd += timeForOneBall;
                        arrEnd.add(new FinishBallEvent(tEnd, id));
                        // обновили количество шаров и время, если требуется отдых
                        balls += 1;
                        if (balls % ball[id] == 0) {
                            tEnd += rest[id];
                        }
                    }
                }

                // Отсортировали события по времени
                Collections.sort(arrEnd, (a, b) -> a.tEnd - b.tEnd);

                // обновляем счетчики шаров для каждого участника и обновляем общее время (по событию),
                // если все шары надуты
                int balls = 0;
                for (FinishBallEvent event : arrEnd) {
                    balls += 1;
                    count[event.id] += 1;
                    if (balls == m) {
                        time = event.tEnd;
                        break;
                    }
                }
            }
        }

        // вывод
        System.out.println(time);
        StringBuilder sb = new StringBuilder();
        for (int num : count) {
            sb.append(num).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());

        reader.close();
    }
}
