import java.util.Arrays;
import java.util.stream.Collectors;

public class PrintMatrix {
    static void printMatrix(int[][] matrix) {
        Arrays.stream(matrix)
                .forEach(array-> System.out.println(Arrays.stream(array).boxed().collect(Collectors.toList())));
    }
}
