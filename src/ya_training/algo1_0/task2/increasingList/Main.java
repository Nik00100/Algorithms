package ya_training.algo1_0.task2.increasingList;

/*Дан список. Определите, является ли он монотонно возрастающим(то есть верно ли, что каждый элемент этого списка больше предыдущего).

Выведите YES, если массив монотонно возрастает и NO в противном случае.

Пример 1
Ввод	Вывод
1 7 9
YES
Пример 2
Ввод	Вывод
1 9 7
NO
Пример 3
Ввод	Вывод
2 2 2
NO*/

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = reader.readLine().split(" ");

        int prev = Integer.parseInt(s[0]);
        for (int i=1; i<s.length; i++) {
            if (prev >= Integer.parseInt(s[i])) {
                System.out.println("NO");
                reader.close();
                return;
            }
            prev = Integer.parseInt(s[i]);
        }

        System.out.println("YES");
        reader.close();
    }
}
