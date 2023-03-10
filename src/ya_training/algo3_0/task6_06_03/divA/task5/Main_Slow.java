package ya_training.algo3_0.task6_06_03.divA.task5;

import java.io.*;
import java.util.*;

public class Main_Slow {

    static class Link {
        String to; // the ending vertex
        int weight; // the weight of the edge

        public Link(String to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Link link = (Link) o;
            return weight == link.weight && Objects.equals(to, link.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(to, weight);
        }
    }

    static String getState(int vertex, int map) {return vertex + " " + map;}

    static int getVertexFromState(String state) {return Integer.parseInt(state.split(" ")[0]);}

    static int getMapFromState(String state) {return Integer.parseInt(state.split(" ")[1]);}

    static void addEdge(String start, String end, Map<String,Set<Link>> graph, int weight) {
        Set<Link> startEnd = graph.getOrDefault(start, new HashSet<>());
        startEnd.add(new Link(end, weight));
        graph.put(start, startEnd);
    }

    static void buildGraph(int v, int u, int map, Map<String,Set<Link>> graph) {
        // state (v, map) and state (u, map)
        String vMap = getState(v, map);
        String uMap = getState(u, map);

        // links {u, v} and {v, u}
        addEdge(vMap, uMap, graph, 0);
        addEdge(uMap, vMap, graph, 0);

        // state (u, 0) and links {u, 0} , {0, u}
        String u0 = getState(u, 0);
        addEdge(uMap, u0, graph, 0);
        addEdge(u0, uMap, graph, 1);

        // // state (v, 0) and links {v, 0} , {0, v}
        String v0 = getState(v, 0);
        addEdge(vMap, v0, graph, 0);
        addEdge(v0, vMap, graph, 1);
    }

    static int bfs(int src, int n, int k, Map<String,Set<Link>> graph) {
        // initialize distances from given source
        int[][] distance = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++)
            Arrays.fill(distance[i], Integer.MAX_VALUE);

        Set<String> visited = new HashSet<>();

        // double ended queue to do BFS 0-1
        Deque<String> stateQueue = new ArrayDeque<>();
        distance[src][0] = 0;
        stateQueue.addFirst(getState(src,0));

        while (!stateQueue.isEmpty()) {
            String curState = stateQueue.pollFirst();
            if (!visited.contains(curState)) {
                int curVertex = getVertexFromState(curState);
                int curMap = getMapFromState(curState);

                for (Link link : graph.get(curState)) {

                    // get next vertex and map
                    int next = getVertexFromState(link.to);
                    int map = getMapFromState(link.to);
                    // get edge weight
                    int weight = link.weight;

                    // checking for optimal distance
                    if (distance[next][map] > distance[curVertex][curMap] + weight) {

                        // update the distance
                        distance[next][map] = distance[curVertex][curMap] + weight;

                        // put 0 weight edges to front and 1 weight edges to back so that vertices
                        // are processed in increasing order of weight
                        if (weight == 0) {
                            stateQueue.addFirst(link.to);
                        } else {
                            stateQueue.addLast(link.to);
                        }
                    }
                }
            }
            visited.add(curState);
        }

        return distance[n][0] == Integer.MAX_VALUE ? -1 : distance[n][0];
    }

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        String[] s = reader.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);

        Map<String,Set<Link>> graph = new HashMap<>();

        for (int i = 1; i <= k; i++) {
            int r = Integer.parseInt(reader.readLine());
            for (int j = 0; j < r; j++) {
                String[] ab = reader.readLine().split(" ");
                int a = Integer.parseInt(ab[0]);
                int b = Integer.parseInt(ab[1]);
                buildGraph(a, b, i, graph);
            }
        }


        int src = 1;//source node

        System.out.println(bfs(src, n, k, graph));

        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        System.out.println(duration);

        reader.close();
    }

}
