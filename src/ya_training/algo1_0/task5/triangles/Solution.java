package ya_training.algo1_0.task5.triangles;

/*Петя достаточно давно занимается в математическом кружке, поэтому он уже успел не только правила выполнения простейших операций,
но и такое достаточно сложное понятие как симметрия. Для того, чтобы получше изучить симметрию Петя решил начать с наиболее
простых геометрических фигур – треугольников. Он скоро понял, что осевой симметрией обладают так называемые равнобедренные
треугольники. Поэтому теперь Петя ищет везде такие треугольники.

Напомним, что треугольник называется равнобедренным, если его площадь положительна, и у него есть хотя бы две равные стороны.

Недавно Петя, зайдя в класс, увидел, что на доске нарисовано n точек. Разумеется, он сразу задумался, сколько существует троек
из этих точек, которые являются вершинами равнобедренных треугольников.

Требуется написать программу, решающую указанную задачу.

Формат ввода
Входной файл содержит целое число n (3 ≤ n ≤ 1500). Каждая из последующих строк содержит по два целых числа – xi и yi – координаты
i-ой точки. Координаты точек не превосходят 109 по абсолютной величине. Среди заданных точек нет совпадающих.

Формат вывода
В выходной файл выведите ответ на задачу.

Пример 1
Ввод
3
0 0
2 2
-2 2
Вывод
1
Пример 2
Ввод
4
0 0
1 1
1 0
0 1
Вывод
4*/
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    static class P {
        long x;
        long y;

        public P(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "P{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            P p = (P) o;
            return x == p.x && y == p.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        P[] p = new P[n];
        for (int i = 0; i < n; i++) {
            String[] sN = reader.readLine().split(" ");
            p[i] = new P(Integer.parseInt(sN[0]), Integer.parseInt(sN[1]));
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            Set<P> used = new HashSet<>();
            List<Long> neig = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    long x = p[i].x - p[j].x;
                    long y = p[i].y - p[j].y;
                    long length = x*x + y*y;
                    neig.add(length);
                    if (used.contains(new P(x,y))) {
                        ans--;
                    }
                    used.add(new P(-x,-y));
                }
            }
            Collections.sort(neig);
            //System.out.println(neig.stream().collect(Collectors.toList()));
            int r =0;
            for (int l=0; l<neig.size(); l++) {
                while (r<neig.size() && neig.get(r).equals(neig.get(l))) {
                    r++;
                }
                ans += r - l - 1;
            }

        }

        System.out.println(ans);
        reader.close();
    }
}
