package Search_SlidingWindow_QuickSelect_TwoPointer.TwoPointerMagicalString;

/*481. Magical String https://leetcode.com/problems/magical-string/description/

A magical string s consists of only '1' and '2' and obeys the following rules:
The string s is magical because concatenating the number of contiguous occurrences of characters '1' and '2' generates the
string s itself. The first few elements of s is s = "1221121221221121122……". If we group the consecutive 1's and 2's in s,
it will be "1 22 11 2 1 22 1 22 11 2 11 22 ......" and the occurrences of 1's or 2's in each group are
"1 2 2 1 1 2 1 2 2 1 2 2 ......". You can see that the occurrence sequence is s itself.
Given an integer n, return the number of 1's in the first n number in the magical string s.

Example 1:
Input: n = 6
Output: 3
Explanation: The first 6 elements of magical string s is "122112" and it contains three 1's, so return 3.

Algorithm:

    - Create an int array a and initialize the first 3 elements with 1, 2, 2.
    - Create two pointers head and tail. head points to the number which will be used to generate new numbers.
        tail points to the next empty position to put the new number. Then keep generating new numbers until tail >= n.
    - Need to create the array 1 element more than n to avoid overflow because the last round head might points to a number 2.
    - A trick to flip number back and forth between 1 and 2: num = num ^ 3*/

public class Main {
    public int magicalString(int n) {
        if (n <= 0) return 0;
        if (n <= 3) return 1;

        int[] a = new int[n + 1];
        a[0] = 1; a[1] = 2; a[2] = 2;
        int head = 2, tail = 3, num = 1, result = 1;

        while (tail < n) {
            for (int i = 0; i < a[head]; i++) {
                a[tail] = num;
                if (num == 1 && tail < n) result++;
                tail++;
            }
            num = num ^ 3;
            head++;
        }

        return result;
    }
}
