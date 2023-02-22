package ya_fastTrack_4_03.planner;


import java.util.Scanner;

public class GPT {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        String s = sc.next();
        int[] freq = new int[26]; // frequency array to count the occurrences of each letter
        int left = 0, right = 0, maxLen = 0, maxFreq = 0;

        while (right < s.length()) {
            // expand the window to the right
            freq[s.charAt(right) - 'a']++;
            maxFreq = Math.max(maxFreq, freq[s.charAt(right) - 'a']);
            right++;

            // shrink the window from the left
            while (right - left - maxFreq > k) {
                freq[s.charAt(left) - 'a']--;
                left++;
                maxFreq = 0;
                for (int i = 0; i < 26; i++) {
                    maxFreq = Math.max(maxFreq, freq[i]);
                }
            }

            // update the maximum length
            maxLen = Math.max(maxLen, right - left);
        }

        System.out.println(maxLen);
    }
}


