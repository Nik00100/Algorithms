package task5FindPath;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("input5.txt");
        OutputStream outputStream = System.out;
        InputReader inputReader = new InputReader(inputStream);
        OutputWriter outputWriter = new OutputWriter(outputStream);
        FindPath findPath = new FindPath();
        findPath.solve(inputReader,outputWriter);

    }

    static class FindPath {
        int maxSize=-1;
        List<Integer> unreachable = new ArrayList<>();

        public void solve(InputReader in, OutputWriter out) throws IOException {
            String[] nm = in.readLine().split(" ");
            int n = Integer.parseInt(nm[0]);
            int m = Integer.parseInt(nm[1]);

            Flight[] flights = new Flight[m];
            for (int i = 0; i<m; i++) {
                String[] uvt = in.readLine().split(" ");
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
            out.println(maxSize);
            decToBinary(index,n).stream().forEach(num->out.print(num));

            in.close();
            out.close();
        }

        private List<List<Integer>> getGraph (Flight[] flights, List<Integer> schedule) {
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

        private List<Integer> decToBinary(int dec, int n) {
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


        private List<List<Integer>> allPathsSourceTarget(List<List<Integer>> graph) {
            List<List<Integer>> res = new ArrayList<>();
            if(graph.size() == 0)
                return res;
            List<Integer> path = new ArrayList<>();
            //добавляем стартовый 0-ой узел
            path.add(0);
            dfs(graph, path, 0,res);
            return res;
        }
        private void dfs(List<List<Integer>> graph, List<Integer> path, int node, List<List<Integer>> res) {
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

    }

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

    static class InputReader {
        private InputStream stream;

        private BufferedReader bufferedReader;

        public InputReader(InputStream stream) {
            this.bufferedReader = new BufferedReader(new InputStreamReader(stream));
            this.stream = stream;
        }

        public String readLine() throws IOException {
            return bufferedReader.readLine();
        }

        public int readIntFromLine() throws IOException {
            return Integer.parseInt(bufferedReader.readLine());
        }

        public void close() throws IOException {
            bufferedReader.close();
            stream.close();
        }
    }

    static class OutputWriter {
        private OutputStream outputStream;
        private PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            this.outputStream=outputStream;
            this.writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public void print(int n) {
            writer.write(String.valueOf(n));
        }

        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0) {
                    writer.print("");
                }
                writer.print(objects[i].toString());
            }
        }

        public void println(Object... objects) {
            print(objects);
            writer.println();
        }

        public void close() throws IOException {
            writer.close();
            outputStream.close();
        }
    }
}

