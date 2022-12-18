package yandex.brackets;

import java.io.*;
import java.util.*;

class Solution {
    public static List<String> generateParenthesis(int m) {
        int n = 2*m;
        List<String> resultList = new ArrayList<>();
        char[] result;
        StringBuilder sb = new StringBuilder();

        if (n < 2) return null;
        result = new char[n];
        for (int i = 0; i < n/2; i++) {
            result[i] = '(';
        }
        for (int i = n/2; i < n; i++) {
            result[i] = ')';
        }
        resultList.add(sb.append(result).toString());

        sb.delete(0,result.length);
        do {
            int i = n - 1;
            int c = 0;
            while (i >= 0) {
                c += result[i] == ')' ? -1 : 1;
                if ((c < 0) && (result[i] == '(')) break;
                --i;
            }
            if (i < 0) break;

            result[i++] = ')';
            int ind = i;
            for (; i < n; i++) {
                result[i] = (i<= (n-ind+c)/2+ind) ? '(' : ')';
            }
            resultList.add(sb.append(result).toString());
            sb.delete(0,result.length);
        } while (true);

        return resultList;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int m = Integer.parseInt(reader.readLine());
        List<String> list = generateParenthesis(m);
        if (list != null)
            list.stream().forEach(str -> {
                try {
                    writer.write(str);
                    writer.newLine();
                } catch (IOException e) {}
            });
        else
            try {
                writer.newLine();
            } catch (IOException e) {}

        reader.close();
        writer.close();
    }
}