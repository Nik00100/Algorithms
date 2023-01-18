package Search_SlidingWindow_QuickSelect_TwoPointer.BinarySearchLongestIncreasingSubsequence;
/*You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the height of an envelope.
One envelope can fit into another if and only if both the width and height of one envelope are greater than the
other envelope's width and height.
Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).
Note: You cannot rotate an envelope.

Example 1:
Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
Output: 3
Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).

    - Sort the array. Ascend on width and descend on height if width are same.
    - Find the longest increasing subsequence based on height.

    - Since the width is increasing, we only need to consider height.
    - [3, 4] cannot contains [3, 3], so we need to put [3, 4] before [3, 3] when sorting otherwise
        it will be counted as an increasing number if the order is [3, 3], [3, 4]

This problem is asking for Long Increasing Subsequence LIS in two dimensions, width and height.
Sorting the width reduces the problem by one dimension.If width is strictly increasing,
the problem is equivalent to finding LIS in only the height dimension.However, when there is a tie in width,
a strictly increasing sequence in height may not be a correct solution.For example, [[3,3] cannot fit in [3,4]].
Sorting height in descending order when there is a tie prevents such a sequence to be included in the solution.

The same idea can be applied to problems of higher dimensions. For example, box fitting is three dimensions, width, height,
and length. Sorting width ascending and height descending reduces the problem by one dimension.
Finding the LIS by height further reduces the problem by another dimension. When find LIS based on only length,
it becomes a standard LIS problem.*/

import java.util.*;

public class RussianDoll {
    public static int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes,(a,b)-> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        List<Integer> subs = new ArrayList<>();
        for (int[] envelop : envelopes) {
            int index = Collections.binarySearch(subs,envelop[1]);
            if (index < 0) {
                index = ~index;
            }
            if (index == subs.size()) {
                subs.add(envelop[1]);
            } else {
                subs.set(index,envelop[1]);
            }
        }
        return subs.size();
    }


    public static void main(String[] args) {
        int[][] envelopes = {{5,4},{6,4},{6,7},{2,3}};
        System.out.println(maxEnvelopes(envelopes));
    }
}
