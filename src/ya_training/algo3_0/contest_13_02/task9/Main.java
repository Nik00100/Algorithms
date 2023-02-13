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
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static void printMatrix(int[][] matrix) {
        Arrays.stream(matrix)
                .forEach(array-> System.out.println(Arrays.stream(array).boxed().collect(Collectors.toList())));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] nmk = reader.readLine().split(" ");
        int n = Integer.parseInt(nmk[0]);
        int m = Integer.parseInt(nmk[1]);
        int k = Integer.parseInt(nmk[2]);

        int[][] matrix = new int[m][n];
        for (int i=0; i<m; i++) {
            String[] s = reader.readLine().split(" ");
            for (int j=0; j<n; j++) {
                matrix[i][j] = Integer.parseInt(s[j]);
            }
        }

        printMatrix(matrix);

        reader.close();
    }
}
