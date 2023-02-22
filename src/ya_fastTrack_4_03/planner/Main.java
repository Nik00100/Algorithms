package ya_fastTrack_4_03.planner;

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

        reader.close();
    }
}

