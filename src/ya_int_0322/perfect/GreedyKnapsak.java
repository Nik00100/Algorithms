package ya_int_0322.perfect;

import java.util.*;

public class GreedyKnapsak {
    static List<Integer> findMaxSubsequence(int[] arr, long target) {
        int n = arr.length;
        Arrays.sort(arr);
        long currSum = 0;
        List<Integer> currSubsequence = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (currSum + arr[i] <= target) {
                currSum += arr[i];
                currSubsequence.add(arr[i]);
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
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            a[i] = Math.abs(num - x);
        }

        System.out.println(Arrays.stream(a).boxed().toList());

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
