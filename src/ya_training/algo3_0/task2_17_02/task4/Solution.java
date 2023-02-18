package ya_training.algo3_0.task2_17_02.task4;


import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input8.txt")));

        int n = Integer.parseInt(reader.readLine());
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < n; i++) {
            String[] s = reader.readLine().split(" ");
            int command = Integer.parseInt(s[0]);

            if (command == 0) {
                int value = Integer.parseInt(s[1]);
                priorityQueue.offer(value);
            } else {
                int max = priorityQueue.poll();
                System.out.println(max);
            }
        }
        reader.close();
    }
}
