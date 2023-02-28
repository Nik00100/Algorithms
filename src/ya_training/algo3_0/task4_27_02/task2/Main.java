package ya_training.algo3_0.task4_27_02.task2;

/*В левом верхнем углу прямоугольной таблицы размером N×M находится черепашка. В каждой клетке таблицы записано некоторое число.
Черепашка может перемещаться вправо или вниз, при этом маршрут черепашки заканчивается в правом нижнем углу таблицы.
Подсчитаем сумму чисел, записанных в клетках, через которую проползла черепашка (включая начальную и конечную клетку).
Найдите наибольшее возможное значение этой суммы и маршрут, на котором достигается эта сумма.

Формат ввода
В первой строке входных данных записаны два натуральных числа N и M, не превосходящих 100 — размеры таблицы. Далее идет N строк,
каждая из которых содержит M чисел, разделенных пробелами — описание таблицы. Все числа в клетках таблицы целые и могут принимать
значения от 0 до 100.
Формат вывода
Первая строка выходных данных содержит максимальную возможную сумму, вторая — маршрут, на котором достигается эта сумма.
Маршрут выводится в виде последовательности, которая должна содержать N-1 букву D, означающую передвижение вниз и M-1 букву R,
означающую передвижение направо. Если таких последовательностей несколько, необходимо вывести ровно одну (любую) из них.
Пример
Ввод	Вывод
5 5
9 9 9 9 9
3 0 0 0 0
9 9 9 9 9
6 6 6 6 8
9 9 9 9 9

74
D D R R R R D D*/

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n, m;
        String[] line = reader.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);

        int[][] cost = new int[n][m];
        for (int i = 0; i < n; i++) {
            line = reader.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                cost[i][j] = Integer.parseInt(line[j]);
            }
        }

        for (int i = 1; i < m; i++) {
            cost[0][i] += cost[0][i - 1];
        }
        for (int i = 1; i < n; i++) {
            cost[i][0] += cost[i - 1][0];
            for (int j = 1; j < m; j++) {
                cost[i][j] += Math.max(cost[i - 1][j], cost[i][j - 1]);
            }
        }

        System.out.println(cost[n - 1][m - 1]);

        StringBuilder sb = new StringBuilder();
        int i = n - 1, j = m - 1;
        while (i > 0 || j > 0) {
            if (i == 0) {
                sb.append("R ");
                j--;
            } else if (j == 0) {
                sb.append("D ");
                i--;
            } else if (cost[i - 1][j] > cost[i][j - 1]) {
                sb.append("D ");
                i--;
            } else {
                sb.append("R ");
                j--;
            }
        }

        System.out.println(sb.reverse().toString().trim());
    }
}