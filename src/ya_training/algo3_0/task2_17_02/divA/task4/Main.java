package ya_training.algo3_0.task2_17_02.divA.task4;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        PrintWriter writer = new PrintWriter(new FileWriter("output.txt"));

        PriorityQueue<Double> numbers = new PriorityQueue<>();

        int n = Integer.parseInt(reader.readLine());

        String[] s = reader.readLine().split(" ");
        for (int i = 0; i < n; ++i) {
            double number = Double.parseDouble(s[i]);
            numbers.offer(number);
        }

        double cost = 0;
        while (numbers.size() > 1) {
            double first = numbers.poll();
            double second = numbers.poll();
            double sum = first + second;
            cost += sum;
            numbers.offer(sum);
        }

        writer.printf("%.2f", cost * 0.05);
        writer.close();
    }
}
