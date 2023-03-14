package ya_training.algo3_0.final_sprint.task1;

import java.io.*;
import java.util.*;

public class Main {
    static Map<String, Long> result;
    static Stack<String> stack;

    static void operate (String str) {
        String[] s = str.split(" ");
        if (s[0].equals("add")) {
            long num = Long.parseLong(s[1]);
            if (num > 0) {
                long current = result.getOrDefault(s[2], 0L);
                stack.push(s[1] + " " + s[2]);
                result.put(s[2], current + num);
            }
        } else if (s[0].equals("delete")) {
            long num = Long.parseLong(s[1]);
            while (!stack.empty() && num > 0) {
                String[] valName = stack.pop().split(" ");
                long valueInStack = Long.parseLong(valName[0]);
                //System.out.println(valName[0] + " " + valName[1]);
                String currentTrainName = valName[1];
                long currentTrainValueInMap = result.get(currentTrainName);
                if (num >= valueInStack) {
                    result.put(currentTrainName, currentTrainValueInMap - valueInStack);
                    num -= valueInStack;
                } else {
                    result.put(currentTrainName, currentTrainValueInMap - num);
                    stack.push((valueInStack - num) + " " + currentTrainName);
                    num = 0;
                }
            }
        } else {
            long current = result.getOrDefault(s[1], 0L);
            System.out.println(current);
        }
    }

    public static void main(String[] args) throws IOException {
        //BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input8.txt")));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        result = new HashMap<>();
        stack = new Stack<>();

        int n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            String s = reader.readLine().trim();
            operate(s);
            //result.entrySet().stream().map(e->e.getKey() +" "+ e.getValue()).forEach(System.out::println);
        }

        //result.entrySet().stream().map(e->e.getKey() +" "+ e.getValue()).forEach(System.out::println);

        /*BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        for (int i = 0; i<k; i++) {
            int num = Integer.parseInt(sk[i]);
            writer.write(String.valueOf(countInterestingSteakers(num, A)));
            writer.write('\n');
        }*/


        reader.close();
    }
}
