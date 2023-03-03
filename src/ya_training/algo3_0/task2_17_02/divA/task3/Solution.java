package ya_training.algo3_0.task2_17_02.divA.task3;

import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));

        String[] kn = in.readLine().split(" ");
        int k = Integer.parseInt(kn[0]);
        int n = Integer.parseInt(kn[1]);

        int[] inTime = new int[n];
        int[] outTime = new int[n];

        for (int i = 0; i < n; ++i) {
            String[] line = in.readLine().split(" ");
            inTime[i] = Integer.parseInt(line[0]);
            outTime[i] = Integer.parseInt(line[1]);
        }

        PriorityQueue<Integer> freeGarages = new PriorityQueue<Integer>();
        for (int i = 1; i <= k; i++) {
            freeGarages.offer(i);
        }

        int[] ans = new int[n];
        Queue<int[]> trainQueue = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        for (int i = 0; i < n; ++i) {
            while (!trainQueue.isEmpty() && trainQueue.peek()[0] < inTime[i]) {
                int[] train = trainQueue.poll();
                freeGarages.offer(train[1]);
            }
            if (freeGarages.isEmpty()) {
                out.println(String.format("0 %d", i + 1));
                in.close();
                out.close();
                return;
            }
            ans[i] = freeGarages.poll();
            trainQueue.offer(new int[] { outTime[i], ans[i] });
        }

        for (int i = 0; i < n; i++)
            out.println(ans[i]);

        out.close();
        in.close();
    }
}

