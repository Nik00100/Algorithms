package ya_int_0322.keyboard_done.q;

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        int[] ids = new int[n];
        String[] s = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            ids[i] = Integer.parseInt(s[i]);
        }

        int[] rows = new int[n];
        s = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            rows[i] = Integer.parseInt(s[i]);
        }

        int k = Integer.parseInt(reader.readLine());
        int[] text = new int[k];
        s = reader.readLine().split(" ");
        for (int i = 0; i < k; i++) {
            text[i] = Integer.parseInt(s[i]);
        }
        Map<Integer, Integer> rowMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            rowMap.put(ids[i], rows[i]);
        }
        int transitions = 0;
        int currentRow = rowMap.get(text[0]);
        for (int i = 1; i < k; i++) {
            int nextRow = rowMap.get(text[i]);
            if (currentRow != nextRow) {
                transitions++;
            }
            currentRow = nextRow;
        }
        System.out.println(transitions);

        reader.close();
    }
}

