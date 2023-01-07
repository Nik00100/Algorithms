package ya.deadline;

import java.io.*;
import java.util.*;

public class Main {
    static long getDay(Set<Long> set, int x, int k) {
        long res = 0;
        while (k>0) {
            long next = set.iterator().next();
            res = next;
            set.add(x+next);
            set.remove(next);
            k--;
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nxk = reader.readLine().split(" ");
        int n = Integer.parseInt(nxk[0]);
        int x = Integer.parseInt(nxk[1]);
        int k = Integer.parseInt(nxk[2]);

        String[] deadlines = reader.readLine().split(" ");
        Set<Long> set = new TreeSet<>();
        for (int i=0; i<n; i++) {
            set.add(Long.parseLong(deadlines[i]));
        }

        writer.write(String.valueOf(getDay(set,x,k)));

        reader.close();
        writer.close();
    }
}
