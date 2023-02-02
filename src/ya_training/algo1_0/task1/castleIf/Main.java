package ya_training.algo1_0.task1.castleIf;

/*За многие годы заточения узник замка Иф проделал в стене прямоугольное отверстие размером D × E.
Замок Иф сложен из кирпичей, размером A × B × C. Определите, сможет ли узник выбрасывать кирпичи в море через это отверстие,
если стороны кирпича должны быть параллельны сторонам отверстия.

Формат ввода
Программа получает на вход числа A, B, C, D, E.

Формат вывода
Программа должна вывести слово YES или NO.

Пример 1
Ввод	Вывод
1
1
1
1
1
YES
Пример 2
Ввод	Вывод
2
2
2
1
1
NO


Выбираем минимальные грани кирпича и проверяем габариты*/

import java.io.*;
import java.util.*;

public class Main {
    public static void solve (int a, int b, int c, int d, int e) {
        d = Math.min(d,e);
        e = Math.max(d,e);
        int[] brick = new int[] {a,b,c};
        Arrays.sort(brick);
        if (brick[0] <= d && brick[1] <= e)
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());
        int c = Integer.parseInt(reader.readLine());
        int d = Integer.parseInt(reader.readLine());
        int e = Integer.parseInt(reader.readLine());

        solve(a,b,c,d,e);

        reader.close();
    }
}
