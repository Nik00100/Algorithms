package tink.task1RepeatedChars;

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String departmentName = reader.readLine();
        String painting = reader.readLine();

        int result = 0;
        int i = 0;

        if (departmentName.replaceAll(" ", "").length() != n || painting.length() != n) {
            System.out.println("Проверьте введенные данные");
        } else {
            while (i + 1 < n) {
                char temp = painting.charAt(i);
                if (painting.charAt(i + 1) != temp) {
                    i++;
                    continue;
                }
                while (i + 1 < n && painting.charAt(i + 1) == temp) {
                    i++;
                }
                result++;
            }
        }
        System.out.println(result);
    }
}
