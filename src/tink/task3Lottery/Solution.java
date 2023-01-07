package tink.task3Lottery;

import java.io.*;

public class Solution {
    // Для поиска НОК вначале ищем НОД по алгоритму Евклида, а НОК = |a*b|/НОД
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        long minLCM = Long.MAX_VALUE;
        int index = -1;
        for (int i=1; i<=n/2; i++) {
            if (minLCM > LCM(i,n-i)) {
                minLCM = LCM(i,n-i);
                index=i;
            }
        }
        System.out.println(index + " " + (n - index));
    }

    // НОК
    private static int LCM(int x, int y) {
        int a = x;
        int b = y;
        while (y != 0) {
            int tmp = y;
            y = x % y;
            x = tmp;  // в итоге НОД будет равен х
        }
        // метод вернет НОК согласно формуле НОК = |a*b|/НОД
        return Math.abs(a * b) / x;
    }

}
