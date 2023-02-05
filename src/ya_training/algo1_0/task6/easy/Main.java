package ya_training.algo1_0.task6.easy;

/*Сегодня утром жюри решило добавить в вариант олимпиады еще одну, Очень Легкую Задачу. Ответственный секретарь Оргкомитета
напечатал ее условие в одном экземпляре, и теперь ему нужно до начала олимпиады успеть сделать еще N копий. В его распоряжении
имеются два ксерокса, один из которых копирует лист за х секунд, а другой – за y. (Разрешается использовать как один ксерокс,
так и оба одновременно. Можно копировать не только с оригинала, но и с копии.) Помогите ему выяснить, какое минимальное время
для этого потребуется.

Формат ввода
На вход программы поступают три натуральных числа N, x и y, разделенные пробелом (1 ≤ N ≤ 2 × 10^8, 1 ≤ x, y ≤ 10).

Формат вывода
Выведите одно число – минимальное время в секундах, необходимое для получения N копий.

Пример 1
Ввод	Вывод
4 1 1
3
Пример 2
Ввод	Вывод
5 1 2
4
*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] s = reader.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int x = Integer.parseInt(s[1]);
        int y = Integer.parseInt(s[2]);

        int t1 = Math.min(x, y);
        int t2 = Math.max(x, y);
        y = t2;
        x = t1;

        if (x * (n - 1) > y) {
            // возможно использовать два
            if (x != y) {
                int l = 0;
                int r = x * (n - 1);
                while (r > l ) {
                    int middle = (l + r) / 2;
                    if  (n - 1 <= (middle / x) + (middle / y)) {
                        r = middle;
                    } else {
                        l = middle+1;
                    }
                }
                System.out.println(r + x);
            } else {
                System.out.println((n - 1) / 2 * x + (n - 1) % 2 * x + x);
            }

        } else {
            System.out.println(x * n);
        }
        reader.close();
    }
}
