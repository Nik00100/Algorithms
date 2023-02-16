package ya_training.algo3_0.contest_13_02.task4;

import java.io.*;

public class Solution {

    static String calculation(int pos1, int pos2, int row1, int row2, int n, int row) {
        StringBuilder sb = new StringBuilder();
        if (pos2 <= n) {
            if (Math.abs(row - row1) >= Math.abs(row - row2)) {
                sb.append(row2).append(" ").append(2 - pos2 % 2);
            } else if (pos1 > 0) {
                sb.append(row1).append(" ").append(2 - pos1 % 2);
            }
        } else if (pos1 > 0) {
                sb.append(row1).append(" ").append(2 - pos1 % 2);
        } else {
            sb.append(-1);
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input8.txt")));

        int n = Integer.parseInt(reader.readLine());
        int k = Integer.parseInt(reader.readLine());
        int row = Integer.parseInt(reader.readLine());
        int column = Integer.parseInt(reader.readLine());

        int pos1 = (row - 1) * 2 + column - k; // position before Petya with same variant
        int pos2 = (row - 1) * 2 + column + k; // position after Petya with same variant
        int row1 = (pos1 + 1) / 2; // possible desk where Vasya could sit before Petya
        int row2 = (pos2 + 1) / 2; // possible desk where Vasya could sit after Petya

        System.out.println(calculation(pos1, pos2, row1, row2, n , row));

        reader.close();
    }
}
