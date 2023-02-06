package ya_training.algo1_0.task7.desks;

/*На первом курсе одной Школы, учится 1 ≤ N ≤ 109 студентов. При проведении экзаменов студентов рассаживают в ряд,
каждого за своей партой. Парты пронумерованы числами от 0 до N - 1.

Известно, что студент, оставшись без наблюдения, открывает телефон и начинает искать ответы на экзамен в поисковике Яндекса.

Поэтому было решено позвать M преподавателей наблюдать за студентами. Когда за студентом наблюдает хотя бы один преподаватель,
он стесняется и не идет искать ответы к экзамену. Преподаватель с номером i видит студентов сидящих за партами от bi до ei
включительно.

Необходимо посчитать количество студентов, которые все таки будут искать ответы к экзамену в Яндексе

Формат ввода
В первой строке находятся два целых числа 1 ≤ N ≤ 10^9, 1 ≤ M ≤ 10^4 — число студентов и число преподавателей соответственно.
В следующих M строках содержится по два целых числа 0 ≤ bi ≤ ei ≤ N - 1 — парты, за которыми наблюдает i-й преподаватель.

Формат вывода
Выведите одно число — количество студентов оставшихся без наблюдения.

Пример 1
Ввод	Вывод
10 3
1 3
2 4
9 9

5
Пример 2
Ввод	Вывод
10 2
1 1
1 2

8*/

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    enum Status {
        WATCH,
        NOT_WATCH
    }

    static class Desk {
        int number;
        Status status;

        public Desk(int number, Status status) {
            this.number = number;
            this.status = status;
        }

        @Override
        public String toString() {
            return "Desk{" + number + ", " + status + '}';
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = reader.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int m = Integer.parseInt(nk[1]);

        Desk[] desks = new Desk[2*m];
        for (int i = 0; i < 2*m; i +=2) {
            String[] s = reader.readLine().split(" ");
            desks[i] = new Desk(Integer.parseInt(s[0]), Status.WATCH);
            desks[i + 1] = new Desk(Integer.parseInt(s[1]), Status.NOT_WATCH);
        }

        Arrays.sort(desks, (a, b) -> a.number == b.number ? a.status.ordinal() - b.status.ordinal() : a.number - b.number);
        //System.out.println(Arrays.stream(desks).collect(Collectors.toList()));

        int ans = 0; // за кем следят
        int countM = 0; // сколько в данный момент следят
        int b = 0; // начало
        int e = 0; // окончание

        for (int i = 0; i < 2*m; i++) {
            if (desks[i].status == Status.WATCH) {
                // начало отрезка
                countM += 1; // увеличиваем наблюдающих
                if (countM == 1) {
                    b = desks[i].number; // наблюдение только началось
                }
            } else {
                // окончание отрезка
                countM -= 1; // уменьшаем наблюдающих
                e = Math.max(e, desks[i].number);
                if (countM == 0) {
                    ans += e - b + 1;
                    e = 0;
                    b = 0;
                }
            }
        }

        System.out.println(n - ans);

        reader.close();
    }
}
