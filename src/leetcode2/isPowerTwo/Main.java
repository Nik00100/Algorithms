package leetcode2.isPowerTwo;

public class Main {
    static boolean isPowerTwo(int n) {
        if (n == 1) return true;
        if (n == 0 || n == -1 || n % 2 != 0) return false;
        while (n > 1) {
            if (n % 2 != 0) return false;
            n = n >> 1;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPowerTwo(-16));
    }
}
