package ya_training.algo3_0.task4_27_02.task1;

/*В каждой клетке прямоугольной таблицы N×M записано некоторое число. Изначально игрок находится в левой верхней клетке.
За один ход ему разрешается перемещаться в соседнюю клетку либо вправо, либо вниз (влево и вверх перемещаться запрещено).
При проходе через клетку с игрока берут столько килограммов еды, какое число записано в этой клетке (еду берут также за первую
и последнюю клетки его пути). Требуется найти минимальный вес еды в килограммах, отдав которую игрок может попасть в
правый нижний угол.

Формат ввода
Вводятся два числа N и M — размеры таблицы (1≤N≤20, 1≤M≤20). Затем идет N строк по M чисел в каждой — размеры штрафов в
килограммах за прохождение через соответствующие клетки (числа от 0 до 100).
Формат вывода
Выведите минимальный вес еды в килограммах, отдав которую можно попасть в правый нижний угол.
Пример
Ввод	Вывод
5 5
1 1 1 1 1
3 100 100 100 100
1 1 1 1 1
2 2 2 2 1
1 1 1 1 1

11*/

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = reader.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        int[][] cost = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] line = reader.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                cost[i][j] = Integer.parseInt(line[j]);
            }
        }

        for (int i = 1; i < n; i++) {
            cost[i][0] += cost[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            cost[0][i] += cost[0][i - 1];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                cost[i][j] += Math.min(cost[i - 1][j], cost[i][j - 1]);
            }
        }

        System.out.println(cost[n - 1][m - 1]);

        reader.close();
    }
}