package ya_training.algo1_0.task7.auditory;

/*Экзамен по берляндскому языку проходит в узкой и длинной аудитории. На экзамен пришло N студентов. Все они посажены в ряд.
Таким образом, позиция каждого человека задается координатой на оси Ox (эта ось ведет вдоль длинной аудитории).
Два человека могут разговаривать, если расстояние между ними меньше или равно D. Какое наименьшее количество типов билетов
должен подготовить преподаватель, чтобы никакие два студента с одинаковыми билетами не могли разговаривать? Выведите способ
раздачи преподавателем билетов.

Формат ввода
В первой строке входного файла содержится два целых числа N, D (1≤N≤10000; 0≤D≤10^6). Вторая строка содержит последовательность
различных целых чисел X1, X2, ..., XN, где Xi (0≤Xi≤10^6) обозначает координату вдоль оси Ox i-го студента

Формат вывода
В первую строчку выходного файла выведите количество вариантов, а во вторую, разделяя пробелами, номера вариантов студентов в
том порядке, в каком они перечислены во входном файле.

Пример 1
Ввод
4 1
11 1 12 2

Вывод
2
1 1 2 2


Пример 2
Ввод
4 0
11 1 12 2

Вывод
1
1 1 1 1 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static class Stud {
        int cord;
        int pos;
        int variant;
        public Stud(int cord, int pos) {
            this.cord = cord;
            this.pos = pos;
        }
        @Override
        public String toString() {return "{" + cord + ", " + pos + ", " + variant + "}";}
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] nd = reader.readLine().split(" ");
        int n = Integer.parseInt(nd[0]);
        int d = Integer.parseInt(nd[1]);

        int maxV = 1; // максимальный вариант (первоначальное значение)

        if (n == 1 || d == 0) {
            System.out.println(maxV);
            StringBuilder sb = new StringBuilder();
            for (int i =0; i < n; i++) {
                sb.append(maxV).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb.toString());
        } else {
            String[] x = reader.readLine().split(" ");
            Stud[] studsForSort = new Stud[n];
            Stud[] studsArrayInput = new Stud[n];
            for (int i = 0; i < n; i++) {
                Stud stud = new Stud(Integer.parseInt(x[i]), i);
                studsForSort[i] = stud;
                studsArrayInput[i] = stud;
            }

            Arrays.sort(studsForSort, (a, b)-> a.cord - b.cord);

            // сначала ищем макс вариант
            for (int i = 1; i < n; i++) {
                if (studsForSort[i].cord - studsForSort[i - maxV].cord <= d) {
                    maxV += 1;
                }
            }
            // раздадим варианты
            int curV = 1; // текущий вариант
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {

                Stud stud = studsForSort[i];
                stud.variant = curV;
                curV += 1;
                if (curV > maxV) {
                    curV = 1;
                }
            }

            //System.out.println(Arrays.stream(studsArrayInput).collect(Collectors.toList()));

            for (int i = 0; i < n; i++) {
                Stud stud = studsArrayInput[i];
                sb.append(stud.variant).append(" ");
            }

            sb.deleteCharAt(sb.length() - 1);
            System.out.println(maxV);
            System.out.println(sb.toString());

        }

        reader.close();
    }
}
