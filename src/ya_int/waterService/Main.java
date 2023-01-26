package ya_int.waterService;

import java.io.*;
import java.util.*;

public class Main {
    static class Order {
        int start;
        int finish;
        int cost;
        int duration;

        public Order(int start, int finish, int cost) {
            this.start = start;
            this.finish = finish;
            this.cost = cost;
            this.duration = finish - start;
        }
    }

    static int getSumOrderCostStartedInPeriod (List<Order> orders, int begin, int end) {
        int totalSum = 0;
        for (Order o : orders) {
            if (begin <= o.start && end >= o.start) {
                totalSum += o.cost;
            }
        }
        return totalSum;
    }

    static int getSumOrderDurationFinishedInPeriod (List<Order> orders, int begin, int end) {
        int totalSum = 0;
        for (Order o : orders) {
            if (begin <= o.finish && end >= o.finish) {
                totalSum += o.duration;
            }
        }
        return totalSum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Order> orders = new ArrayList<>();

        int n = Integer.parseInt(reader.readLine());
        for (int i=0; i<n; i++) {
            String[] s = reader.readLine().split(" ");
            orders.add(new Order(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Integer.parseInt(s[2])));
        }

        int m = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<m; i++){
            String[] s = reader.readLine().split(" ");
            int begin = Integer.parseInt(s[0]);
            int end = Integer.parseInt(s[1]);
            int command = Integer.parseInt(s[2]);
            int request;
            if (command == 1) {
                request = getSumOrderCostStartedInPeriod(orders,begin,end);
            } else {
                request = getSumOrderDurationFinishedInPeriod(orders,begin,end);
            }
            sb.append(request).append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        reader.close();
    }
}
