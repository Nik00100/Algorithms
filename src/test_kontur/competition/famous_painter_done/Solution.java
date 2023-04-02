package test_kontur.competition.famous_painter_done;

/*A. Известный художник
ограничение по времени на тест1 секунда
ограничение по памяти на тест256 мегабайт

Очень известный в определенных кругах художник Игнат рисовал каждый день по одной картине в течение N дней.
В i-й день Игнат нарисовал картину с яркостью красок ai.
Теперь Игнат хочет выбрать из N получившихся картин самую контрастную пару. Пара картин (i,j), нарисованных в дни i и j
соответственно, называется самой контрастной, если значение ai−aj максимально среди всех возможных пар.

Входные данные
В первой строке содержится целое число N — количество дней (2≤N≤10^5). Во второй строке содержатся целые числа a1,a2,...,aN,
где ai — яркость картины, нарисованной в i-й день (−10^9≤ai≤10^9).

Выходные данные
Выведите различные целые числа i и j — номера дней, в которые Игнат нарисовал самую контрастную пару картин (i,j).
Числа i и j должны лежать в пределах от 1 до N. Если таких пар несколько, выведите пару с максимальным значением i−j.
Обратите внимание, что пара (i,j) отличается от пары (j,i).

Система оценки
В этой задаче 2 группы тестов.
Первая группа тестов стоит 5 баллов, для нее выполняется ограничение N≤1000
Вторая группа тестов стоит 5 баллов, для нее выполняется ограничение N≤105.

Примеры
входные данные
6
1 2 1 3 1 3
выходные данные
6 1
входные данные
4
2 1 0 -1
выходные данные
1 4
*/

import java.io.*;
import java.util.*;

public class Solution {
    static List<Integer> getIndexesOfElementsEqualsValue(int[] arr, int value) {
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                answer.add(i);
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] a = new int[n];
        String[] s = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(s[i]);
        }

        int minValue = Arrays.stream(a).min().getAsInt();
        int maxValue = Arrays.stream(a).max().getAsInt();

        List<Integer> indexesMaxValue = getIndexesOfElementsEqualsValue(a, maxValue);
        List<Integer> indexesMinValue = getIndexesOfElementsEqualsValue(a, minValue);

        int maxIndex = indexesMaxValue.get(indexesMaxValue.size() - 1) + 1;
        int minIndex = indexesMinValue.get(0) + 1;

        System.out.println(maxIndex + " " + minIndex);

        reader.close();
    }
}
