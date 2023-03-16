package ya_int_0322.abrakadabra.q;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        String text = scanner.nextLine();
        int[] p = new int[text.length()];
        int[] d = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            p[i] = scanner.nextInt() - 1;
        }
        for (int i = 0; i < text.length(); i++) {
            d[i] = scanner.nextInt();
        }

        int[] m = new int[text.length()];
        int[] count = new int[26];
        int power = 0;
        for (int i = 0; i < text.length(); i++) {
            int pos = i;
            int cnt = 0;
            while (m[pos] <= count[text.charAt(pos) - 'a']) {
                cnt++;
                count[text.charAt(pos) - 'a']++;
                pos = p[pos];
                if (pos == i) {
                    break;
                }
            }
            m[pos] = cnt;
            power += d[i] * m[pos];
        }

        System.out.println(power);
    }
}
