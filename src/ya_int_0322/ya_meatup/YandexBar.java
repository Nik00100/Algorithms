package ya_int_0322.ya_meatup;

import java.io.*;
import java.util.*;

public class YandexBar {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = reader.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        String[] str = new String[n];
        for (int i =0; i< n; i++) {
            str[i] = reader.readLine();
        }
        int k = Integer.parseInt(reader.readLine());
        int start = n - 2;
        for (int i = 0; i < k; i++){
            String[] s = reader.readLine().split(" ");
            int num = Integer.parseInt(s[1]);
            char ch = s[2].toCharArray()[0];
            fillGlass(str, num, ch, start, m);
            start -= num;
        }

        for (String s : str) {
            System.out.println(s);
        }

        reader.close();
    }

    static void fillGlass (String[] glassForm, int num, char ch, int start, int m) {
        for (int i = start; i > start - num; i--) {
            String current = glassForm[i];
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < m; j++) {
                if (current.charAt(j) == ' ') {
                    sb.append(ch);
                } else {
                    sb.append(current.charAt(j));
                }
            }
            glassForm[i] = sb.toString();
        }
    }
}
