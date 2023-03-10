package ya_training.algo3_0.task5_01_03.divA.task3;

/*33. Радио Байтик

Как известно, при распространении радиоволн возникает интерференция, поэтому если рядом расположены две радиопередающие станции,
вещающие на одной и той же частоте, то качество радиопередач резко снижается.

Радиостанция «Байтик» планирует транслировать свои программы в стране Флатландия. Министерство связи Флатландии выдало радиостанции
лицензию на вещание на двух различных частотах.

Владельцы радиостанции имеют возможность транслировать свои радиопрограммы с использованием N радиовышек, расположенных в различных
 точках страны. Для осуществления трансляции на каждой радиовышке требуется установить специальный передатчик – трансмиттер.
 Каждый передатчик можно настроить на одну из двух частот, выделенных радиостанции. Кроме частоты вещания, передатчик
 характеризуется также своей мощностью. Чем мощнее передатчик, тем на большее расстояние он распространяет радиоволны.
 Для простоты, предположим, что передатчик мощности R распространяет радиоволны на расстояние, равное R километрам.

Все передатчики, установленные на вышках, должны, согласно инструкции министерства, иметь одну и ту же мощность. Чтобы программы
радиостанции могли приниматься на как можно большей территории, мощность передатчиков должна быть как можно большей. С другой
стороны, необходимо, чтобы прием передач был качественным на всей территории Флатландии. Прием передач считается качественным,
если не существует такого участка ненулевой площади, на который радиоволны радиостанции «Байтик» приходят на одной частоте
одновременно с двух вышек.

Требуется написать программу, которая определяет, какую максимальную мощность можно было установить на всех передатчиках,
позволяющую выбрать на каждом передатчике такую одну из двух частот передачи, чтобы прием был качественным на всей территории
Флатландии.

Формат ввода
Первая строка содержит число N — количество вышек (3 ≤ N ≤ 1200). Последующие N строк содержат по два целых числа — координаты
вышек. Координаты заданы в километрах и не превышают 104 по модулю. Все точки, в которых расположены вышки, различны.
Все числа в строках разделены пробелом.

Формат вывода
В первой строке выводится вещественное число — искомая мощность передатчиков. Во второй строке выводятся N чисел, где i-е
число должно быть равно 1, если соответствующий передатчик должен вещать на первой частоте, и 2, если на второй. Ответ
должен быть выведен с точностью, не меньшей 10–8.

Пример 1
Ввод	Вывод
4
0 0
0 1
1 0
1 1
0.707106781186548
1 2 2 1
Пример 2
Ввод	Вывод
3
0 0
0 1
0 2
1.000000000000000
1 2 1*/

import java.util.*;

public class Solution {

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int dist(Point a, Point b) {
        int dx = a.x - b.x;
        int dy = a.y - b.y;
        return dx * dx + dy * dy;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Point[] p = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            p[i] = new Point(x, y);
        }
        int left = 0;
        int right = 1000000000;
        int[] ansColor = new int[n];
        Arrays.fill(ansColor, 0);
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            final int UNDEF = 0;
            int[] color = new int[n];
            Arrays.fill(color, UNDEF);
            Deque<Integer> stack = new ArrayDeque<>();
            boolean good = true;
            for (int i = 0; i < n && good; i++) {
                if (color[i] == UNDEF) {
                    stack.push(i);
                    color[i] = 1;
                    while (!stack.isEmpty() && good) {
                        int cur = stack.removeLast();
                        for (int next = 0; next < n; next++) {
                            if (next != cur && dist(p[cur], p[next]) < mid) {
                                if (color[next] == 0) {
                                    color[next] = 3 - color[cur];
                                    stack.push(next);
                                } else if (color[next] == color[cur]) {
                                    good = false;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (good) {
                left = mid;
                ansColor = Arrays.copyOf(color, color.length);
            } else {
                right = mid;
            }
        }
        System.out.printf("%.9f\n", Math.sqrt(left) / 2);
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(ansColor[i]);
        }
        sc.close();
    }
}