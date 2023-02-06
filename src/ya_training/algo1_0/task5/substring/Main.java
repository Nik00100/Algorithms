package ya_training.algo1_0.task5.substring;

/*В этой задаче Вам требуется найти максимальную по длине подстроку данной строки, такую что каждый символ встречается в
ней не более k раз.

Формат ввода
В первой строке даны два целых числа n и k (1≤ n ≤100000,1≤ k ≤ n ) , где n – количество символов в строке.
Во второй строке n символов – данная строка, состоящая только из строчных латинских букв.

Формат вывода
В выходной файл выведите два числа – длину искомой подстроки и номер её первого символа. Если решений несколько, выведите любое.

Пример 1
Ввод	Вывод
3 1
abb

2 1

Пример 2
Ввод	Вывод
5 2
ababa

4 1*/

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = reader.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int k = Integer.parseInt(split[1]);
        String s = reader.readLine();

        int r = 0;
        long ans = 0;
        long index = 0;
        int[] alphabet = new int[26];

        for (int l = 0; l<n; l++) {
            while (r < n && alphabet[s.charAt(r) - 'a'] < k) {
                alphabet[s.charAt(r) - 'a']++;
                r++;
            }

            // уменьшаем счетчик символов l-ого символа, потому что окно передвигается
            alphabet[s.charAt(l) - 'a']--;

            // обновляем максимальную длину и индекс старта
            if (ans < r - l) {
                ans = r - l;
                index = l + 1;
            }
        }

        System.out.println(ans + " " + index);

        reader.close();
    }
}
