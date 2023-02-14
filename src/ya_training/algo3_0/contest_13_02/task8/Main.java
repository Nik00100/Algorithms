package ya_training.algo3_0.contest_13_02.task8;

/*На клетчатой плоскости закрашено K клеток. Требуется найти минимальный по площади прямоугольник, со сторонами, параллельными
линиям сетки, покрывающий все закрашенные клетки.

Формат ввода
Во входном файле, на первой строке, находится число K (1 ≤ K ≤ 100). На следующих K строках находятся пары чисел Xi и Yi –
координаты закрашенных клеток (|Xi|, |Yi| ≤ 10^9).

Формат вывода
Выведите в выходной файл координаты левого нижнего и правого верхнего углов прямоугольника.

Пример
Ввод
3
1 1
1 10
5 5

Вывод
1 1 5 10*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        int[] X = new int[n];
        int[] Y = new int[n];
        for (int i=0; i<n; i++) {
            String[] xy = reader.readLine().split(" ");
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);
            X[i] = x;
            Y[i] = y;
        }

        Arrays.sort(X);
        Arrays.sort(Y);

        int[] answer = new int[4];
        answer[0] = X[0];
        answer[1] = Y[0];
        answer[2] = X[n - 1];
        answer[3] = Y[n - 1];

        StringBuilder sb = new StringBuilder();
        for (int num : answer)
            sb.append(num).append(" ");
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());

        reader.close();
    }
}
