package training.algo1_0.task2.closestNumber;

/*Напишите программу, которая находит в массиве элемент, самый близкий по величине к  данному числу.

Формат ввода
В первой строке задается одно натуральное число N, не превосходящее 1000 – размер массива.
Во второй строке содержатся N чисел – элементы массива (целые числа, не превосходящие по модулю 1000).
В третьей строке вводится одно целое число x, не превосходящее по модулю 1000.

Формат вывода
Вывести значение элемента массива, ближайшее к x. Если таких чисел несколько, выведите любое из них.

Пример 1
Ввод	Вывод
5
1 2 3 4 5
6
5
Пример 2
Ввод	Вывод
5
5 4 3 2 1
3
3*/

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[] s = reader.readLine().split(" ");
        int num = Integer.parseInt(reader.readLine());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }

        int temp = Math.abs(arr[0] - num);
        int ans = 0;

        for (int i = 1; i < s.length; i++) {
            if (Math.abs(arr[i] - num) < temp) {
                ans = i;
                temp = Math.abs(arr[i] - num);
            }
        }

        System.out.println(arr[ans]);

        reader.close();
    }
}
