package ya_training.algo1_0.task6.binSearch;

/*Реализуйте двоичный поиск в массиве

Формат ввода
В первой строке входных данных содержатся натуральные числа N и K (). Во второй строке задаются N элементов первого массива,
а в третьей строке – K элементов второго массива. Элементы обоих массивов - целые числа, каждое из которых по модулю не
превосходит 109

Формат вывода
Требуется для каждого из K чисел вывести в отдельную строку "YES", если это число встречается в первом массиве, и "NO" в
противном случае.

Пример 1
Ввод	Вывод
10 10
1 61 126 217 2876 6127 39162 98126 712687 1000000000
100 6127 1 61 200 -10000 1 217 10000 1000000000
NO
YES
YES
YES
NO
NO
YES
YES
NO
YES
Пример 2
Ввод	Вывод
10 10
-8 -6 -4 -4 -2 -1 0 2 3 3
8 3 -3 -2 2 -1 2 9 -8 0
NO
YES
NO
YES
YES
YES
YES
NO
YES
YES
Пример 3
Ввод	Вывод
10 5
1 2 3 4 5 6 7 8 9 10
-2 0 4 9 12
NO
NO
YES
YES
NO*/

import java.io.*;
import java.util.*;

public class Main {

    static int bSearch (int number, int[] arr) {
            int l = 0;
            var r = arr.length - 1;
            while (r > l) {
                int middle = (l + r) / 2;
                if (number == arr[middle]) {
                    return middle;
                } else if (number < arr[middle]) {
                    r = middle;
                } else {
                    l = middle + 1;
                }
            }
            return arr[r] == number ? r : -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = reader.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);

        String[] sN = reader.readLine().split(" ");
        int[] N = new int[n];
        for (int i = 0; i < n; i++) {
            N[i] = Integer.parseInt(sN[i]);
        }

        String[] sK = reader.readLine().split(" ");
        int[] K = new int[k];
        for (int i = 0; i < k; i++) {
            K[i] = Integer.parseInt(sK[i]);
        }

        Map<Integer,String> map = new HashMap<>();
        for (int i=0; i<k; i++) {
            if (!map.containsKey(i)) {
                if (bSearch(K[i],N) != -1) {
                    map.put(i,"YES");
                } else {
                    map.put(i,"NO");
                }
            }
        }

        for (String str : map.values()) {
            System.out.println(str);
        }

        //System.out.println(bSearch(11,N));

        reader.close();

    }
}
