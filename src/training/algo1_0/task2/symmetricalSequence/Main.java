package training.algo1_0.task2.symmetricalSequence;

/*Последовательность чисел назовем симметричной, если она одинаково читается как слева направо, так и справа налево.
Например, следующие последовательности являются симметричными:

1 2 3 4 5 4 3 2 1

1 2 1 2 2 1 2 1

Вашей программе будет дана последовательность чисел. Требуется определить, какое минимальное количество и каких чисел надо
приписать в конец этой последовательности, чтобы она стала симметричной.

Формат ввода
Сначала вводится число N — количество элементов исходной последовательности (1 ≤ N ≤ 100). Далее идут N чисел — элементы
этой последовательности, натуральные числа от 1 до 9.

Формат вывода
Выведите сначала число M — минимальное количество элементов, которое надо дописать к последовательности, а потом M чисел
(каждое — от 1 до 9) — числа, которые надо дописать к последовательности.

Пример 1
Ввод
9
1 2 3 4 5 4 3 2 1
Вывод
0
Пример 2
Ввод
5
1 2 1 2 2
Вывод
3
1 2 1
Пример 3
Ввод
5
1 2 3 4 5
Вывод
4
4 3 2 1*/

import java.io.*;

public class Main {

    public static int[] solve (int[] arr) {
        int n = arr.length;
        for (int start = 0; start < n; start++) {
            int i = start;
            int j = n - 1;

            while (i<n && j >=0 && arr[i] == arr[j] && i<=j) {
                i++;
                j--;
            }
            if (i > j) {
                int[] ans = new int[start];
                for (int k = 0; k <= start -1; k++) {
                    ans[k] = arr[start - 1 - k];
                }
                return ans;
            }
        }
        return new int[0];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[] s = reader.readLine().split(" ");

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }

        int[] ans = solve(arr);
        System.out.println(ans.length);
        if (ans.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<ans.length; i++) {
                sb.append(ans[i]).append(" ");
            }
            sb.deleteCharAt(sb.length()-1);
            System.out.println(sb.toString());
        }

        reader.close();
    }
}
