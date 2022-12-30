package leetcode3.countDigitOneInN;

/*Given an integer n, count the total number of digit 1 appearing in all non-negative
integers less than or equal to n.

Example 1:
Input: n = 13
Output: 6

Iterate over i from 1 to n incrementing by 10 each time:
    - Add (n/(i∗10))∗i to ans representing the repetition of groups of i sizes
    after each (i∗10) interval.

    - Add Math.min(remainder - pow10 + 1, pow10) to ans representing the additional
    digits dependant on the digit in ith place.
*/

public class Main {

    public static int countDigitOne(int n) {
        int ans = 0;

        for (long pow10 = 1; pow10 <= n; pow10 *= 10) {
            final long divisor = pow10 * 10;
            final int quotient = (int) (n / divisor);
            final int remainder = (int) (n % divisor);
            if (quotient > 0)
                ans += quotient * pow10;
            if (remainder >= pow10)
                ans += Math.min(remainder - pow10 + 1, pow10);
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(countDigitOne(14));
    }

}
