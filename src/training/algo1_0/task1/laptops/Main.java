package training.algo1_0.task1.laptops;

/*В школе решили на один прямоугольный стол поставить два прямоугольных ноутбука. Ноутбуки нужно поставить так,
чтобы их стороны были параллельны сторонам стола. Определите, какие размеры должен иметь стол, чтобы оба ноутбука
на него поместились, и площадь стола была минимальна.

Формат ввода
Вводится четыре натуральных числа, первые два задают размеры одного ноутбука, а следующие два — размеры второго.
Числа не превышают 1000.

Формат вывода
Выведите два числа — размеры стола. Если возможно несколько ответов, выведите любой из них (но только один).

Пример 1
Ввод
10 2 2 10
Вывод
20 2
2 20
4 10
10 4
Пример 2
Ввод
5 7 3 2
Вывод
9 5
5 9
Примечания
В примерах указаны всевозможные ответы на поставленную задачу. Ваша программа должна вывести один из них.*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = reader.readLine().split(" ");
        int a1 = Integer.parseInt(s[0]);
        int b1 = Integer.parseInt(s[1]);
        int a2 = Integer.parseInt(s[2]);
        int b2 = Integer.parseInt(s[3]);

        List<Table> ans = generate(a1,b1,a2,b2);
        Collections.sort(ans, (t1,t2)-> t1.getSquare() - t2.getSquare());

        System.out.println(ans.get(0).l + " " + ans.get(0).w);

        reader.close();
    }

    static List<Table> generate(int a1, int b1, int a2, int b2) {
        List<Table> ans = new ArrayList<>();
        ans.add(new Table(a1+a2, Math.max(b1,b2)));
        ans.add(new Table(a1+b2, Math.max(b1,a2)));
        ans.add(new Table(b1+a2, Math.max(a1,b2)));
        ans.add(new Table(b1+b2, Math.max(a1,a2)));
        return ans;
    }

    static class Table {
        int l;
        int w;

        public Table(int l, int w) {
            this.l = l;
            this.w = w;
        }
        int getSquare () {
            return w*l;
        }

        @Override
        public String toString() {
            return "Table{" +
                    "l=" + l +
                    ", w=" + w +
                    '}';
        }
    }
}
