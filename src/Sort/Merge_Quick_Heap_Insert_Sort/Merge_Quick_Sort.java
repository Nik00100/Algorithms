package Sort.Merge_Quick_Heap_Insert_Sort;

/*Given an array of integers nums, sort the array in ascending order and return it.
You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and with the
smallest space complexity possible.
Example 1:
Input: nums = [5,2,3,1]
Output: [1,2,3,5]
Explanation: After sorting the array, the positions of some numbers are not changed (for example, 2 and 3),
while the positions of other numbers are changed (for example, 1 and 5).
Example 2:
Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]
Explanation: Note that the values of nums are not necessary unique.*/

import java.util.Arrays;

public class Merge_Quick_Sort {

    static class MergeSort {
        // Алгоритм сортировки слиянием:
        // Разбивает список на две части, каждую из них он разбивает ещё на две и так далее,
        // пока не останутся единичные элементы. Массив из одного элемента считается упорядоченным.
        // Соседние элементы сравниваются и соединяются вместе до тех пор, пока все элементы не будут отсортированы.
        // Худшее время	(n\log n), Лучшее время	O(n\log n), Среднее время O(n\log n)
        int[] sortArray(int[] nums) {
            mergeSort(nums, nums.length);
            return nums;
        }

        // Рекурсивная часть - поиск индекса середины массива, разбиение на два подмассива,
        // вызов методов разбиения двух полученных подмассивов и метода объединения/сортировки.
        private void mergeSort(int[] nums, int n) {
            // если один элемент или массив пуст, то делить нечего - выходим
            if (nums.length == 0 || n < 2) return;
            // середина массива
            int m = n/2;
            // разбиваем первоначальный массив на два подмассива
            int[] leftArray = new int[m];
            int[] rightArray = new int[n-m];
            for (int i=0; i<m; i++) {
                leftArray[i] = nums[i];
            }
            for  (int i=m; i<n; i++) {
                rightArray[i-m] = nums[i];
            }
            // дальше разбиваем два подмассива
            mergeSort(leftArray,m);
            mergeSort(rightArray,n-m);
            // объединяем в один массив
            merge(nums, leftArray, rightArray, m, n-m);
        }

        // Метод merge() - производит сортировку элементов и объединение их в один массив.
        // Сравниваются значения элементов двух подмассивов по одному и меньший элемент помещается в результирующий массив.
        // Когда заканчиваются элементы в одном из подмассивов, остальные элементы просто копируются в результирующий массив.
        private void merge(int[] nums, int[] leftArray, int[] rightArray, int leftArrayLength, int rightArrayLength) {
            // индекс левого подмассива
            int i = 0;
            // индекс правого подмассива
            int j = 0;
            // индекс результирующего массива
            int k = 0;
            // сортировка, пока есть элементы в обоих подмассивах
            while (i < leftArrayLength && j < rightArrayLength) {
                if (leftArray[i] < rightArray[j]) {
                    nums[k++] = leftArray[i++];
                } else {
                    nums[k++] = rightArray[j++];
                }
            }
            // если элементы в подмассивах остались, то они просто перемещаются в результирующий массив
            while (i < leftArrayLength) {
                nums[k++] = leftArray[i++];
            }
            while (j < rightArrayLength) {
                nums[k++] = rightArray[j++];
            }
        }
    }

    static class QuickSort {
        // Алгоритм быстрой сортировки:
        // Выбирается опорный элемент — по сути любой элемент, относительно которого нужно отсортировать остальные значения.
        // Значения меньше его — слева, значения больше — справа.
        // Далее у правой и левой части также выбирается по опорному элементу и происходит то же самое:
        // сортируются значения относительно этих элементов, потом у образовавшихся частей выбираются  опорные элементы —
        // и так до тех пор, пока мы не получим отсортированный ряд.
        // Худшее время	(n^2), Лучшее время	O(n\log n), Среднее время O(n\log n)

        int[] sortArray(int[] nums) {
            quickSort(nums,0, nums.length-1);
            return nums;
        }

        void quickSort(int[] nums, int l, int r) {
            // завершить,если массив пуст или уже нечего делить
            if (nums.length == 0 || l >= r) return;
            // выбираем опорный элемент
            int m = (l+r)/2;
            int middle = nums[m];
            // находим элемент слева больше опорного и справа меньше опорного и меняем их местами,
            // при этом все элементы, находящиеся "на своих местах" не трогаем
            int i = l, j = r;
            while (i<=j) {
                while (nums[i] < middle) i++;
                while (nums[j] > middle) j--;
                if (i <= j) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    i++;
                    j--;
                }
            }
            // рекурсия для левой и правой частей, если индексы i или j не достигли своих "краев"
            if (l < j) quickSort(nums, l, j);
            if (r > i) quickSort(nums, i, r);
        }
    }


    public static void main(String[] args) {
        int[] nums = {5,1,1,2,0,0};
        System.out.println(Arrays.toString(new MergeSort().sortArray(nums)));
    }
}
