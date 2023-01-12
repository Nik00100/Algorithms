package Array_Greedy.ArrayRepeatedOnes;

import java.io.*;

class Main {
    static int calcOnes(int[] input) {
        int cur = 0;
        int total = 0;
        for(int i=0; i<input.length; i++) {
            if (input[i]==1) {
                cur++;
                total = Math.max(total,cur);
            } else {
                cur = 0;
            }
        }
        return total;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] array = new int[n];
        for(int i=0; i<n; i++) {
            array[i] = Integer.parseInt(reader.readLine());
        }
        System.out.println(calcOnes(array));
    }
}