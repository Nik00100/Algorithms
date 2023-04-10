package ya_int_0322.ya_meatup.q;

import java.util.*;

public class YandexMedian {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String s = scanner.next();
        int[] ans = new int[n];
        for (int r = 1; r < n; r++) {
            int l = r - 1;
            int cnt0 = 0;
            int cnt1 = 0;
            while (l >= 0 && r < n) {
                if (s.charAt(l) == '0') {
                    cnt0++;
                } else {
                    cnt1++;
                }
                if (s.charAt(r) == '0') {
                    cnt0++;
                } else {
                    cnt1++;
                }
                if (cnt0 == cnt1) {
                    ans[r] = l + 1;
                    break;
                }
                l--;
                r++;
            }
            if (ans[r] == 0) {
                ans[r] = -1;
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(ans[i] + " ");
        }
    }
}
