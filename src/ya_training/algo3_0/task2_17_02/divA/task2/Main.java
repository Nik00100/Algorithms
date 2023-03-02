package ya_training.algo3_0.task2_17_02.divA.task2;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        int n = Integer.parseInt(reader.readLine());
        Deque<Integer> queue1 = new ArrayDeque<>();
        Deque<Integer> queue2 = new ArrayDeque<>();

        char sign;
        int x;
        while (n-- > 0) {
            String input = reader.readLine();
            sign = input.charAt(0);
            if (sign == '+') {
                x = Integer.parseInt(input.substring(2));
                queue2.addLast(x);
            } else if (sign == '*') {
                x = Integer.parseInt(input.substring(2));
                queue2.addFirst(x);
            } else {
                System.out.println(queue1.peekFirst());
                queue1.removeFirst();
            }
            if (queue1.size() < queue2.size()) {
                queue1.addLast(queue2.peekFirst());
                queue2.removeFirst();
            }
        }

        reader.close();
    }
}
