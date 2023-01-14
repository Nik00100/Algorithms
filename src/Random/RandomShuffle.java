package Random;

import java.util.Arrays;
import java.util.Random;

public class RandomShuffle {
    static Random rand = new Random();

    private static int randRange(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    private static void swapAt(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int[] shuffle(int[] array) {
        for (int i = 0; i < array.length; i++) {
            swapAt(array, i, randRange(i, array.length));
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 5, 6, 7, 8, 9, 10};
        for (int i=0; i<5; i++) {
            System.out.println(Arrays.stream(shuffle(array)).boxed().toList());
        }
    }

}
