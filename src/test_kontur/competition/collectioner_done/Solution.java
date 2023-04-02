package test_kontur.competition.collectioner_done;

/*B. Коллекционер
ограничение по времени на тест1 секунда
ограничение по памяти на тест256 мегабайт

Игнат увлекается коллекционированием настенных ковров. Недавно он смог освободить одну стену комнаты и теперь хочет повесить на эту
стену ковер наибольшей возможной площади.

В стену вбито N гвоздей. Ковер можно вешать только параллельно осям координат, на четыре гвоздя, расположенных по углам ковра.
Другие гвозди на стене разрешается накрывать ковром. Найдите максимальную площадь ковра, который можно повесить на эту стену.

Входные данные
В первой строке содержится целое число N — количество забитых гвоздей в стене (4≤N≤1500).
В каждой из следующих N строк содержатся целые числа xi и yi — координаты i-го гвоздя (−10^9≤xi,yi≤10^9). Гарантируется, что никакие два гвоздя не расположены в одной точке.

Выходные данные
Выведите максимальную площадь ковра, который можно разместить на стене. Если на стене невозможно разместить ни одного ковра, выведите 0.
Обратите внимание, что ответ задачи может не помещаться в 32-битный целый тип данных!

Система оценки
В этой задаче 3 группы тестов.
Первая группа тестов стоит 4 балла, для нее выполняется ограничение N≤30.
Вторая группа тестов стоит 3 балла, для нее выполняется ограничение N≤300.
Третья группа тестов стоит 3 балла, для нее выполняется ограничение N≤1500.

Примеры
входные данные
8
0 0
1 1
0 2
5 0
5 2
0 4
3 0
3 4
выходные данные
12

входные данные
4
1 -1
1 1
-1 1
1 0
выходные данные
0

Примечание
В первом тесте можно повесить ковер с площадью 12 на гвозди с координатами (0,0), (3,0), (0,4), (3,4) либо ковер с площадью 10
на гвозди с координатами (0,0), (5,0), (0,2), (5,2).*/

import java.util.*;
import java.io.*;

public class Solution {
    static class Pin {
        int x, y;
        public Pin(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Pin) {
                Pin other = (Pin) obj;
                return x == other.x && y == other.y;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Pin[] pins = new Pin[n];
        for (int i = 0; i < n; i++) {
            String[] line = reader.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            pins[i] = new Pin(x, y);
        }

        Set<Pin> seenPins = new HashSet<>();
        long maxArea = Long.MIN_VALUE;
        for (Pin pin1 : pins) {
            for (Pin pin2 : seenPins) {
                if (seenPins.contains(new Pin(pin1.x, pin2.y)) && seenPins.contains(new Pin(pin2.x, pin1.y))) {
                    long area = (long) Math.abs(pin1.x - pin2.x) * Math.abs(pin1.y - pin2.y);
                    if (area > 0 && area > maxArea) {
                        maxArea = area;
                    }
                }
            }
            seenPins.add(pin1);
        }

        if (maxArea == Long.MIN_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(maxArea);
        }

        reader.close();
    }
}
