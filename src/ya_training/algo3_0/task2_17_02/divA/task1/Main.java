package ya_training.algo3_0.task2_17_02.divA.task1;



import java.io.*;
import java.util.*;

public class Main {

    static int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        Deque<Integer> q = new ArrayDeque<>(); // Max queue

        for (int i = 0; i < nums.length; ++i) {
            while (!q.isEmpty() && q.peekLast() > nums[i])
                q.pollLast();
            q.offerLast(nums[i]);
            if (i >= k && nums[i - k] == q.peekFirst()) // Out of bound
                q.pollFirst();
            if (i >= k - 1)
                ans[i - k + 1] = q.peekFirst();
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {

        //long startTime = System.currentTimeMillis();

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

        String[] nk = reader.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        int[] arr = new int[n];
        String[] s = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        for (int num : maxSlidingWindow(arr, k)) {
            writer.write(String.valueOf(num));
            writer.write('\n');
        }

        //long endTime = System.currentTimeMillis();
        //System.out.println(endTime - startTime);

        reader.close();
        writer.close();
    }
}
