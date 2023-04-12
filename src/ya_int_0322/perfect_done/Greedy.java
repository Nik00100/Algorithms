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

import java.io.*;
import java.util.*;

/**
 * Использую жадный алгоритм. Вначале сортирую скульптуры по времени приведения их в идеальное состояние.
 * Затем беру по очереди все скульптуры, начиная с первой, пока есть время.
 * */

public class Greedy {
    static class Sculpture {
        int timeToBecamePerfect;
        int index;

        public Sculpture(int timeToBecamePerfect, int index) {
            this.timeToBecamePerfect = timeToBecamePerfect;
            this.index = index;
        }
    }

    static List<Integer> findMaxSubsequence(Sculpture[] arr, long target) {
        int n = arr.length;

        Arrays.sort(arr, (o1, o2) -> o1.timeToBecamePerfect == o2.timeToBecamePerfect
                ? o1.index - o2.index : o1.timeToBecamePerfect - o2.timeToBecamePerfect);

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

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] s1 = reader.readLine().split(" ");
        int n = Integer.parseInt(s1[0]);
        int x = Integer.parseInt(s1[1]); // масса в килограммах идеальной скульптуры
        long t = Integer.parseInt(s1[2]);

        Sculpture[] sculptures = new Sculpture[n];
        String[] s2 = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            int mass = Integer.parseInt(s2[i]); // масса в килограммах k-ой скульптуры
            int timeToBecamePerfect = Math.abs(mass - x); // время для того, чтобы сделать k-ую скульптуру идеальной
            sculptures[i] = new Sculpture(timeToBecamePerfect, i + 1);
        }

        List<Integer> answer = findMaxSubsequence(sculptures, t);
        System.out.println(answer.size());
        StringBuilder sb = new StringBuilder();
        for (int num : answer) {
            sb.append(num).append(" ");
        }
        System.out.println(sb.toString().trim());

        reader.close();
    }
}

