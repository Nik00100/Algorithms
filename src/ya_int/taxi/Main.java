package ya_int.taxi;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class Main {
    static class Order {
        LocalDateTime time;
        String status;

        public Order(LocalDateTime time, String status) {
            this.time = time;
            this.status = status;
        }

       /* @Override
        public String toString() {
            return "Order{" +
                    "time=" + time +
                    ", status='" + status + '\'' +
                    '}';
        }*/
    }

    private static long getDurationInMinutes (LocalDateTime d1, LocalDateTime d2) {
        Duration duration = Duration.between(d1, d2);
        return Math.abs(duration.toMinutes());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer,List<Order>> map = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy D H:m");
        int n = Integer.parseInt(reader.readLine());
        for (int i=0; i<n; i++) {
            String[] s = reader.readLine().split(" ");
            int rocketNumber = Integer.parseInt(s[3]);
            List<Order> orderList = map.getOrDefault(rocketNumber,new ArrayList<>());
            String dayTime = new StringBuilder().append("1000").append(" ").append(s[0]).append(" ").append(s[1]).append(":").append(s[2]).toString();
            Order order = new Order(LocalDateTime.parse(dayTime, formatter), s[4]);
            orderList.add(order);
            map.put(rocketNumber,orderList);
        }

        Comparator<Order> comparator = Comparator.comparing(order -> order.time);
        comparator = comparator.thenComparing(order -> order.status);

        StringBuilder sb = new StringBuilder();
        for (List<Order> list : map.values()) {
            Collections.sort(list,comparator);
            LocalDateTime start = list.get(0).time;
            LocalDateTime end;
            long total = 0;
            for (int i=1; i<list.size(); i++) {
                if (list.get(i).status.equals("C") || list.get(i).status.equals("S")) {
                    end = list.get(i).time;
                    total += getDurationInMinutes(start,end);
                    if (i+1<list.size()) start = list.get(i+1).time;
                }
            }
            sb.append(total).append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        reader.close();
    }
}
