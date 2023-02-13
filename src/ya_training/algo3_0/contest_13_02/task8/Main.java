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

        String[] s = reader.readLine().split(" ");
        int n = s.length;



        reader.close();
    }
}
