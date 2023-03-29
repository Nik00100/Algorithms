package ya_int_0322.perfect_done.q;

import java.util.*;

public class Main {
    static class C {
        int time;
        int index;

        public C(int time, int index) {
            this.time = time;
            this.index = index;
        }
    }
    static List<Integer> findMaxSubsequence(C[] arr, long target) {
        int n = arr.length;
        Arrays.sort(arr, (o1, o2) -> o1.time == o2.time ? o1.index - o2.index : o1.time - o2.time);
        long currSum = 0;
        List<Integer> currSubsequence = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (currSum + arr[i].time <= target) {
                currSum += arr[i].time;
                currSubsequence.add(arr[i].index);
            } else {
                break;
            }
        }
        return currSubsequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = scanner.nextInt();
        long t = scanner.nextLong();
        C[] a = new C[n];
        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            int time = Math.abs(num - x);
            a[i] = new C(time, i + 1);
        }

        List<Integer> answer = findMaxSubsequence(a, t);
        System.out.println(answer.size());
        StringBuilder sb = new StringBuilder();
        for (int num : answer) {
            sb.append(num).append(" ");
        }
        System.out.println(sb.toString().trim());

        scanner.close();
    }
}