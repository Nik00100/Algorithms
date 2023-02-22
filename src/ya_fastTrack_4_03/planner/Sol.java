package ya_fastTrack_4_03.planner;

import Sort.Merge_Quick_Heap_Insert_Sort.Heap_Insert_Sort;

import java.util.Arrays;

public class Sol {

    static void heapSort(int[] nums) {
        int n = nums.length;

        // Построение кучи, минимальный элемент окажется в корне.
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(nums, n, i);

        /*// Один за другим извлекаем элементы из кучи
        for (int i=n-1; i>=0; i--) {
            // Перемещаем текущий корень в конец
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;

            // Вызываем процедуру heapify на уменьшенной куче
            heapify(nums, i, 0);
        }*/

        for (int i=n-1; i>=0; i--) {
            // Перемещаем текущий корень в конец
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;

            // Вызываем процедуру heapify на уменьшенной куче
            heapify(nums, i, 0);
        }
    }
    static void heapify(int[] nums, int n, int i) {
        int lowest = i; // Инициализируем наименьший элемент как корень
        int l = 2*i + 1; // левый = 2*i + 1
        int r = 2*i + 2; // правый = 2*i + 2

        // Если левый дочерний элемент меньше корня
        if (l < n && nums[l] < nums[lowest])
            lowest = l;

        // Если правый дочерний элемент меньше наименьшего на данный момент элемента
        if (r < n && nums[r] < nums[lowest])
            lowest = r;
        // Если наименьший элемент не корень
        if (lowest != i) {
            int swap = nums[i];
            nums[i] = nums[lowest];
            nums[lowest] = swap;

            // Рекурсивно преобразуем в двоичную кучу затронутое поддерево
            heapify(nums, n, lowest);
        }
    }


    public static void main(String[] args) {
        int[] nums = {5,1,7,2,0,3};
        /*heapSort(nums);
        System.out.println(Arrays.toString(nums));*/
        int n = nums.length;

        // Построение кучи, минимальный элемент окажется в корне.
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(nums, n, i);
        System.out.println(Arrays.toString(nums));
    }
}
