package ya_training.algo1_0.task5.numbersSum;

/*Вася очень любит везде искать своё счастливое число K. Каждый день он ходит в школу по улице, вдоль которой припарковано N машин.
Он заинтересовался вопросом, сколько существует наборов машин, стоящих подряд на местах с
L до R, что сумма их номеров равна K. Помогите Васе узнать ответ на его вопрос.Например, если число N=5, K=17,
а номера машин равны 17, 7, 10, 7, 10, то существует 4 набора машин:17 (L=1,R=1),7, 10 (L=2,R=3),10, 7 (L=3,R=4),7, 10 (L=4,R=5)

Формат ввода
В первой строке входных данных задаются числа N и K (1≤N≤100000, 1≤K≤10^9).Во второй строке содержится N чисел,
задающих номера машин. Номера машин могут принимать значения от 1 до 999 включительно.

Формат вывода
Необходимо вывести одно число — количество наборов.

Пример 1
Ввод
5 17
17 7 10 7 10
Вывод
4
Пример 2
Ввод
5 10
1 2 3 4 1
Вывод
2*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = reader.readLine().split(" ");

        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);

        int[] N = new int[n];
        String[] sN = reader.readLine().split(" ");
        for (int i=0; i<n; i++) {
            N[i] = Integer.parseInt(sN[i]);
        }

        // указатель левый
        int l = 0;
        int sum = 0;
        int count = 0;

        // указатель правый
        for (int r=0; r<n; r++) {
            sum += N[r];
            while (sum > k) {
                sum -= N[l];
                l++;
            }
            if (sum == k) {
                count ++;
                sum -= N[l];
                l++;
            }
        }

        System.out.println(count);

        reader.close();

    }
}
