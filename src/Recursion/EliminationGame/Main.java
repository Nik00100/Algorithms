package Recursion.EliminationGame;

/*You have a list arr of all integers in the range [1, n] sorted in a strictly increasing order. Apply the following algorithm on arr:

Starting from left to right, remove the first number and every other number afterward until you reach the end of the list.
Repeat the previous step again, but this time from right to left, remove the rightmost number and every other number from the remaining numbers.
Keep repeating the steps again, alternating left to right and right to left, until a single number remains.
Given the integer n, return the last number that remains in arr.



Example 1:

Input: n = 9
Output: 6
Explanation:
arr = [1, 2, 3, 4, 5, 6, 7, 8, 9]
arr = [2, 4, 6, 8]
arr = [2, 6]
arr = [6]*/

public class Main {
    /*Denote L as traversing [1,...,n] from left to right, R as traversing [1,...,n] from right to left.

    When n is odd:
    L(1234567) = R(246) = R(123)*2
    R(1234567) = L(246) = L(123)*2

    When n is even:
    L(123456) = R(246) = R(123)*2
    R(123456) = L(135) = L(123) + 1 (a bit tricky)*/
    public int lastRemaining(int n) {
        return leftToRight(n);
    }

    // eliminate [1...n] first from left to right, then alternate
    private int leftToRight(int n) {
        if (n == 1) return 1;
        // scan from left to right is simple, the length of array doesn't matter
        // [1, 2, 3, 4] -> 2 * [1, 2]
        // [1, 2, 3, 4, 5] -> 2 * [1, 2]
        return 2 * rightToLeft(n / 2);
    }

    // eliminate [1...n] first from right to left, then alternate
    private int rightToLeft(int n) {
        if (n == 1) return 1;
        // if the length of array is even, we will get only odd number
        // [1, 2, 3, 4] -> [1, 3] = 2 * [1, 2] - 1
        if (n % 2 == 0) return 2 * leftToRight(n / 2) - 1;
            // else if the length of array is odd, we will get only even number
            // [1, 2, 3, 4, 5] -> [2, 4] = 2 * [1, 2]
        else return 2 * leftToRight(n / 2);
    }
}
