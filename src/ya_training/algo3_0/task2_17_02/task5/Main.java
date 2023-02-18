package ya_training.algo3_0.task2_17_02.task5;

/*Отсортируйте данный массив. Используйте пирамидальную сортировку.

Формат ввода
Первая строка входных данных содержит количество элементов в массиве N, N ≤ 105. Далее задаются N целых чисел,
не превосходящих по абсолютной величине 10^9.

Формат вывода
Выведите эти числа в порядке неубывания.

Пример
Ввод	Вывод
1
1

1 */

import java.io.*;
import java.util.*;

public class Main {

    static class HeapSort {
        public void heapSort(int[] nums) {
            int n = nums.length;

            // Построение кучи, максимальный элемент окажется в корне.
            for (int i = n / 2 - 1; i >= 0; i--)
                heapify(nums, n, i);

            // Один за другим извлекаем элементы из кучи
            for (int i=n-1; i>=0; i--) {
                // Перемещаем текущий корень в конец
                int temp = nums[0];
                nums[0] = nums[i];
                nums[i] = temp;

                // Вызываем процедуру heapify на уменьшенной куче
                heapify(nums, i, 0);
            }
        }

        // Метод для преобразования в двоичную кучу поддерева с корневым узлом i,
        // i - индекс элемента в nums[], n - размер кучи
        void heapify(int[] nums, int n, int i) {
            int largest = i; // Инициализируем наибольший элемент как корень
            int l = 2*i + 1; // левый = 2*i + 1
            int r = 2*i + 2; // правый = 2*i + 2

            // Если левый дочерний элемент больше корня
            if (l < n && nums[l] > nums[largest])
                largest = l;

            // Если правый дочерний элемент больше, чем самый большой элемент на данный момент
            if (r < n && nums[r] > nums[largest])
                largest = r;
            // Если самый большой элемент не корень
            if (largest != i) {
                int swap = nums[i];
                nums[i] = nums[largest];
                nums[largest] = swap;

                // Рекурсивно преобразуем в двоичную кучу затронутое поддерево
                heapify(nums, n, largest);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        String[] s = reader.readLine().split(" ");


        Stack<Integer> stack = new Stack<>();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(s[i]);
        }

        new HeapSort().heapSort(nums);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            sb.append(nums[i]).append(" ");
        }

        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());

        reader.close();
    }
}
