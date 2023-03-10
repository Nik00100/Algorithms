public class ProgramTimeCount {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        //methodToTime();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println(duration);
    }
}
