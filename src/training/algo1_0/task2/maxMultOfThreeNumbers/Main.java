package training.algo1_0.task2.maxMultOfThreeNumbers;

/*В данном списке из n ≤ 105 целых чисел найдите три числа,произведение которых максимально.

Решение должно иметь сложность O(n), где n - размер списка.

Выведите три искомых числа в любом порядке.

Пример 1
Ввод
3 5 1 7 9 0 9 -3 10
Вывод
10 9 9
Пример 2
Ввод
-5 -30000 -12
Вывод
-5 -12 -30000
Пример 3
Ввод
1 2 3
Вывод
3 2 1
*/

import java.io.*;
import java.util.*;

public class Main {

    public static int findKthLargest(int[] A, int k) {
        k = A.length - k; // convert to index of k largest
        int l = 0, r = A.length - 1;
        while (l <= r) {
            int i = l; // partition [l,r] by A[l]: [l,i]<A[l], [i+1,j)>=A[l]
            for (int j = l + 1; j <= r; j++)
                if (A[j] < A[l]) swap(A, j, ++i);
            swap(A, l, i);

            if (k < i) r = i - 1;
            else if (k > i) l = i + 1;
            else return A[i];
        }
        return -1; // k is invalid
    }

    public static void swap (int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] s = reader.readLine().split(" ");
        int n = s.length;

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }



        int[] a1 = arr;
        findKthLargest(a1,3);
        long s1 = (long) a1[n-1]*a1[n-2]*a1[n-3];

        int[] a2 = arr;
        findKthLargest(a2,1);
        int[] a3 = arr;
        findKthLargest(a3,n-1);
        long s2 = (long) a2[n-1]*a3[0]*a3[1];

        if (s1>s2) {
            System.out.println(a1[n-1] +" "+ a1[n-2] + " " + a1[n-3]);
        } else {
            System.out.println(a2[n-1] +" "+ a3[0] + " " + a3[1]);
        }

        /*Arrays.sort(arr);
        System.out.println(Arrays.stream(arr).boxed().toList());*/
    }

}
