package BFS_DFS_UnionFind.DfsCanWin;

/*464. Can I Win  https://leetcode.com/problems/can-i-win/

In the "100 game" two players take turns adding, to a running total, any integer from 1 to 10.
The player who first causes the running total to reach or exceed 100 wins.
What if we change the game so that players cannot re-use integers?
For example, two players might take turns drawing from a common pool of numbers from 1 to 15 without replacement until
they reach a total >= 100.
Given two integers maxChoosableInteger and desiredTotal, return true if the first player to move can force a win,
otherwise, return false. Assume both players play optimally.

Example 1:
Input: maxChoosableInteger = 10, desiredTotal = 11
Output: false
Explanation:
No matter which integer the first player choose, the first player will lose.
The first player can choose an integer from 1 up to 10.
If the first player choose 1, the second player can only choose integers from 2 up to 10.
The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
Same with other integers chosen by the first player, the second player will always win.
Example 2:
Input: maxChoosableInteger = 10, desiredTotal = 0
Output: true
Example 3:
Input: maxChoosableInteger = 10, desiredTotal = 1
Output: true


For short notation, let M = maxChoosableInteger and T = desiredTotal.

Key Observation: the state of the game is completely determined by currently available numbers to pick in the common pool.

State of Game: initially, we have all M numbers [1, M] available in the pool. Each number may or may not be picked at
a state of the game later on, so we have maximum 2^M different states. Note that M <= 20, so int range is enough to cover it.
For memorization, we define int k as the key for a game state, where:
        the i-th bit of k, i.e., k & (1<<i) represents the availability of number i+1 (1: picked; 0: not picked).
At state k, the current player could pick any unpicked number from the pool, so state k can only go to one of the valid
next states k':
        if i-th bit of k is 0, set it to be 1, i.e., next state k' = k|(1<<i).

Recursion: apparently the current player can win at state k iff opponent can't win at some valid next state k'.

Memorization: to speed up the recursion:
0 : not calculated yet;
1 : current player can win;
-1: current player can't win.

Initial State Check:
There are several checks to be done at initial state k = 0 for early termination so we won't waste our time for DFS process:
    - if the sum of entire pool S = M*(M+1)/2 is less than T, of course, nobody can reach T.
    - if the sum S == T, the order to pick numbers from the pool is irrelevant. Whoever picks the last will reach T.
        So the first player can win iff M is odd.*/

public class Main {
    static class Solution {
        private int[] memo;
        public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
            int sum = (1+maxChoosableInteger) * maxChoosableInteger / 2;
            // Can win
            if(desiredTotal <= maxChoosableInteger) return true;
            // Total is too large, nobody can win
            if(sum < desiredTotal) return false;
            // Total happens to match sum, whoever picks at odd times wins
            if(sum == desiredTotal) return maxChoosableInteger % 2 == 1;
            memo = new int[1<<maxChoosableInteger];
            // Non-trivial case: do DFS
            // Initial total: desiredTotal
            // Initial game state: k = 0 (all numbers are not picked)
            return dfs(maxChoosableInteger, desiredTotal, 0);
        }

        // DFS to check if I can win
        // bitRep: current game state
        // total: remaining total to reach
        private boolean dfs(int maxInteger, int total, int bitRep) {
            // total is already reached by opponent, so I lose
            if (total <= 0) {
                return false;
            }
            if (memo[bitRep] != 0) {
                return memo[bitRep] == 1;
            }
            // try all currently available numbers
            for(int i = 0; i < maxInteger; i++) {
                int pos = 1<<i;
                // if (i+1) is available to pick and my opponent can't win after I picked, I win!
                if ((bitRep & pos) == 0 && !dfs(maxInteger, total-i-1, bitRep | pos)) {
                    memo[bitRep] = 1;
                    return true;
                }
            }
            // Otherwise, I will lose
            memo[bitRep] = -1;
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().canIWin(10,1));
    }

}
