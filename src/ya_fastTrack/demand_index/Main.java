package ya_fastTrack.demand_index;

/*Индекс спроса
Представим запросы по доставке товаров точками на декартовой плоскости. Для удобства будем считать, что все точки имеют целочисленные координаты.

Даны n точек заказов за определенный период. Для определения очагов спроса нужно определить, какое наибольшее количество заказов попало в некоторый прямоугольник площади
s (со сторонами параллельными осям координат), и сами эти заказы.

Обратите внимание, у выбранного прямоугольника длины сторон не обязательно должны быть целыми, но каждая из них должна быть не менее 1.

Заказы, координаты которых попадают на границу прямоугольника, считаются попадающими в область.

Примеры

Формат ввода
В первой строке записаны два целых числа n и s (2≤n≤300, 1≤s≤10^6).
В каждой из следующих n строк записаны координаты одного из заказов xi, yi (0≤xi,yi≤2000).
Обратите внимание, что из одной точки может быть сделано несколько заказов.

Формат вывода
В первой строке выведите число k (1≤k≤n) — найденные заказы.
Во второй строке выведите k различных индексов i1…ik (1≤i1<…<ik≤n) — заказы, для которых окаймляющий прямоугольник имеет площадь не более
s (если сторона менее 1, то она будет увеличена до 1).

Если подходящих наборов заказов несколько (их ровно k и попадают в подходящую область), то вы можете вывести любой из таких наборов.

Пример 1
Ввод
5 1
0 0
0 2
2 0
1 1
2 2
Вывод
2
1 4
Пример 2
Ввод
5 2
0 0
0 2
2 0
1 1
2 2
Вывод
3
1 3 4
Пример 3
Ввод
5 4
0 0
0 2
2 0
1 1
2 2
Вывод
5
1 2 3 4 5
Пример 4
Ввод
2 1
0 0
2 0
Вывод
1
1*/

import java.util.*;

public class Main {
    public static List<Object> findMaxRect(List<List<Integer>> points, int s) {
        Set<Integer> xs = new HashSet<>();
        Set<Integer> ys = new HashSet<>();

        for (List<Integer> point : points) {
            xs.add(point.get(0));
            ys.add(point.get(1));
        }

        List<List<Integer>> maxRect = new ArrayList<>();
        int maxCount = -1;

        for (int x1 : xs) {
            for (int y1 : ys) {
                for (int x2 : xs) {
                    if (x2 < x1) continue;
                    for (int y2 : ys) {
                        if (y2 < y1) continue;

                        int count = 0;

                        for (List<Integer> point : points) {
                            if (x1 <= point.get(0) && point.get(0) <= x2 &&
                                    y1 <= point.get(1) && point.get(1) <= y2) {
                                count++;
                            }
                        }

                        if (count > maxCount) {
                            maxCount = count;
                            maxRect.clear();
                            maxRect.add(Arrays.asList(x1, y1));
                            maxRect.add(Arrays.asList(x2, y2));
                        }
                    }
                }
            }
        }

        List<List<Integer>> result = new ArrayList<>();

        for (List<Integer> point : points) {
            int x = point.get(0);
            int y = point.get(1);

            if (maxRect.get(0).get(0) <= x && x <= maxRect.get(1).get(0) &&
                    maxRect.get(0).get(1) <= y && y <= maxRect.get(1).get(1)) {
                result.add(point);
            }
        }

        List<Object> resultList = new ArrayList<>();
        resultList.add(maxRect);
        resultList.add(result);

        return resultList;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

    }

}
