package test_kontur.sandbox.children_square_done;

/*D. Детская площадка
ограничение по времени на тест1 секунда
ограничение по памяти на тест256 мегабайт

Дана схема городского квартала в виде прямоугольной таблицы размером n×m. Каждая ячейка таблицы или принадлежит какому-то зданию, или свободна. Все ячейки по границам квартала заняты зданиями.

Нужно разместить в этом квартале детскую площадку в виде прямоугольника, стороны которого параллельны границам квартала. Все ячейки, которые будут отданы под площадку, должны быть свободными. Если есть несколько вариантов размещения площадки, нужно выбрать такой, при котором она имеет наибольшую площадь (то есть занимает наибольшее число ячеек таблицы на схеме).

Входные данные
В первой строке записаны целые числа n и m — размеры таблицы (3≤n,m≤500). В следующих n строках по m символов в каждой записана таблица. Свободные ячейки обозначены в ней символами «.», а занятые — символами «*». Гарантируется, что первая и последняя строка таблицы, а также первый и последний столбец таблицы заполнены символами «*».

Выходные данные
Выведите наибольшую возможную площадь (количество ячеек) детской площадки. Если в квартале невозможно разместить детскую площадку, выведите «0».

Система оценки
В этой задаче три группы тестов.

Первая группа тестов стоит 4 балла, для неё выполняются ограничения n,m≤30.

Вторая группа тестов стоит 4 балла, для неё выполняются ограничения n,m≤100.

Третья группа тестов стоит 2 балла, для неё выполняются ограничения n,m≤500.

Примеры
входные данные
4 10
**********
*...*....*
*...*....*
**********
выходные данные
8
входные данные
3 3
***
***
***
выходные данные
0
*/

import java.util.*;

public class Main {

    static class Solution {
        public int maximalRectangle(char[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

            int[] height = new int[matrix[0].length];
            for (int i = 0; i < matrix[0].length; i++) {
                if (matrix[0][i] == '.') height[i] = 1;
            }
            int result = largestInLine(height);
            for (int i = 1; i < matrix.length; i++) {
                resetHeight(matrix, height, i);
                result = Math.max(result, largestInLine(height));
            }

            return result;
        }

        private void resetHeight(char[][] matrix, int[] height, int idx) {
            for (int i = 0; i < matrix[0].length; i++) {
                if (matrix[idx][i] == '.') height[i] += 1;
                else height[i] = 0;
            }
        }

        public int largestInLine(int[] height) {
            if (height == null || height.length == 0) return 0;
            int len = height.length;
            Stack<Integer> s = new Stack<Integer>();
            int maxArea = 0;
            for (int i = 0; i <= len; i++) {
                int h = (i == len ? 0 : height[i]);
                if (s.isEmpty() || h >= height[s.peek()]) {
                    s.push(i);
                } else {
                    int tp = s.pop();
                    maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                    i--;
                }
            }
            return maxArea;
        }
    }
    static char[][] map;
    static int n, m;
    static int maxArea = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        scanner.nextLine();
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        scanner.close();

        System.out.println(new Solution().maximalRectangle(map));
    }
}
