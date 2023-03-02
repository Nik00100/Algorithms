package ya_training.algo3_0.task2_17_02.divA.task3;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));

        String[] kn = reader.readLine().split(" ");
        int k = Integer.parseInt(kn[0]);
        int n = Integer.parseInt(kn[1]);

        int[] arrival = new int[n];
        int[] departure = new int[n];
        for (int i = 0; i < n; ++i) {
            String[] s = reader.readLine().split(" ");
            arrival[i] = Integer.parseInt(s[0]);
            departure[i] = Integer.parseInt(s[1]);
        }

        TreeSet<Integer> garages = new TreeSet<>();
        for (int i = 1; i <= k; ++i)
            garages.add(i);

        int[] ans = new int[n];
        TreeSet<Pair<Integer, Integer>> train_set = new TreeSet<>();
        for (int i = 0; i < n; ++i) {
            for (Iterator<Pair<Integer, Integer>> it = train_set.iterator(); it.hasNext();) {
                Pair<Integer, Integer> pair = it.next();
                if (pair.first < arrival[i]) {
                    for (Iterator<Pair<Integer, Integer>> x = it; x.hasNext() && x.next().first == pair.first;) {
                        garages.add(pair.second);
                        x.remove();
                    }
                } else
                    break;
            }
            if (garages.isEmpty()) {
                writer.println(0 + " " + (i + 1));
                writer.close();
                return;
            }
            ans[i] =garages.first();
            train_set.add(new Pair<>(departure[i], garages.first()));
            garages.remove(garages.first());
        }

        for (int i = 0; i < n; ++i)
            writer.println(ans[i]);

        reader.close();
        writer.close();
    }

    static class Pair<K extends Comparable<? super K>, V> implements Comparable<Pair<K, V>> {
        public final K first;
        public final V second;

        public Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ')';
        }

        @Override
        public int compareTo(Pair<K, V> o) {
            return first.compareTo(o.first);
        }
    }
}

