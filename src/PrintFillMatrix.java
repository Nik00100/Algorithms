
import java.util.Arrays;
import java.util.stream.Collectors;

public class PrintFillMatrix {
    static void fillMatrix(int[][] matrix, int value) {
        for (int i = 0; i < matrix.length; i++)
            Arrays.fill(matrix[i], value);
    }


    static void printMatrix(int[][] matrix) {
        Arrays.stream(matrix)
                .forEach(array-> System.out.println(Arrays.stream(array).boxed().collect(Collectors.toList())));
        System.out.println("*********************");
    }
}
