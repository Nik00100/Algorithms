package ya_int_0322.ya_meatup.q;

import java.util.*;

public class YandexMedian {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String s = scanner.next();
        int[] c0 = new int[n + 1]; // количество нулей
        int[] c1 = new int[n + 1]; // количество единиц
        for (int i = 1; i <= n; i++) {
            c0[i] = c0[i - 1];
            c1[i] = c1[i - 1];
            if (s.charAt(i - 1) == '0') {
                c0[i]++;
            } else {
                c1[i]++;
            }
        }
        int[] L = new int[n + 1]; // ответы
        for (int R = 2; R <= n; R++) {
            int c0_R = c0[R], c1_R = c1[R]; // количество нулей и единиц в S[1..R]
            if (c0_R > c1_R) { // медиана равна 0
                L[R] = findL(R, c1_R + 1, c0);
            } else if (c0_R < c1_R) { // медиана равна 1
                L[R] = findL(R, c0_R + 1, c1);
            } else { // медиана равна 0.5
                L[R] = findL(R, c0_R, c1);
                if (L[R] == -1) { // если не найдено L такое, что SR равно медиане, попробуем другой вариант
                    L[R] = findL(R, c0_R + 1, c1);
                }
                if (L[R] == -1) {
                    L[R] = findL(R, c1_R + 1, c0);
                }
            }
        }
        for (int i = 2; i <= n; i++) {
            System.out.print(L[i] + " ");
        }
    }

    // поиск L такое, что SR равно медиане
    private static int findL(int R, int count, int[] c) {
        int left = 1, right = R;
        while (left < right) {
            int mid = (left + right) / 2;
            if (c[R] - c[mid - 1] == count) {
                return mid;
            } else if (c[R] - c[mid - 1] < count) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (c[R] - c[left - 1] == count) {
            return left;
        } else {
            return -1;
        }
    }

}
