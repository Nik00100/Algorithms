package yandex.hamming;

import java.io.*;

public class Main {
    static String getHamming(String s, String t, int n) throws IOException {
        if (s.length() != n || t.length() != n) throw new IOException("Введены неверные значения");

        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        char[] res = sChars;
        int countDiff = 0;

        for(int i = 0; i<n; i++) {
            if (sChars[i] != tChars[i]) {
                countDiff++;
            }
            if (countDiff % 2 == 0 && countDiff > 0) {
                res[i] = tChars[i];
            }
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nq = reader.readLine().split(" ");
        int n = Integer.parseInt(nq[0]);
        int q = Integer.parseInt(nq[1]);


        for (int i=0; i<q; i++) {
            String[] st = reader.readLine().split(" ");
            writer.write(getHamming(st[0],st[1],n));
            writer.newLine();
        }

        reader.close();
        writer.close();
    }
}
