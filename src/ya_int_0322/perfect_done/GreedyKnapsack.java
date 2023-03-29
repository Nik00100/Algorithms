package ya_int_0322.perfect_done;

/*Пример 1
Ввод
3 5 2
5 10 6
Вывод
2
1 3
Пример 2
Ввод
5 19 32
36 10 72 4 50
Вывод
2
2 4
Пример 3
Ввод
4 25 10
1 10 42 9
Вывод
0*/

import java.util.*;

public class GreedyKnapsack {
    static class Country {
        int timeToBecamePerfect;
        int index;

        public Country(int timeToBecamePerfect, int index) {
            this.timeToBecamePerfect = timeToBecamePerfect;
            this.index = index;
        }

        @Override
        public String toString() {
            return "{" +
                    "time=" + timeToBecamePerfect +
                    ", index=" + index +
                    '}';
        }
    }
    static List<Integer> findMaxSubsequence(Country[] arr, long target) {
        int n = arr.length;

        Arrays.sort(arr, (o1, o2) -> o1.timeToBecamePerfect == o2.timeToBecamePerfect
                ? o1.index - o2.index : o1.timeToBecamePerfect - o2.timeToBecamePerfect);

        System.out.println(Arrays.stream(arr).toList());

        long currSum = 0;
        List<Integer> currSubsequence = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (currSum + arr[i].timeToBecamePerfect <= target) {
                currSum += arr[i].timeToBecamePerfect;
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
        Country[] a = new Country[n];
        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            int time = Math.abs(num - x);
            a[i] = new Country(time, i + 1);
        }

        System.out.println(Arrays.stream(a).toList());

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

