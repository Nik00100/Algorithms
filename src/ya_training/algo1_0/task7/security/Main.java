package ya_training.algo1_0.task7.security;

/*На секретной военной базе работает N охранников. Сутки поделены на 10000 равных промежутков времени, и известно когда каждый
из охранников приходит на дежурство и уходит с него. Например, если охранник приходит в 5, а уходит в 8, то значит,
что он был в 6, 7 и 8-ой промежуток (а в 5-й нет!!!).

Укажите, верно ли что для данного набора охранников, объект охраняется в любой момент времени хотя бы одним охранником и
удаление любого из них приводит к появлению промежутка времени, когда объект не охраняется.

Формат ввода
В первой строке входного файла записано натуральное число K (1≤K≤100) — количество тестов в файле.
Каждый тест начинается с числа N (1≤N≤10000), за которым следует N пар неотрицательных целых
чисел A и B — время прихода на дежурство и ухода (0≤A≤B≤10000) соответствующего охранника.

Формат вывода
Выведите K строк, где в M-ой строке находится слово Accepted, если M-ый набор охранников удовлетворяет описанным выше условиям.
В противном случае выведите Wrong Answer.

Пример
Ввод	Вывод
2
3 0 3000 2500 7000 2700 10000
2 0 3000 2700 10000

Wrong Answer
Accepted*/

import java.io.*;
import java.util.*;

public class Main {

    enum Type {
        IN(-1),
        OUT(2000_000_000);
        private int num;
        Type(int num) {
            this.num = num;
        }
        public int getNum() {
            return num;
        }
    }

    static class Event {
        int time;
        Type type;
        int id;
        public Event(int time, Type type, int id) {
            this.time = time;
            this.type = type;
            this.id = id;
        }
        @Override
        public String toString() {return "{" + "time=" + time + ", type=" + type + ", id=" + id + '}';}
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input8.txt")));

        int k = Integer.parseInt(reader.readLine());

        for (int i = 0; i < k; i++) {
            List<Event> events = new ArrayList<>();
            String[] nt = reader.readLine().split(" ");
            int n = Integer.parseInt(nt[0]);
            for (int j = 1; j < 2*n; j+=2) {
                int tIn = Integer.parseInt(nt[j]);
                int tOut = Integer.parseInt(nt[j + 1]);
                int index = (j - 1) / 2;
                events.add(new Event(tIn, Type.IN, index));
                events.add(new Event(tOut, Type.OUT, index));
            }

            Collections.sort(events, (a,b) -> a.time == b.time ? a.type.getNum() - b.type.getNum() : a.time - b.time);
            //System.out.println(events);

            Set<Integer> goodSeq = new HashSet<>();
            Set<Integer> nowSeq = new HashSet<>();
            boolean flag = true;
            int prevTime = -1;

            for (Event event : events) {
                if (event.time != 0 && nowSeq.size() == 0) { // нет охранников в этот момент времени, кроме случая 0
                    flag = false;
                    break;
                }
                if (event.time != prevTime && nowSeq.size() == 1) { // в какой-то момент объект охранялся одним охранником
                    goodSeq.addAll(nowSeq);
                }
                if (event.type.equals(Type.IN)) {
                    nowSeq.add(event.id);
                }
                else {
                    nowSeq.remove(event.id);
                }

                prevTime = event.time;
            }

            if (events.get(events.size() - 1).time != 10000) { // если ушел раньше
                flag = false;
            }
            if (flag && goodSeq.size() == n) {
                System.out.println("Accepted");
            } else {
                System.out.println("Wrong Answer");
            }
        }

        reader.close();
    }
}
