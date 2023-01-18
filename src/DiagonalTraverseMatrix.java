/*Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.
Input: int[][] mat = {
            {1,2,3},
            {4,5,6},
            {7,8,9}
            };
Output: [1,2,4,7,5,3,6,8,9]


The basic idea here is that while changing the direction
from moving up to moving down, the element on the right is always the first to traverse, whereas
from moving down to moving up, the element below the current one is always the first to traverse.*/


import Geometry.DfsBombDetonatesRadius.Solution;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DiagonalTraverseMatrix {

        static public int[] findDiagonalOrder(int[][] matrix) {
            if(matrix.length == 0) return new int[0];

            int rows = matrix.length;
            int columns = matrix[0].length;
            int[] nums = new int[rows * columns];

            int currentRow = 0;
            int currentColumn = 0;
            boolean goUp = true;


            for(int i = 0; i < nums.length; i++)
            {
                if(goUp) // moving up
                {
                    nums[i] = matrix[currentRow--][currentColumn++];

                    // exceed the boundary
                    if(!(currentRow >= 0 && currentColumn <= columns - 1))
                    {
                        // return to the previous valid position
                        currentRow++;
                        currentColumn--;

                        // Going to the element right to it(same row, next column) is always preferable
                        // while changing the direction from moving up to moving down
                        // unless the next column is invalid
                        if(currentColumn < columns - 1) currentColumn++;
                        else currentRow++;

                        // will move down for next iteration
                        goUp = false;
                    }
                }
                else // moving down
                {
                    nums[i] = matrix[currentRow++][currentColumn--];

                    // exceed the boundary
                    if(!(currentRow <= rows - 1 && currentColumn >= 0))
                    {
                        // return to the previous valid position
                        currentRow--;
                        currentColumn++;

                        // Going to the element below it(same column, next row) is always preferable
                        // while changing the direction from moving down to moving up
                        // unless the next row is invalid
                        if(currentRow < rows - 1) currentRow++;
                        else currentColumn++;

                        // will move up for next iteration
                        goUp = true;
                    }
                }
            }
            return nums;
        }

    static void printMatrix(int[][] matrix) {
        Arrays.stream(matrix)
                .forEach(array-> System.out.println(Arrays.stream(array).boxed().collect(Collectors.toList())));
    }



    public static void main(String[] args) {
        int[][] mat = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        printMatrix(mat);
        System.out.println(Arrays.stream(findDiagonalOrder(mat)).boxed().toList());

    }

}
