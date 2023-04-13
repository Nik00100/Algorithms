package Sort.BucketSort_RadixSort;

/*Количество бакетов соответствует размеру входного массива, а индекс каждого бакета вычисляется путем округления элемента
до целого числа. Сложность алгоритма BucketSort зависит от алгоритма сортировки, используемого для отсортированных бакетов.
Например, если использовать быструю сортировку (QuickSort) для каждого бакета, то общая сложность будет O(n log n),
где n - количество элементов во входном массиве.

Шаг 1: Создание бакетов
    Создаем пустой массив из списков (List) bucket длиной n.
    Для каждого элемента arr[i] входного массива:
        Вычисляем индекс бакета bucketIndex = (int) Math.floor(n * arr[i]).
        Если bucket[bucketIndex] еще не создан, то создаем его как новый список.

Шаг 2: Распределение элементов по бакетам
    Для каждого элемента arr[i] входного массива:
        Вычисляем индекс бакета bucketIndex = (int) Math.floor(n * arr[i]).
        Добавляем элемент arr[i] в список bucket[bucketIndex].

Шаг 3: Сортировка элементов в бакетах
    Для каждого созданного бакета bucket[i] cортируем элементы списка bucket[i] с помощью выбранного алгоритма сортировки.

Шаг 4: Объединение отсортированных бакетов
    Создаем переменную index = 0.
    Для каждого созданного бакета bucket[i]:
        Если бакет bucket[i] не является пустым:
            Добавляем все элементы из списка bucket[i] в массив arr, начиная с индекса index.
            Увеличиваем index на количество элементов, которые мы только что добавили в массив arr.*/

import java.util.*;

public class BucketSort {
    public static void bucketSort(double[] arr) {
        int n = arr.length;
        List<Double>[] bucket = new List[n];

        // создаем бакеты и распределяем элементы по ним
        for (int i = 0; i < n; i++) {
            int bucketIndex = (int) Math.floor(n * arr[i]);
            if (bucket[bucketIndex] == null) {
                bucket[bucketIndex] = new ArrayList<>();
            }
            bucket[bucketIndex].add(arr[i]);
        }

        // сортируем каждый бакет
        for (int i = 0; i < n; i++) {
            if (bucket[i] != null) {
                Collections.sort(bucket[i]);
            }
        }

        // объединяем отсортированные бакеты
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (bucket[i] != null) {
                for (double num : bucket[i]) {
                    arr[index++] = num;
                }
            }
        }
    }

    public static void main(String[] args) {
        double[] arr = {0.25, 0.53, 0.18, 0.16, 0.37, 0.44, 0.70, 0.11, 0.63, 0.87};
        System.out.println("Original array: " + Arrays.toString(arr));
        bucketSort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
