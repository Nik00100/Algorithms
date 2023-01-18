package Search_SlidingWindow_QuickSelect_TwoPointer.TwoPointerPaintHouse;

/*There are a row of n houses, each house can be painted with one of the k colors.
 The cost of painting each house with a certain color is different.
 You have to paint all the houses such that no two adjacent houses have the same color.

 The cost of painting each house with a certain color is represented by a n x k cost matrix.

 For example,
 costs0 is the cost of painting house 0 with color 0;
 costs1 is the cost of painting house 1 with color 2, and so on...

 Find the minimum cost to paint all houses.
 Note: All costs are positive integers.

 Example:

 Input: [[1,5,3],[2,9,4]]
 Output: 5
 Explanation:

    input[0] => house-0
    input[1] => house-1

    Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
    Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.


 Follow up: Could you solve it in O(nk) runtime?*/

public class Solutions {
    public int minCostII(int[][] costs) {
        int prevIndex = -1; // The previous minimum index
        int prevMin1 = 0;   // Minimum cost so far
        int prevMin2 = 0;   // 2nd minimum cost so far

        for (int[] cost : costs) { // O(n)
            // The painted index s.t. achieve the minimum cost after painting current house
            int index = -1;
            int min1 = Integer.MAX_VALUE;           // The minimum cost after painting current house
            int min2 = Integer.MAX_VALUE;           // The 2nd minimum cost after painting current house
            for (int i = 0; i < cost.length; ++i) { // O(k)
                final int theCost = cost[i] + (i == prevIndex ? prevMin2 : prevMin1);
                if (theCost < min1) {
                    index = i;
                    min2 = min1;
                    min1 = theCost;
                } else if (theCost < min2) { // Min1 <= theCost < min2
                    min2 = theCost;
                }
            }
            prevIndex = index;
            prevMin1 = min1;
            prevMin2 = min2;
        }

        return prevMin1;
    }
}
