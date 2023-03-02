package ya_training.algo3_0.task1_15_02.divA.task1;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static boolean isSortPossible(double[] tasks) {
        if (tasks == null || tasks.length == 0) {
            return true;
        }
        PriorityQueue<Double> queue = new PriorityQueue<>(Arrays.stream(tasks).boxed().collect(Collectors.toList()));

        Stack<Double> stack = new Stack<>();
        int n = tasks.length;

        boolean couldSort = true;
        double next = queue.poll();

        for (int i = 0; i < n; i++) {
            double num = tasks[i];
            if (num == next) {
                if (!queue.isEmpty()) next = queue.poll();
                while (!stack.isEmpty() && stack.peek() == next) {
                    stack.pop();
                    if (!queue.isEmpty()) next = queue.poll();
                }
                continue;
            }

            if (!stack.isEmpty() && num > stack.peek()) {
                couldSort = false;
                break;
            }

            stack.push(num);
        }
        return couldSort;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

        int n = Integer.parseInt(reader.readLine());

        for (int i =0; i<n; i++) {
            String[] row = reader.readLine().split(" ");
            int k = Integer.parseInt(row[0]);
            double[] tasks = new double[k];
            for (int j =0; j<k; j++) {
                tasks[j] = Double.parseDouble(row[j + 1]);
            }
            if (isSortPossible(tasks))
                System.out.println(1);
            else
                System.out.println(0);
        }

        reader.close();
    }
}
