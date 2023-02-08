package ya_training.algo1_0.task7.segments;

/*Дано n отрезков на числовой прямой и m точек на этой же прямой. Для каждой из данных точек определите, скольким отрезкам
они принадлежат. Точка x считается принадлежащей отрезку с концами a и b, если выполняется двойное неравенство
min(a, b) ≤ x ≤ max(a, b).

Формат ввода
Первая строка содержит два целых числа n (1≤ n ≤10^5) – число отрезков и m (1≤ m ≤10^5) – число точек.
В следующих n строках по два целых числи ai и bi – координаты концов соответствующего отрезка.
В последней строке m целых чисел – координаты точек. Все числа по модулю не превосходят 10^9

Формат вывода
В выходной файл выведите m чисел – для каждой точки количество отрезков, в которых она содержится.

Пример
Ввод
3 2
0 5
-3 2
7 10
1 6

Вывод
2 0 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    enum Status {
        START(-1),
        END(2000_000_000);

        private int num;

        Status(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }
    }

    static class Point {
        int number;
        int status;

        long count;

        public Point(int number, int status) {
            this.number = number;
            this.status = status;
        }

        @Override
        public String toString() {
            return "{" + number + ", " + status + "}";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input7.txt")));
        String[] nk = reader.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int m = Integer.parseInt(nk[1]);

        Point[] points = new Point[2 * n + m];
        for (int i = 0; i < n; i++) {
            String[] interval = reader.readLine().split(" ");
            int a = Integer.parseInt(interval[0]);
            int b = Integer.parseInt(interval[1]);
            points[2 * i] = new Point(Math.min(a,b), Status.START.getNum());
            points[2 * i + 1] = new Point(Math.max(a,b), Status.END.getNum());
        }

        String[] p = reader.readLine().split(" ");

        Point[] pointsAns = new Point[m];
        for (int i = 0; i < m; i++) {
            Point point = new Point(Integer.parseInt(p[i]), i);
            points[2 * n + i] = point;
            pointsAns[i] = point;
        }

        Arrays.sort(points, (a, b) -> a.number == b.number ? a.status - b.status : a.number - b.number);

        long count = 0; // количество открытых отрезков

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 2 * n + m; i++) {
            Point point = points[i];

            if (point.status == Status.START.getNum()) {
                count++;
            } else if (point.status == Status.END.getNum()) {
                count--;
            } else {
                point.count = count;
            }
        }


        for (int i = 0; i < m; i++) {
            sb.append(pointsAns[i].count).append(" ");
        }

        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());

        reader.close();
    }
}
