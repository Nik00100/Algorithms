package ya_fastTrack_4_03.planner;

/*Планировщик 100 баллов
Имеется m исполнителей, пронумерованных числами от 1 до m, и k очередей задач, пронумерованных числами от 1 до k.
Изначально очереди пусты. Системе предстоит выполнить n задач: i-я задача встанет в очередь с номером qi на si-й секунде и потребует
ti секунд на выполнение.

Каждую секунду происходит следующее:

    В конец некоторых очередей могут добавиться новые задачи (но не более одной задачи в каждую очередь)

    Пока это возможно, выбирается незанятый исполнитель с наименьшим номером и непустая очередь, из которой дольше всего
    не забирались задачи. Если таких очередей несколько, то выбирается очередь с наименьшим номером. Исполнитель забирает задачу
    из очереди и становится занятым на время ее выполнения.

Для каждой задачи определите, когда и какой исполнитель начнет ее выполнять.

Формат ввода
В первой строке находятся числаn,m,k (1≤n,m,k≤2⋅10^5). В каждой i-й из последующих n строк находятся 3 числа:
si, qi и ti(1≤ si,ti ≤10^9; 1≤ qi ≤k) — время в секундах постановки i-й задачи в очередь, номер очереди и время в секундах,
необходимое на выполнение задачи, соответственно.
Гарантируется, что si ≤ sj при i<j.

Формат вывода
Для каждой задачи в порядке их следования во входных данных выведите по 2 числа: номер исполнителя и время в секундах,
когда он заберет эту задачу из ее очереди. То есть суммарно нужно вывести 2n чисел.

Пример 1
Ввод
5 1 2
1 1 5
1 2 3
2 2 1
3 1 2
4 1 3
Вывод
1 1
1 6
1 11
1 9
1 12
Пример 2
Ввод
5 2 2
1 1 5
1 2 3
2 2 1
3 1 2
4 1 3
Вывод
1 1
2 1
1 6
2 4
2 6
Пример 3
Ввод
5 2 2
1 1 1
1 2 5
2 2 1
5 1 3
6 1 3
Вывод
1 1
2 1
1 2
1 5
2 6

Примечание
Система оценки: в задаче используется потестовая оценка. Баллы начисляются за долю пройденных тестов выше 10%, т.е.
за 60% пройденных тестов будет начислено 55% баллов, а за 55% — 50%.

Ограничение памяти 256.0 Мб
Ограничение времени 15 с
Ввод стандартный ввод или input.txt
Вывод стандартный вывод или output.txt*/

import java.io.*;
import java.util.*;

public class Main {

    static int time = 1;

    static class Task {
        int queueId;
        int timeout;
        int start;
        int duration;

        public Task(int start, int duration) {
            this.start = start;
            this.duration = duration;
        }

        int getTimeOut() {
            return time - start;
        }

        @Override
        public String toString() {return "T{" + "s=" + start + ", d=" + duration + '}';}
    }

    static class WorkQueue {
        int id;
        Queue<Task> tasks;

        public WorkQueue(int id) {
            this.id = id;
            tasks = new LinkedList<>();

        }

        int getTimeOut() {
            if (tasks.size() == 0) {
                return  0;
            } else {
                return time - tasks.peek().start;
            }
        }

        @Override
        public String toString() {return "Q{" + "id=" + id + ", " + tasks + '}';}
    }



    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] nlm = reader.readLine().split(" ");

        int n = Integer.parseInt(nlm[0]);
        int l = Integer.parseInt(nlm[1]);
        int m = Integer.parseInt(nlm[2]);

        PriorityQueue<Integer> workers = new PriorityQueue<>();
        for (int i = 1; i <= m; i++) {
            workers.offer(i);
        }

        System.out.println(workers);

        PriorityQueue<WorkQueue> q = new PriorityQueue<>((a,b) -> a.getTimeOut() - b.getTimeOut());
        List<WorkQueue> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] s = reader.readLine().split(" ");
            int start = Integer.parseInt(s[0]);
            int duration = Integer.parseInt(s[3]);
            WorkQueue.Task task = new WorkQueue.Task(start, duration);
            int id = Integer.parseInt(s[1]);
            WorkQueue workQueue = new WorkQueue(id);
        }



        reader.close();
    }
}
