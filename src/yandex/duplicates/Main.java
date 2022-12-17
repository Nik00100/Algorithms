package yandex.duplicates;

import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        if (n==0) return;
        int prev = Integer.parseInt(reader.readLine());
        System.out.println(prev);
        if (n>1) {
            for (int i = 1; i < n; i++) {
                int cur = Integer.parseInt(reader.readLine());
                if (cur > prev) {
                    System.out.println(cur);
                }
                prev = cur;
            }
        }
    }
}

