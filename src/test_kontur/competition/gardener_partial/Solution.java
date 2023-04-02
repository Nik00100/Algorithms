package test_kontur.competition.gardener_partial;

/*E. Неплохой огородник
ограничение по времени на тест2 секунды
ограничение по памяти на тест256 мегабайт

В деревне у Игната есть огород. План огорода представляет из себя клеточное поле из N строк и M столбцов.
Строки нумеруются от 1 до N сверху вниз, а столбцы нумеруются от 1 до M слева направо.
Игнат на время уезжал в город, и за время его отсутствия огород зарос сорняками.
Теперь огород можно описать матрицей N на M из целых неотрицательных чисел, обозначающих высоту сорняков в текущей клетке.
Игнат хочет привести огород в порядок. Но так как он человек занятой, у него хватит времени только на то,
чтобы прополоть ровно одну строку и ровно один столбец на плане огорода. После того как Игнат пропалывает одну клетку,
высота сорняков в этой клетке становится равной нулю.
Игнат ввел понятие запущенности огорода. Так он называет максимальную высоту сорняков среди всех клеток огорода.
Теперь Игнат хочет выбрать строку и столбец, которые нужно прополоть, чтобы запущенность огорода стала как можно меньше.
Найдите такие строку и столбец.

Входные данные
В первой строке содержатся целые числа N и M (2≤N,M≤1400).
В каждой из следующих N строк содержатся по M цифр от '0' до '9' без пробелов — высоты сорняков в соответствующих клетках огорода.

Выходные данные
Выведите номер строки и номер столбца, которые нужно прополоть, чтобы запущенность огорода стала минимально возможной.
Если подходящих ответов несколько, выведите любой из них.

Система оценки
В этой задаче 3 группы тестов.
Первая группа тестов стоит 4 балла, для нее выполняется ограничение N≤30.
Вторая группа тестов стоит 3 балла, для нее выполняется ограничение N≤300.
Третья группа тестов стоит 3 балла, для нее выполняется ограничение N≤1400.

Пример
входные данные
5 4
4412
3212
0121
2192
4103
выходные данные
1 3

Примечание
В примере есть несколько способов добиться минимальной запущенности огорода (она равна 4).
Например, другой способ, кроме приведённого в примере — прополоть четвёртую строку и первый столбец.
Любой ответ, приводящий к запущенности 4, является правильным.*/

import java.io.*;
import java.util.*;

public class Solution {
    static List<int[]> getListOfPointCoords (int[][] arr, int value) {
        List<int[]> answer = new ArrayList<>();
        int n = arr.length;
        int m = arr[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == value) {
                    answer.add(new int[] {i, j});
                }
            }
        }
        return answer;
    }

    static int maxHeight (int[][] arr, int row, int col) {
        int maxHeight = Integer.MIN_VALUE;
        int n = arr.length;
        int m = arr[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == row || j ==col) {
                    continue;
                }
                maxHeight = Math.max(arr[i][j], maxHeight);
            }
        }
        return maxHeight;
    }

    static int[] calculateFromTwoPoint(int[][] arr, int row1, int col1, int row2, int col2) {
        int[] result;
        if (maxHeight(arr, row1, col2) >= maxHeight(arr, row2, col1)) {
            result = new int[] {row2, col1, maxHeight(arr, row2, col1)};
        } else {
            result = new int[] {row1, col2, maxHeight(arr, row1, col2)};
        }
        return result;
    }

    static int[] calculateRowCol(int[][] arr, List<int[]> listOfMax1Coords, List<int[]> listOfMax2Coords) {
        int row = Integer.MAX_VALUE;
        int col = Integer.MAX_VALUE;
        int minHeight = Integer.MAX_VALUE;

        for (int[] coord : listOfMax1Coords) { // проверяем все координаты максимальных высот max1
            if (maxHeight(arr, coord[0], coord[1]) < minHeight) {
                row = coord[0];
                col = coord[1];
                minHeight = maxHeight(arr, coord[0], coord[1]);
            }
        }

        for (int[] coord1 : listOfMax1Coords) {// проверяем все координаты максимальных высот max1 и max2
            for (int[] coord2 : listOfMax2Coords) {
                if (calculateFromTwoPoint(arr, coord1[0], coord1[1], coord2[0], coord2[1])[2] < minHeight) {
                    row = calculateFromTwoPoint(arr, coord1[0], coord1[1], coord2[0], coord2[1])[0];
                    col = calculateFromTwoPoint(arr, coord1[0], coord1[1], coord2[0], coord2[1])[1];
                    minHeight = calculateFromTwoPoint(arr, coord1[0], coord1[1], coord2[0], coord2[1])[2];
                }
            }
        }

        return new int[] {row, col};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = reader.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            String row = reader.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = row.charAt(j) - '0';
            }
        }

        int max1 = Math.max(arr[0][0], arr[0][1]);
        int max2 = Math.min(arr[0][0], arr[0][1]);
        for (int i = 0; i < n; i++) {
            for (int j = 2; j < m; j++) {
                if (arr[i][j] >= max1) {
                    max2 = max1;
                    max1 = arr[i][j];
                } else if (arr[i][j] > max2) {
                    max2 = arr[i][j];
                }
            }
        }

        List<int[]> listOfMax1Coords = getListOfPointCoords(arr, max1);
        List<int[]> listOfMax2Coords = getListOfPointCoords(arr, max2);

        int[] result = new int[2];
        result[0] = calculateRowCol(arr, listOfMax1Coords, listOfMax2Coords)[0];
        result[1] = calculateRowCol(arr, listOfMax1Coords, listOfMax2Coords)[1];

        System.out.println(String.format("%d %d", result[0] + 1, result[1] + 1));

        reader.close();
    }
}
