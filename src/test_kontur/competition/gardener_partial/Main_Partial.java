package test_kontur.competition.gardener_partial;

import java.util.*;

public class Main_Partial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] s = scanner.nextLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        int[][] a = new int[n][m];

        for (int i = 0; i < n; i++) {
            String str = scanner.nextLine();
            for (int j = 0; j < m; j++) {
                a[i][j] = str.charAt(j) - '0';
            }
        }

        int ans = Integer.MAX_VALUE;
        int row = -1, col = -1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int max = Integer.MIN_VALUE;

                for (int k = 0; k < n; k++) {
                    if (k == i) continue;
                    for (int l = 0; l < m; l++) {
                        if (l == j) continue;
                        max = Math.max(max, a[k][l]);
                    }
                }

                if (max < ans) {
                    ans = max;
                    row = i;
                    col = j;
                }
            }
        }

        System.out.println((row + 1) + " " + (col + 1));
    }

}
