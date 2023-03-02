package BFS_DFS_UnionFind.BfsBulbSwitcherII;

/*672. Bulb Switcher II https://leetcode.com/problems/bulb-switcher-ii/description/


and O(1) solution
So, there are only 8 cases.
All_on, 1, 2, 3, 4, 1+4, 2+4, 3+4

public int flipLights(int n, int m) {
        if(m==0) return 1;
        if(n==1) return 2;
        if(n==2&&m==1) return 3;
        if(n==2) return 4;
        if(m==1) return 4;
        if(m==2) return 7;
        if(m>=3) return 8;
        return 8;
    }


There is a room with n bulbs labeled from 1 to n that all are turned on initially, and four buttons on the wall.
Each of the four buttons has a different functionality where:

Button 1: Flips the status of all the bulbs.
Button 2: Flips the status of all the bulbs with even labels (i.e., 2, 4, ...).
Button 3: Flips the status of all the bulbs with odd labels (i.e., 1, 3, ...).
Button 4: Flips the status of all the bulbs with a label j = 3k + 1 where k = 0, 1, 2, ... (i.e., 1, 4, 7, 10, ...).
You must make exactly presses button presses in total. For each press, you may pick any of the four buttons to press.

Given the two integers n and presses, return the number of different possible statuses after performing all presses button presses.



Example 1:

Input: n = 1, presses = 1
Output: 2
Explanation: Status can be:
- [off] by pressing button 1
- [on] by pressing button 2
Example 2:

Input: n = 2, presses = 1
Output: 3
Explanation: Status can be:
- [off, off] by pressing button 1
- [on, off] by pressing button 2
- [off, on] by pressing button 3
Example 3:

Input: n = 3, presses = 1
Output: 4
Explanation: Status can be:
- [off, off, off] by pressing button 1
- [off, on, off] by pressing button 2
- [on, off, on] by pressing button 3
- [off, on, on] by pressing button 4*/

import java.util.*;

public class Solution {
    public int flipLights(int n, int m) {
        n = n <= 6? n: (n % 6 + 6);

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        int init = (1 << n) - 1;
        queue.offer(init);
        for (int i=0; i<m; i++) {
            int size = queue.size();
            visited.clear();
            for (int k=0; k<size; k++) {
                int s = queue.poll();
                int[] next = new int[] {flipAll(s, n),
                        flipEven(s, n), flipOdd(s, n), flip3k1(s, n)};
                for (int s1: next) {
                    if (!visited.contains(s1)) {
                        queue.offer(s1);
                        visited.add(s1);
                    }
                }
            }
        }
        return queue.size();
    }

    private int flipAll(int s, int n) {
        int x = (1 << n) - 1;
        return s ^ x;
    }

    private int flipEven(int s, int n) {
        for (int i=0; i<n; i+=2) {
            s ^= 1 << i;
        }
        return s;
    }

    private int flipOdd(int s, int n) {
        for (int i=1; i<n; i+=2) {
            s ^= 1 << i;
        }
        return s;
    }

    private int flip3k1(int s, int n) {
        for (int i=0; i<n; i+=3) {
            s ^= 1 << i;
        }
        return s;
    }


}
