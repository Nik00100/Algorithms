package Math.MathLargestPalindrome;

/*479. Largest Palindrome Product https://leetcode.com/problems/largest-palindrome-product/

Given an integer n, return the largest palindromic integer that can be represented as the product of two n-digits integers.
Since the answer can be very large, return it modulo 1337.

Example 1:
Input: n = 2
Output: 987
Explanation: 99 x 91 = 9009, 9009 % 1337 = 987
Example 2:
Input: n = 1
Output: 9*/

public class Main {
    static class Solution {
        public long largestPalindrome(int n) {
            if (n == 1)
                return 9;

            final int kMod = 1337;
            final int upper = (int) Math.pow(10, n) - 1;
            final int lower = (int) Math.pow(10, n - 1) - 1;

            for (int i = upper; i > lower; --i) {
                final long cand = getPalindromeCandidate(i);
                for (long j = upper; j * j >= cand; --j)
                    if (cand % j == 0)
                        //return (int) (cand % kMod);
                        return cand;
            }

            throw new IllegalArgumentException();
        }

        private long getPalindromeCandidate(int i) {
            final String reversed = new StringBuilder().append(i).reverse().toString();
            return Long.valueOf(i + reversed);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().largestPalindrome(5));
    }
}
