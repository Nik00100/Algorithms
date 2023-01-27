package BFS_DFS_UnionFind.BacktrackParenGenerate;

import java.io.*;
import java.util.*;

class Variant_2 {
    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        generate(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public static void generate(List<String> ans, StringBuilder cur, int open, int close, int max){
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }

        if (open < max) {
            cur.append("(");
            generate(ans, cur, open+1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(")");
            generate(ans, cur, open, close+1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine());
        List<String> list = generateParenthesis(n);
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