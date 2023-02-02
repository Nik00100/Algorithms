package training.algo1_0.task1.triangle;

/*Даны три натуральных числа. Возможно ли построить треугольник с такими сторонами. Если это возможно, выведите строку YES,
иначе выведите строку NO.

Треугольник — это три точки, не лежащие на одной прямой.

Формат ввода
Вводятся три натуральных числа.

Формат вывода
Выведите ответ на задачу.

Пример 1
Ввод	Вывод
3
4
5

YES
Пример 2
Ввод	Вывод
3
5
4


YES
Пример 3
Ввод	Вывод
4
5
3
YES*/

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] sides = new int[3];

        String res = "YES";

        int p = 0;
        for (int i=0; i<3; i++) {
            sides[i] = Integer.parseInt(reader.readLine());
            p += sides[i];
        }

        for (int i=0; i<3; i++) {
            if (sides[i] >= p - sides[i]) res = "NO";
        }

        System.out.println(res);
        reader.close();
    }

}
