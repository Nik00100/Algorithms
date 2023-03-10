package ya_training.algo3_0.task1_15_02.divA.task4;

/*Гистограмма является многоугольником, сформированным из последовательности прямоугольников, выровненных на общей базовой линии.
Прямоугольники имеют равную ширину, но могут иметь различные высоты. Например, фигура слева показывает гистограмму,
которая состоит из прямоугольников с высотами 2, 1, 4, 5, 1, 3, 3. Все прямоугольники на этом рисунке имеют ширину, равную 1.
Пример:
         ||
      || ||
      || ||
      || ||    ||
||    || || || ||
|| || || || || ||
2  1  5  6  2  3
Input: int[] heights = {2,1,5,6,2,3};
Output: 10


Обычно гистограммы используются для представления дискретных распределений, например, частоты символов в текстах.
Отметьте, что порядок прямоугольников очень важен. Вычислите область самого большого прямоугольника в гистограмме, который
также находится на общей базовой линии. На рисунке справа заштрихованная фигура является самым большим выровненным прямоугольником
на изображенной гистограмме.

Формат ввода
В первой строке входного файла записано число N (0<N≤10^6) — количество прямоугольников гистограммы. Затем следует N целых чисел
h1,...,hn, где 0≤hi≤10^9. Эти числа обозначают высоты прямоугольников гистограммы слева направо.
Ширина каждого прямоугольника равна 1
Формат вывода
Выведите площадь самого большого прямоугольника в гистограмме. Помните, что этот прямоугольник должен быть на общей базовой линии.
Пример
Ввод	Вывод
7 2 1 4 5 1 3 3
8*/

import java.io.*;
import java.util.*;

public class Main {
    static long largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        long maxArea = 0;
        for (int i = 0; i <= n; i++){
            int height = (i == n ? 0 : heights[i]);
            if (stack.isEmpty() || height >= heights[stack.peek()]) {
                stack.push(i);
            } else {
                int heightIndex = stack.pop();
                maxArea = Math.max(maxArea, (long) heights[heightIndex] * (stack.isEmpty() ? i : i - 1 - stack.peek()));
                i--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = reader.readLine().split(" ");

        int n = Integer.parseInt(s[0]);

        int[] heights = new int[n];
        for (int i=0; i<n; i++) {
            heights[i] = Integer.parseInt(s[i + 1]);
        }

        System.out.println(largestRectangleArea(heights));

        reader.close();
    }
}
