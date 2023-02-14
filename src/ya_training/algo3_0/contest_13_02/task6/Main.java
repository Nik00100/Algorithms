package ya_training.algo3_0.contest_13_02.task6;

import java.io.*;
import java.util.*;

public class Main {
    static boolean overlaped (int[] a, int[] b) {
        int[][] sum = new int[2][2];
        sum[0] = a;
        sum[1] = b;
        Arrays.sort(sum, (x,y) -> x[0] - y[0]);
        return sum[0][1] >= sum[1][0];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input8.txt")));

        int m = Integer.parseInt(reader.readLine());
        int n = Integer.parseInt(reader.readLine());

        int[][] seg = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] s = reader.readLine().split(" ");
            int start = Integer.parseInt(s[0]);
            int end = Integer.parseInt(s[1]);
            seg[i][0] = start;
            seg[i][1] = end;
        }

        int count = 0;
        for (int i= 0; i< n; i++) {
            int[] a = seg[i];
            boolean flag = false;
            for (int j = i + 1; j<n; j++) {
                int[] b = seg[j];
                if (overlaped(a, b)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                count++;
                //System.out.println(a[0] + " " + a[1]);
            }
        }

        System.out.println(count);

        reader.close();
    }
}
