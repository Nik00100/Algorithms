package String.MathStringHammingDistance;

public class Main {
    /*The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
    Given two integers x and y, return the Hamming distance between them.
    Example 1:
    Input: x = 1, y = 4
    Output: 2
    Explanation:
    1   (0 0 0 1)
    4   (0 1 0 0)
           ↑   ↑
    The above arrows point to positions where the corresponding bits are different.*/
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
    public int hammingDistanceIterative(int x, int y) {
        int res = 0;
        int m = x^y;                  // take the xor of two numbers
        while(m != 0){                // count the no of "1"s
            if((m&1) == 1)
                res++;
            m = m>>1;
        }
        return res;
    }



    /*Given an integer array nums, return the sum of Hamming distances between all the pairs of the integers in nums.
    Example 1:
    Input: nums = [4,14,2]
    Output: 6
    Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
    showing the four bits relevant in this case).
    The answer will be:
    HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.*/
    public int totalHammingDistance(int[] nums) {
        int n = 31;
        int len = nums.length;
        int[] countOfOnes = new int[n];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < n; j++) {
                countOfOnes[j] += (nums[i] >> j) & 1;
            }
        }
        int sum = 0;
        for (int count: countOfOnes) {
            sum += count * (len - count);
        }
        return sum;
    }

}
