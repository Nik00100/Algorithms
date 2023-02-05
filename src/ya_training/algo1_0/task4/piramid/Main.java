package ya_training.algo1_0.task4.piramid;

/*Для строительства двумерной пирамиды используются прямоугольные блоки, каждый из которых характеризуется шириной и высотой.
Можно поставить один блок на другой, только если ширина верхнего блока строго меньше ширины нижнего (блоки нельзя поворачивать).
Самым нижним в пирамиде может быть блок любой ширины.
По заданному набору блоков определите, пирамиду какой наибольшей высоты можно построить из них.

Формат ввода
В первой строке входных данных задается число N — количество блоков (1≤N≤100 000). В следующих N строках задаются пары
натуральных чисел wi и hi (1≤wi,hi≤10^9) — ширина и высота блока соответственно.

Формат вывода
Выведите одно целое число — максимальную высоту пирамиды.

Пример
Ввод
3
3 1
2 2
3 3
Вывод
5
Примечания
В примере пирамида будет состоять из двух блоков: нижним блоком будет блок номер 3, а верхним — блок номер 2.
Блок номер 1 нельзя использовать вместе с блоком номер 3.*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer,Integer> map = new HashMap<>();
        int n = Integer.parseInt(reader.readLine());
        for (int i=0; i<n; i++) {
            String[] s = reader.readLine().split(" ");
            int width = Integer.parseInt(s[0]);
            int height = Integer.parseInt(s[1]);
            int currHeight = map.getOrDefault(width,0);
            if (height > currHeight) map.put(width,height);
        }

        long totalHeight = 0;
        for (Integer h : map.values()) {
            totalHeight += h;
        }

        System.out.println(totalHeight);

        reader.close();
    }

}
