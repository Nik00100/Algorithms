package test_kontur.competition.obsessed_done;

/*C. Одержимый
ограничение по времени на тест1.5 секунд
ограничение по памяти на тест256 мегабайт

Игнат просто одержим интересными подотрезками массивов из целых неотрицательных чисел.
Интересным подотрезком массива [a1,…,aN] он называет массив [al,al+1,…,ar−1,ar] (1≤l≤r≤N), в котором не более одного
нулевого элемента, а сумма всех элементов не превосходит числа K.
У Игната есть массив a длины N. Найдите количество интересных подотрезков этого массива.

Входные данные
В первой строке содержатся целые числа N и K (1≤N≤10^5;0≤K≤10^9).
Во второй строке содержатся целые числа a1, ..., aN (0≤ai≤10^9).

Выходные данные
Выведите количество интересных подотрезков массива.

Система оценки
В этой задаче 4 группы тестов.
Первая группа тестов стоит 3 балла, для нее выполняются ограничения N≤100; 0≤ai≤105.
Вторая группа тестов стоит 3 балла, для нее выполняются ограничения N≤2000; 0≤ai≤109.
Третья группа тестов стоит 2 балла, для нее выполняются ограничения N≤105; 1≤ai≤109.
Четвертая группа тестов стоит 2 балла, для нее выполняются ограничения N≤105; 0≤ai≤109.

Примеры
входные данные
4 1
0 1 1 0
выходные данные
6

входные данные
4 4
1 2 3 4
выходные данные
5

Примечание
В первом примере интересными являются четыре одноэлементных подотрезка, а также подотрезки [0,1] и [1,0].
Во втором примере интересными являются четыре одноэлементных подотрезка, а также подотрезок [1,2].
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] nk = reader.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);

        int[] nums = new int[n];
        String[] s = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(s[i]);
        }

        long subarraySum = 0l, subarrayCount = 0l;
        int left = 0, right = 0, zero = 0;
        while(right < n){
            if (nums[right] == 0) {
                zero++;
            }
            subarraySum += nums[right];
            while(left <= right && subarraySum > k){
                if (nums[left] == 0) {
                    zero--;
                }
                subarraySum -= nums[left++];
            }
            while(left <= right && zero > 1){
                if (nums[left] == 0) {
                    zero--;
                }
                subarraySum -= nums[left++];
            }
            subarrayCount += (right - left + 1);
            right++;
        }

        System.out.println(subarrayCount);

        reader.close();
    }
}
