package ya_fastTrack.planner;

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
import java.util.stream.Collectors;

public class Main {
    static class Task {
        int id;
        int queuingTime;
        int duration;
        long startExecuted;
        Worker worker;
        public Task(int id, int queuingTime, int duration) {
            this.id = id;
            this.queuingTime = queuingTime;
            this.duration = duration;
            this.startExecuted = 0;
        }
        void assignWorker(Worker worker) {
            this.worker = worker;
            this.startExecuted = worker.getTimeWhenReady() < this.queuingTime ? this.queuingTime : worker.timeWhenReady;
        }
    }

    static class Worker {
        int id;
        long timeWhenReady;
        public Worker(int id) {
            this.id = id;
            this.timeWhenReady = 1;
        }
        void updateTimeWhenReady(Task task) {this.timeWhenReady = task.startExecuted + task.duration;}
        int getId() {return id;}
        long getTimeWhenReady() {return timeWhenReady;}
    }

    static class QueueManager {
        int queueNumber;
        Queue<Task> tasksQueue;
        int usage;
        public QueueManager(int queueNumber) {
            this.queueNumber = queueNumber;
            this.tasksQueue = new LinkedList<>();
            this.usage = 0;
        }
        void updateManager() {usage++;}
        public int getNextQueuingTime() {
            return tasksQueue.isEmpty() ? 0 : tasksQueue.peek().queuingTime;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] nlm = reader.readLine().split(" ");

        int n = Integer.parseInt(nlm[0]);
        int m = Integer.parseInt(nlm[1]);
        int k = Integer.parseInt(nlm[2]);

        PriorityQueue<Worker> workersQueue = new PriorityQueue<>(Comparator.comparing(Worker::getTimeWhenReady)
                .thenComparing(Worker::getId));
        for (int i = 1; i <= m; i++) {
            workersQueue.offer(new Worker(i));
        }

        QueueManager[] managers = new QueueManager[k];
        List<Task> tasksList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] s = reader.readLine().split(" ");
            int queuingTime = Integer.parseInt(s[0]);
            int queueNumber = Integer.parseInt(s[1]) - 1;
            int duration = Integer.parseInt(s[2]);

            Task task = new Task(i + 1, queuingTime, duration);
            if (managers[queueNumber] == null) {
                managers[queueNumber] = new QueueManager(queueNumber);
            }
            tasksList.add(task);
            managers[queueNumber].tasksQueue.offer(task);
        }


        if (m >= n) {
            for (int i = 0; i < n; i++) {
                System.out.println(String.format("%d %d", i + 1, tasksList.get(i).queuingTime));
            }
        } else {
            Set<QueueManager> managerSet = Arrays.stream(managers).collect(Collectors.toSet());
            for (int i = 0; i < n; i++){
                Worker worker = workersQueue.poll();
                long timeWhenReady = worker.getTimeWhenReady();

                PriorityQueue<QueueManager> managerQueue = new PriorityQueue<>((a,b) -> {
                    if (a.usage != b.usage) {
                        return a.usage - b.usage;
                    } else {
                        if (timeWhenReady < a.getNextQueuingTime() && timeWhenReady >= b.getNextQueuingTime()) {
                            return a.getNextQueuingTime() - b.getNextQueuingTime();
                        } else {
                            return a.queueNumber - b.queueNumber;
                        }
                    }
                });

                for (QueueManager manager : managerSet) {
                    managerQueue.offer(manager);
                }

                QueueManager qm = managerQueue.poll();
                managerSet.remove(qm);

                Task task = qm.tasksQueue.poll();

                task.assignWorker(worker);
                worker.updateTimeWhenReady(task);
                qm.updateManager();

                workersQueue.offer(worker);

                if (!qm.tasksQueue.isEmpty()) managerSet.add(qm);
            }

            for (Task t : tasksList) {
                System.out.println(t.worker.id + " " + t.startExecuted);
            }
        }

        reader.close();
    }
}

