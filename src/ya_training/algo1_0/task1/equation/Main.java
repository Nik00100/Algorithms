package ya_training.algo1_0.task1.equation;

/*Решите в целых числах уравнение:

sqrt(a*x + b) = c,

a, b, c – данные целые числа: найдите все решения или сообщите, что решений в целых числах нет.

Формат ввода
Вводятся три числа a, b и c по одному в строке.

Формат вывода
Программа должна вывести все решения уравнения в порядке возрастания, либо NO SOLUTION (заглавными буквами),
если решений нет. Если решений бесконечно много, вывести MANY SOLUTIONS.

Пример 1
Ввод	Вывод
1
0
0
0
Пример 2
Ввод	Вывод
1
2
3
7
Пример 3
Ввод	Вывод
1
2
-3
NO SOLUTION
*/

import java.io.*;

public class Main {

    public static void solve (int a, int b, int c) {
        if (c < 0 || (a == 0 && b != c*c) || (a!=0 && b + a*(c*c - b)/a < 0))
            System.out.println("NO SOLUTION");
        else if (a == 0 && b == c*c)
            System.out.println("MANY SOLUTIONS");
        else {
            if (a != 0) {
                float x = ((float)c*c - b) / a;
                int temp = (int) x;
                if (x != temp)
                    System.out.println("NO SOLUTION");
                else
                    System.out.println(temp);
            } else
                System.out.println("NO SOLUTION");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());
        int c = Integer.parseInt(reader.readLine());

        solve(a,b,c);

        reader.close();
    }
}
