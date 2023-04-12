package ya_int_0322.ya_meatup.q;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String s = scanner.next();

        int c0 = 0;
        int c1 = 0;
        int last_0 = -1;
        int last_1 = -1;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                c0++;
                last_0 = i;
            } else {
                c1++;
                last_1 = i;
            }

            if (c0 > c1) {
                System.out.print(last_0 + 1 + " ");
            } else if (c0 < c1) {
                System.out.print(last_1 + 1 + " ");
            } else {
                System.out.print(-1 + " ");
            }
        }
    }
}