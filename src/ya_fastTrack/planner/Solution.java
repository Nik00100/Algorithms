package ya_fastTrack.planner;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    static class Task  {
        int start, duration, beginExecuted, workerId;
        public Task(int start, int queue, int duration) {
            this.start = start;
            this.duration = duration;
        }
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();

        List<Task> tasks = new ArrayList<>();
        Map<Integer, Queue<Task>> taskMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int start = in.nextInt();
            int queue = in.nextInt();
            int time = in.nextInt();

            Task task = new Task(start, queue, time);

            tasks.add(task);

            Queue<Task> taskList = taskMap.getOrDefault(queue, new LinkedList<>());
            taskList.add(task);
            taskMap.put(queue, taskList);
        }


        Map<Integer, Integer> idle = new HashMap<>(); // для учета простоя очередей
        for (int i = 1; i <= k; i++) {
            idle.put(i, 0);
        }

        //int workerReadyTime = tasks.get(0).start;
        Map<Integer, Integer> workers = new HashMap<>();
        for (int i = 1; i <= m; i++) {
            workers.put(i, tasks.get(i - 1).start);
        }

        for (int i = 0; i < n; i++) {

            int bestWorker = getBestChoice(workers).get(0); // выбрали работника
            int workerReadyTime = workers.get(bestWorker);

            updateIdleQueue(idle, taskMap);  // обновили idle
            int bestQueue = getBestQueue(idle, taskMap, workerReadyTime); // выбрали очередь

            Task task = taskMap.get(bestQueue).poll();

            int startTime = Math.max(workerReadyTime, task.start); // время, когда работник приступит к задаче

            task.beginExecuted = startTime; // назначаем время старта для задания
            task.workerId = bestWorker; // назначаем работника для выполнения задания

            idle.put(bestQueue, idle.get(bestQueue) + 1); // обновить счетчик очереди

            workerReadyTime = startTime + task.duration; // обновить время готовности работника
            workers.put(bestWorker, workerReadyTime); // и поместить в Map
        }

        for (Task task: tasks) {
            System.out.println(task.workerId + " " +task.beginExecuted);
        }
    }

    static void updateIdleQueue(Map<Integer, Integer> idle, Map<Integer, Queue<Task>> taskMap) {
        // обновляем idle map (удаляем если необходимо пустые очереди)
        List<Integer> emptyQueues = new ArrayList<>(taskMap.entrySet().stream()
                .filter(entry -> entry.getValue().isEmpty())
                .map(Map.Entry::getKey).collect(Collectors.toList()));
        for (int q : emptyQueues) {
            idle.remove(q);
        }
    }

    static List<Integer> getBestChoice(Map<Integer, Integer> map) {
        int minUsage = map.values().stream().mapToInt(i -> i).min().getAsInt(); // счетчик с наименьшим использованием
        List<Integer> bestChoises = map.entrySet().stream()
                .filter(entry -> entry.getValue() == minUsage)
                .map(Map.Entry::getKey).sorted().collect(Collectors.toList()); // список ключей с наименьшим использованием
        return bestChoises;
    }

    static int getBestQueue(Map<Integer, Integer> idle, Map<Integer, Queue<Task>> taskMap, int workerReadyTime) {
        // пройтись по всем bestchoices
        // и из taskMap выбирать минимальное время начала задачи
        // при этом выбирать из задач, время которых <= workerReadyTime

        List<Integer> bestChoises = getBestChoice(idle);

        List<Integer> answer = new ArrayList<>();
        for (int queueNum : bestChoises) {
            if (taskMap.get(queueNum).peek().start <= workerReadyTime) {
                answer.add(queueNum);
            }
        }
        Collections.sort(answer);

        return answer.size() != 0 ? answer.get(0) : bestChoises.get(0);
    }

}
