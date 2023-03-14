package ya_training.algo3_0.final_sprint.task3;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input8.txt")));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine().trim();

        int n = s.length();
        //int n = Integer.parseInt(reader.readLine());
        //int n = Integer.parseInt(s);




        /*BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        for (int i = 0; i<k; i++) {
            int num = Integer.parseInt(sk[i]);
            writer.write(String.valueOf(countInterestingSteakers(num, A)));
            writer.write('\n');
        }*/


        reader.close();
    }
}
