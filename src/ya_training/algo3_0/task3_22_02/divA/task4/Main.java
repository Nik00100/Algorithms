package ya_training.algo3_0.task3_22_02.divA.task4;

import java.util.*;
import java.io.*;

public class Main {
    static final int BEGIN = getSecond("09:00:00");
    static final int FINISH = getSecond("18:00:00");
    static final int START_DINNER = getSecond("13:00:00");
    static final int END_DINNER = getSecond("14:00:00");


    static int getSecond(String time) {
        String[] s = time.split(":");
        int hour = Integer.parseInt(s[0]);
        int minute = Integer.parseInt(s[1]);
        int second = Integer.parseInt(s[2]);
        return hour*3600 + minute*60 + second;
    }

    static void fillTime(int begin, int workSpeed, int[] timeBeforeLunch, int[] timeAfterLunch) {
        if (begin < START_DINNER) {
            Arrays.fill(timeBeforeLunch, begin - BEGIN, timeBeforeLunch.length, workSpeed);
            Arrays.fill(timeAfterLunch, 0, timeAfterLunch.length, workSpeed);
        } else if (begin>= START_DINNER && begin <= END_DINNER) {
            Arrays.fill(timeAfterLunch, 0, timeAfterLunch.length, workSpeed);
        } else {
            Arrays.fill(timeAfterLunch, begin - END_DINNER, timeAfterLunch.length, workSpeed);
        }
    }

    static int count(int[] dp, int[] time) {
        Arrays.fill(dp, 0);
        int n = time.length;
        for (int i = 0; i < n; i++) {
            if (i > 0) dp[i] = Math.max(dp[i - 1], dp[i]);
            if (i + time[i] <= n) dp[i+time[i]] = Math.max(dp[i + time[i]], dp[i] + 1);
        }
        return Math.max(dp[n], dp[n - 1]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

        int n = Integer.parseInt(reader.readLine());

        int N1 = START_DINNER - BEGIN;
        int N2 = FINISH - END_DINNER;
        int[] timeBeforeLunch = new int[N1];
        int[] timeAfterLunch = new int[N2];

        for (int i = 0; i < n; i++) {
            String[] s = reader.readLine().split(" ");
            int begin = getSecond(s[0].trim());
            int workSpeed = Integer.parseInt(s[1].trim());
            fillTime(begin, workSpeed, timeBeforeLunch, timeAfterLunch);
        }

        System.out.println(count(new int[N1 + 1], timeBeforeLunch) + count(new int[N2 + 1], timeAfterLunch));

        reader.close();
    }
}
