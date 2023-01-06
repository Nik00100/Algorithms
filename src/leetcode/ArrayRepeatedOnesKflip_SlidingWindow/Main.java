package leetcode.ArrayRepeatedOnesKflip_SlidingWindow;

/*
Дан массив из нулей и единиц. Нужно определить, какой максимальный по длине подинтервал единиц можно получить,
удалив ровно K элемент массива.

Our window is defined as [j, i]; in the end the difference i - j will be our answer.

If we encounter zero, we decrease k. If k falls below zero, we advance j and update k*/

public class Main {
    static int longestOnes(int[] nums, int k) {
        int i = 0, j = 0;
        while (i < nums.length) {
            k -= nums[i++] == 1 ? 0 : 1;
            if (k < 0)
                k += nums[j++] == 1 ? 0 : 1;
            System.out.println(i+" "+j);
        }
        return i - j;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,0,0,0,1,1,1,1,0};
        System.out.println(longestOnes(nums,0));
    }
}
