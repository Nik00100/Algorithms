package Sort.Merge_Quick_Heap_Selection_Insert_Sort;

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

public class Main {

    static class MergeSort {
        // алгоритм разбивает список на две части, каждую из них он разбивает ещё на две и так далее,
        // пока не останутся единичные элементы. Массив из одного элемента считается упорядоченным.
        // Соседние элементы сравниваются и соединяются вместе до тех пор, пока все элементы не будут отсортированы.
        int[] sortArray(int[] nums) {
            mergeSort(nums,0, nums.length-1);
            return nums;
        }
        private void mergeSort(int[] nums, int l, int r) {
            if (l >= r) return;
            int mid = l + (r - l) / 2;
            mergeSort(nums, l, mid);
            mergeSort(nums, mid + 1, r);

            int[] buf = Arrays.copyOf(nums, nums.length);
            for (int k = l; k <= r; k++)
                buf[k] = nums[k];

            int i = l, j = mid + 1;
            for (int k = l; k <= r; k++) {
                if (i > mid) {
                    nums[k] = buf[j];
                    j++;
                } else if (j > r) {
                    nums[k] = buf[i];
                    i++;
                } else if (buf[j] < buf[i]) {
                    nums[k] = buf[j];
                    j++;
                } else {
                    nums[k] = buf[i];
                    i++;
                }
            }
        }
    }

    static class QuickSort {
        //Выбирается опорный элемент — по сути любой элемент, относительно которого нужно отсортировать остальные значения.
        //Значения меньше его — слева, значения больше — справа.
        //Далее у правой и левой части также выбирается по опорному элементу и происходит то же самое:
        //сортируются значения относительно этих элементов, потом у образовавшихся частей выбираются  опорные элементы —
        // и так до тех пор, пока мы не получим отсортированный ряд.

        int[] sortArray(int[] nums) {
            quickSort(nums,0, nums.length-1);
            return nums;
        }

        void quickSort(int[] nums, int l, int r) {
            //завершить,если массив пуст или уже нечего делить
            if (nums.length == 0 || l >= r) return;
            //выбираем опорный элемент
            int m = (l+r)/2;
            int middle = nums[m];
            //разделияем на подмассивы и меняем местами
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
            //рекурсия для левой и правой частей
            if (l < j) quickSort(nums, l, j);
            if (r > i) quickSort(nums, i, r);
        }

    }


    public static void main(String[] args) {
        int[] nums = {5,1,1,2,0,0};
        System.out.println(Arrays.toString(new MergeSort().sortArray(nums)));
    }
}
