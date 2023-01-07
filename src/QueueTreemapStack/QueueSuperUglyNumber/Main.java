package QueueTreemapStack.QueueSuperUglyNumber;

/*A super ugly number is a positive integer whose prime factors are in the array primes.
Given an integer n and an array of integers primes, return the nth super ugly number.
The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
Example 1:
Input: n = 12, primes = [2,7,13,19]
Output: 32
Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12 super ugly numbers given primes = [2,7,13,19].
Example 2:
Input: n = 1, primes = [2,3,5]
Output: 1
Explanation: 1 has no prime factors, therefore all of its prime factors are in the array primes = [2,3,5].*/

import java.util.*;

public class Main {
    class UglyNum {
        public int prime;
        public int index; // Point to the next index of uglyNums
        public int value; // Prime * uglyNums[index]
        public UglyNum(int prime, int index, int value) {
            this.prime = prime;
            this.index = index;
            this.value = value;
        }
    }

    class Solution {
        public int nthSuperUglyNumber(int n, int[] primes) {
            Queue<UglyNum> minHeap = new PriorityQueue<>((a, b) -> a.value - b.value);
            int[] uglyNums = new int[n];
            uglyNums[0] = 1;

            for (final int prime : primes)
                minHeap.offer(new UglyNum(prime, 1, prime * uglyNums[0]));

            for (int i = 1; i < n; ++i) {
                uglyNums[i] = minHeap.peek().value;
                while (minHeap.peek().value == uglyNums[i]) {
                    final UglyNum u = minHeap.poll();
                    minHeap.offer(new UglyNum(u.prime, u.index + 1, u.prime * uglyNums[u.index]));
                }
            }

            return uglyNums[n - 1];
        }
    }


}
