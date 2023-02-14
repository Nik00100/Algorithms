package ya_training.algo3_0.contest_13_02.task9;

/*Вам необходимо ответить на запросы узнать сумму всех элементов числовой матрицы N×M в прямоугольнике с
левым верхним углом (x1, y1) и правым нижним (x2, y2)

Формат ввода
В первой строке находится числа N, M размеры матрицы (1 ≤ N, M ≤ 1000) и K — количество запросов (1 ≤ K ≤ 100000).
Каждая из следующих N строк содержит по M чисел`— элементы соответствующей строки матрицы (по модулю не превосходят 1000).
Последующие K строк содержат по 4 целых числа, разделенных пробелом x1 y1 x2 y2 — запрос на сумму элементов матрице в
прямоугольнике (1 ≤ x1 ≤ x2 ≤ N, 1 ≤ y1 ≤ y2 ≤ M)

Формат вывода
Для каждого запроса на отдельной строке выведите его результат — сумму всех чисел в элементов матрице в
прямоугольнике (x1, y1), (x2, y2)

Пример
Ввод
3 3 2
1 2 3
4 5 6
7 8 9
2 2 3 3
1 1 2 3

Вывод
28
21*/

import java.io.*;

public class Main {
    static int[][] summedAreaTable;
    static void fillSAT(int i, int j, int value) {
        if (j > 0)
            value += summedAreaTable[i][j - 1];
        if (i > 0)
            value += summedAreaTable[i - 1][j];
        if (i > 0 && j > 0)
            value -= summedAreaTable[i - 1][j - 1];
        summedAreaTable[i][j] = value;
    }

    static int findSum(int x1, int y1, int x2, int y2) {
        int A = 0, B = 0, C = 0, D = 0;
        if (x1 > 0 && y1 > 0)
            A = summedAreaTable[x1 - 1][y1 - 1];
        if (y1 > 0)
            B = summedAreaTable[x2][y1 - 1];
        if (x1 > 0)
            C = summedAreaTable[x1 - 1][y2];
        D = summedAreaTable[x2][y2];

        return A - B - C + D;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input8.txt")));

        String[] nmk = reader.readLine().split(" ");
        int n = Integer.parseInt(nmk[0]);
        int m = Integer.parseInt(nmk[1]);
        int k = Integer.parseInt(nmk[2]);

        summedAreaTable = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] s = reader.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                int value = Integer.parseInt(s[j]);
                fillSAT(i, j, value);
            }
        }

        for (int i = 0; i < k; i++) {
            String[] s = reader.readLine().split(" ");
            int x1 = Integer.parseInt(s[0]) - 1;
            int y1 = Integer.parseInt(s[1]) - 1;
            int x2 = Integer.parseInt(s[2]) - 1;
            int y2 = Integer.parseInt(s[3]) - 1;
            System.out.println(findSum(x1, y1, x2, y2));
        }

        reader.close();
    }
}
