package ya_training.algo3_0.task3_22_02.divA.task4;

import java.util.*;
import java.io.*;

public class Main {

    static final int BEGIN = getSecond("09:00:00");
    static final int WORK_TIME = 32400;

    static int getSecond(String time) {
        String[] s = time.split(":");
        int hour = Integer.parseInt(s[0]);
        int minute = Integer.parseInt(s[1]);
        int second = Integer.parseInt(s[2]);
        return hour*3600 + minute*60 + second;
    }

    static int[] fillTime(int[] begin, int[] time) {
        int[] answer = new int[WORK_TIME];

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int[] time = new int[WORK_TIME];
        int[] dp = new int[WORK_TIME + 1];
        Arrays.fill(dp, 0);
        Arrays.fill(time, 0);

        for (int i = 0; i < n; i++) {
            String[] s = reader.readLine().split(" ");
            int begin = getSecond(s[0].trim());
            time[begin - BEGIN] = Integer.parseInt(s[1].trim());
        }


        for (int i = 0; i < 32400; i++) {
            if (i+1 < 32400) dp[i+1] = Math.max(dp[i+1], dp[i]);
            if (i+time[i] < 32400) dp[i+time[i]] = Math.max(dp[i+time[i]], dp[i]+1);
        }

        System.out.println(dp[32399]);

        reader.close();
    }
}
