package DP.DPStudentAttendanceExplicitDescription;

/*552. Student Attendance Record II https://leetcode.com/problems/student-attendance-record-ii/

An attendance record for a student can be represented as a string where each character signifies whether the student was absent,
late, or present on that day. The record only contains the following three characters:

'A': Absent.
'L': Late.
'P': Present.
Any student is eligible for an attendance award if they meet both of the following criteria:

The student was absent ('A') for strictly fewer than 2 days total.
The student was never late ('L') for 3 or more consecutive days.
Given an integer n, return the number of possible attendance records of length n that make a student eligible for
an attendance award. The answer may be very large, so return it modulo 10^9+7.
Example 1:
Input: n = 2
Output: 8
Explanation: There are 8 records with length 2 that are eligible for an award:
"PP", "AP", "PA", "LP", "PL", "AL", "LA", "LL"
Only "AA" is not eligible because there are 2 absences (there need to be fewer than 2).
Example 2:
Input: n = 1
Output: 3
Example 3:
Input: n = 10101
Output: 183236316*/

public class Main {
    /*#1 Brute force, O(2^n) , generate all strings. The complexity is not O(3^n) because only 1 A is allowed in the string.
    We can reduce one argument by decreasing n but that will make it less intuitive to derive to dp.*/
    static class dfs {
        public int checkRecord(int n) {
            return dfs(0, 0, 0, n);
        }

        private int dfs(int i, int A, int L, int n) {
            if (i == n) return 1;
            int res = dfs(i + 1, A, 0, n);  //P
            if (A == 0) res += dfs(i + 1, 1, 0, n); //A
            if (L < 2) res += dfs(i + 1, A, L + 1, n); //L
            return res;
        }
    }

    /*#2 */
    static class memo {
        public int checkRecord(int n) {
            int[][][] mem = new int[n][2][3];
            return dfs(0, 0, 0, n, mem);
        }

        private int dfs(int i, int A, int L, int n, int[][][] mem) {
            if (i == n) return 1;
            if (mem[i][A][L] != 0) return mem[i][A][L];
            long res = dfs(i + 1, A, 0, n, mem);  //P
            if (A == 0) res += dfs(i + 1, 1, 0, n, mem); //A
            if (L < 2) res += dfs(i + 1, A, L + 1, n, mem); //L
            return mem[i][A][L] = (int) (res % 1000000007);
        }
    }

    /*#3 O(n) Time, literally same as #2, dp[i][A][L] means the number of valid records starting from i with
    A absents and L consecutive late before i.*/
    static class bottomUpOnSpace {
        public int checkRecord(int n) {
            int[][][] dp = new int[n + 1][2][3];
            dp[n] = new int[][]{{1, 1, 1}, {1, 1, 1}};
            int mod = 1000000007;
            for (int i = n - 1; i >= 0; i--)
                for (int A = 0; A < 2; A++)
                    for (int L = 0; L < 3; L++) {
                        dp[i][A][L] = dp[i + 1][A][0];
                        if (A == 0) dp[i][0][L] = (dp[i][0][L] + dp[i + 1][1][0]) % mod;
                        if (L < 2) dp[i][A][L] = (dp[i][A][L] + dp[i + 1][A][L + 1]) % mod;
                    }
            return dp[0][0][0];
        }
    }

    /*#4 Top down dp, dp[i][A][L] means the number of valid records until i with A absents and L lates.
    Compared with #3, this representation is more straigthforward and is easier for further optimization.
    dp[0][0][0] means empty string with 0 absents and 0 lates. The result is sum(dp[n][A][L]) 0<A<2, 0<L<3,
    since the problem asks for all valid sequences of length n. I generate n+1 because when A=1, according to line 7,
    dp[i][1][0] += dp[i-1][1][L]. According to 8, dp[i][1][0] += dp[i-1][0][L]. So dp[i][1][0]=sum(dp[i-1][A][L]).*/
    static class topDownOnSpace {
        public int checkRecord(int n) {
            int[][][] dp = new int[n + 2][2][3];
            dp[0][0][0] = 1;
            int mod = 1000000007;
            for (int i = 1; i <= n + 1; i++) {
                for (int A = 0; A < 2; A++)
                    for (int L = 0; L < 3; L++) {
                        dp[i][A][0] = (dp[i][A][0] + dp[i - 1][A][L]) % mod;  //P
                        if (A == 1) dp[i][1][0] = (dp[i][1][0] + dp[i - 1][0][L]) % mod;  //A
                        if (L == 1 || L == 2) dp[i][A][L] = (dp[i][A][L] + dp[i - 1][A][L - 1]) % mod;  //L
                    }
            }
            return dp[n + 1][1][0];
        }
    }


}
