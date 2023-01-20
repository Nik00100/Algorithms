package Queue_TreeMap_Stack.QueueIPO;

/*502. IPO  https://leetcode.com/problems/ipo/

Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital,
LeetCode would like to work on some projects to increase its capital before the IPO. Since it has limited resources,
it can only finish at most k distinct projects before the IPO. Help LeetCode design the best way to maximize its
total capital after finishing at most k distinct projects. You are given n projects where the ith project has a pure
profit profits[i] and a minimum capital of capital[i] is needed to start it. Initially, you have w capital.
When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.
Pick a list of at most k distinct projects from given projects to maximize your final capital, and return the
final maximized capital. The answer is guaranteed to fit in a 32-bit signed integer.
Example 1:
Input: k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
Output: 4
Explanation: Since your initial capital is 0, you can only start the project indexed 0.
After finishing it you will obtain profit 1 and your capital becomes 1.
With capital 1, you can either start the project indexed 1 or the project indexed 2.
Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
Example 2:
Input: k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
Output: 6

Intuition
First, we greedily choose the most profitable available project. Then our capital increases by the profit of this project,
and some new projects that were unavailable before might become available now. If we choose a project other than
the most profitable one, our capital increases by a value less than the maximum possible, and fewer new options become available.
It means we should greedily choose the maximum profit every time. We can repeat this process of choosing the most profitable
project and then updating the projects we can afford until we finish kkk projects or cannot afford any new ones.*/

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    static class Project implements Comparable<Project> {
        int profit;
        int startCapital;

        public Project(int startCapital, int profit) {
            this.profit = profit;
            this.startCapital = startCapital;
        }

        @Override
        public int compareTo(Project project) {
            return startCapital - project.startCapital;
        }
    }


    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        Project[] projects = new Project[n];
        for (int i = 0; i < n; i++) {
            projects[i] = new Project(capital[i], profits[i]);
        }
        Arrays.sort(projects);
        // PriorityQueue is a min heap, but we need a max heap, so we use
        // Collections.reverseOrder()
        PriorityQueue<Integer> q = new PriorityQueue<Integer>(n, Collections.reverseOrder());
        int ptr = 0;
        for (int i = 0; i < k; i++) {
            while (ptr < n && projects[ptr].startCapital <= w) {
                q.add(projects[ptr++].profit);
            }
            if (q.isEmpty()) {
                break;
            }
            w += q.poll();
        }
        return w;
    }

    public static void main(String[] args) {
        int k=2, w = 0;
        int[] profits = {1,2,3}, capital = {0,1,1};
        System.out.println(findMaximizedCapital(k,w,profits,capital));
    }
}
