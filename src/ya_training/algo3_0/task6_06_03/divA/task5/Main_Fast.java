package ya_training.algo3_0.task6_06_03.divA.task5;


import java.io.*;
import java.util.*;

public class Main_Fast {
    private static int K;
    static int state (int vertex, int map) {
        return vertex * (K + 1) + map;
    }

    static int vertexFromState (int state) {
        return state / (K + 1);
    }

    static int mapFromState (int state) {
        return state % (K + 1);
    }

    static void addLink(int startState, int endState, List<int[]>[] graph, int weight) {
        int[] startEnd = new int[2];
        startEnd[0] = endState;
        startEnd[1] = weight;
        if (graph[startState] == null) {
            graph[startState] = new ArrayList<>();
        }
        graph[startState].add(startEnd);
    }

    static void fillGraph(int v, int u, int map, List<int[]>[] graph) {
        // state (v, map) and state (u, map)
        int vMap = state(v, map);
        int uMap = state(u, map);

        // links {u, v} and {v, u}
        addLink(vMap, uMap, graph, 0);
        addLink(uMap, vMap, graph, 0);

        // state (u, 0) and links {u, 0} , {0, u}
        int u0 = state(u, 0);
        addLink(uMap, u0, graph, 0);
        addLink(u0, uMap, graph, 1);

        // // state (v, 0) and links {v, 0} , {0, v}
        int v0 = state(v, 0);
        addLink(vMap, v0, graph, 0);
        addLink(v0, vMap, graph, 1);
    }

    static int bfs(int src, int n, int k, List<int[]>[] graph) {
        // initialize distances from given source
        int[][] distance = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++)
            Arrays.fill(distance[i], Integer.MAX_VALUE);

        boolean[] visited = new boolean[(n + 1) * (k + 1)];
        Arrays.fill(visited, false);

        // double ended queue to do BFS 0-1
        Deque<Integer> stateQueue = new ArrayDeque<>();
        distance[src][0] = 0;
        stateQueue.addFirst(state(src,0));

        while (!stateQueue.isEmpty()) {
            int curState = stateQueue.pollFirst();
            if (!visited[curState]) {
                int curVertex = vertexFromState(curState);
                int curMap = mapFromState(curState);

                for (int[] vm : graph[curState]) {
                    int nextState = vm[0];
                    // get next vertex and map
                    int next = vertexFromState(nextState);
                    int map = mapFromState(nextState);
                    // get edge weight
                    int weight = vm[1];

                    //System.out.println(nextState);

                    // checking for optimal distance
                    if (distance[next][map] > distance[curVertex][curMap] + weight ) {

                        // update the distance
                        distance[next][map] = distance[curVertex][curMap] + weight;

                        // put 0 weight edges to front and 1 weight edges to back so that vertices
                        // are processed in increasing order of weight
                        if (weight == 0) {
                            stateQueue.addFirst(nextState);
                        } else {
                            stateQueue.addLast(nextState);
                        }
                    }
                }
            }
            visited[curState] = true;
        }

        return distance[n][0] == Integer.MAX_VALUE ? -1 : distance[n][0];
    }

    public static void main(String[] args) throws IOException {


        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        String[] s = reader.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        K = Integer.parseInt(s[1]);

        List<int[]>[] graph = new ArrayList[(n + 1) * (K + 1)];

        for (int i = 1; i <= K; i++) {
            int r = Integer.parseInt(reader.readLine());
            for (int j = 0; j < r; j++) {
                String[] ab = reader.readLine().split(" ");
                int a = Integer.parseInt(ab[0]);
                int b = Integer.parseInt(ab[1]);
                fillGraph(a, b, i, graph);
            }
        }

        int src = 1;//source node

        System.out.println(bfs(src, n, K, graph));

        reader.close();
    }

}
