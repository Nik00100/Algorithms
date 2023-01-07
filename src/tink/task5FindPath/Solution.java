package tink.task5FindPath;

import java.io.*;
import java.util.*;

// Идея использовать Depth-First Search для поиска всех возможных путей из начального узла в конечный.
// Вначале строим граф, в зависимости от расписания работы аэропортов. Всего возможно 2^n расписаний,
// где n - общее количество аэропортов. И по графу ищем максимальный путь.

public class Solution {
    static int maxSize=-1;
    static List<Integer> unreachable = new ArrayList<>();

    static class Flight {
        int departure;
        int arrival;
        int parity;
        public Flight(int departure, int arrival, int parity) {
            this.departure = departure;
            this.arrival = arrival;
            this.parity = parity;
        }
    }

    private static List<List<Integer>> allPathsSourceTarget(List<List<Integer>> graph) {
        List<List<Integer>> res = new ArrayList<>();
        if(graph.size() == 0)
            return res;
        List<Integer> path = new ArrayList<>();
        //добавляем стартовый 0-ой узел
        path.add(0);
        dfs(graph, path, 0,res);
        return res;
    }
    private static void dfs(List<List<Integer>> graph, List<Integer> path, int node, List<List<Integer>> res) {
        //node - это индекс графа
        if (node == graph.size() - 1)
            //когда достигнет (graph.size()-1) добавляем в результирующий res
            res.add(new ArrayList<>(path));
        //производим обход из следующего узла
        for(int i : graph.get(node)) {
            path.add(i);
            dfs(graph, path, i,res);
            //осуществляем возврат
            path.remove(path.size() - 1);
        }
    }

    private static List<List<Integer>> getGraph (Flight[] flights, List<Integer> schedule) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i=0; i<schedule.size(); i++) {
            res.add(new ArrayList<>());
        }
        // заполняем граф возможных рейсов, в соответствии с расписанием
        for (int i =0; i < flights.length; i++) {
            int departure = flights[i].departure;
            int arrival = flights[i].arrival;
            int parity = flights[i].parity;
            if (schedule.get(departure-1)==parity)
                res.get(departure-1).add(arrival-1);
        }
        return res;
    }

    private static List<Integer> decToBinary(int dec, int n) {
        List<Integer> binaryNum = new ArrayList<>();
        // выполняем деление и записываем остаток
        while (dec > 0) {
            // сохраняем остаток
            binaryNum.add(dec % 2);
            dec = dec / 2;
        }
        // дополняем левые нули, если необходимо
        while (binaryNum.size() < n) {
            binaryNum.add(0);
        }
        Collections.reverse(binaryNum);
        return binaryNum;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = reader.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        Flight[] flights = new Flight[m];
        for (int i = 0; i<m; i++) {
            String[] uvt = reader.readLine().split(" ");
            flights[i] = new Flight(Integer.parseInt(uvt[0]),Integer.parseInt(uvt[1]),Integer.parseInt(uvt[2]));
        }

        int index=-1;
        int indexUnreachable=-1;
        // для каждого варианта расписания ищем возможные пути из начального в конечный город
        for (int i=0; i< (int) Math.pow(2,n); i++) {
            List<List<Integer>> allPath= allPathsSourceTarget(getGraph(flights,decToBinary(i,n)));

            if (allPath.size()==0 && decToBinary(i,n).get(n-1)==decToBinary(i,n).get(n-2)) {
                unreachable.add(i);
                indexUnreachable = i;
            }
            else if (allPath.size()!=0 && maxSize <= allPath.stream().mapToInt(list->list.size()).max().getAsInt()-1) {
                maxSize = allPath.stream().mapToInt(list->list.size()).max().getAsInt()-1;
                index = i;
            }
            allPath.clear();
        }

        index = unreachable.size()==0 ? index : indexUnreachable;
        maxSize = unreachable.size()==0 ? maxSize : -1;
        System.out.println(maxSize);
        decToBinary(index,n).stream().forEach(num->System.out.print(num));
    }
}
