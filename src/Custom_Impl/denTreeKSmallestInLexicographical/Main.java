package Custom_Impl.denTreeKSmallestInLexicographical;

/*440. K-th Smallest in Lexicographical Order https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/description/

Given two integers n and k, return the kth lexicographically smallest integer in the range [1, n].
Example 1:
Input: n = 13, k = 2
Output: 10
Explanation: The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.

Actually this is a denary tree (each node has 10 children). Find the kth element is to do a k steps preorder traverse of the tree.

                                      0
  /                 /                /                 \
 1                 2                 3                 [4 ~ 9]
 /\                 /\              /\                  /\
0, [1~9]         0,   1,[2~9]    0,[1~9]               ...
/      \          /   /
[0~9]  ...     [0~9] [0~3]

Initially, image you are at node 1 (variable: curr), the goal is move (k - 1) steps to the target node x.
(substract steps from k after moving) when k is down to 0, curr will be finally at node x, there you get the result.
we don't really need to do a exact k steps preorder traverse of the denary tree, the idea is to calculate the
steps between curr and curr + 1 (neighbor nodes in same level), in order to skip some unnecessary moves.

Main function
    - Firstly, calculate how many steps curr need to move to curr + 1.
    - if the steps <= k, we know we can move to curr + 1, and narrow down k to k - steps.
    - else if the steps > k, that means the curr + 1 is actually behind the target node x in the preorder path,
        we can't jump to curr + 1. What we have to do is to move forward only 1 step
        (curr * 10 is always next preorder node) and repeat the iteration.

calSteps function
    - how to calculate the steps between curr and curr + 1?
        Here we come up a idea to calculate by level.
        Let n1 = curr, n2 = curr + 1.
        n2 is always the next right node beside n1's right most node (who shares the same ancestor "curr")
        (refer to the pic, 2 is right next to 1, 20 is right next to 19, 200 is right next to 199).

    - so, if n2 <= n, what means n1's right most node exists, we can simply add the number of nodes from n1 to n2 to steps.

    - else if n2 > n, what means n (the biggest node) is on the path between n1 to n2, add (n + 1 - n1) to steps.

    - organize this flow to "steps += Math.min(n + 1, n2) - n1; n1 *= 10; n2 *= 10;"*/

public class Main {
    static class Solution {
        public int findKthNumber(int n, int k) {
            int curr = 1;
            k = k - 1;
            while (k > 0) {
                int steps = calSteps(n, curr, curr + 1);
                if (steps <= k) {
                    curr += 1;
                    k -= steps;
                } else {
                    curr *= 10;
                    k -= 1;
                }
            }
            return curr;
        }
        //use long in case of overflow
        public int calSteps(int n, long n1, long n2) {
            int steps = 0;
            while (n1 <= n) {
                steps += Math.min(n + 1, n2) - n1;
                n1 *= 10;
                n2 *= 10;
            }
            return steps;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findKthNumber(13,4));
    }
}
