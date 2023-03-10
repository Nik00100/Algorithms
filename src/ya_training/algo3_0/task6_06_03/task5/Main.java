package ya_training.algo3_0.task6_06_03.task5;

import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> lines = new ArrayList<>(); // Список линий
    static int n; // Количество станций метро
    static int a, b; // Номера начальной и конечной станции
    static int[] used; // Массив флагов, помечающих использованные станции
    static int[] dist; // Массив расстояний от начальной станции

    // Обход в ширину
    public static int bfs(int start, int end) {
        used = new int[n + 1];
        dist = new int[n + 1];
        Arrays.fill(dist, -1);

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        used[start] = 1;
        dist[start] = 0;

        while (!q.isEmpty()) {
            int v = q.poll();

            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).contains(v)) {
                    for (int j = 0; j < lines.get(i).size(); j++) {
                        int u = lines.get(i).get(j);

                        if (used[u] == 0) {
                            q.add(u);
                            used[u] = 1;
                            dist[u] = dist[v] + 1;

                            if (u == end) {
                                return dist[end] - 1;
                            }
                        }
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        int m = sc.nextInt();

        for (int i = 0; i < m; i++) {
            int p = sc.nextInt();
            ArrayList<Integer> line = new ArrayList<>();

            for (int j = 0; j < p; j++) {
                line.add(sc.nextInt());
            }

            lines.add(line);
        }

        a = sc.nextInt();
        b = sc.nextInt();

        int result = bfs(a, b);

        System.out.println(result);
    }


}

