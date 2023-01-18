package Search_SlidingWindow_QuickSelect_TwoPointer.BinarySearchHeaters;

/*475. Heaters https://leetcode.com/problems/heaters/description/

Winter is coming! During the contest, your first job is to design a standard heater with a fixed warm radius
to warm all the houses. Every house can be warmed, as long as the house is within the heater's warm radius range.
Given the positions of houses and heaters on a horizontal line, return the minimum radius standard of heaters so
that those heaters could cover all houses.
Notice that all the heaters follow your radius standard, and the warm radius will the same.
Example 1:
Input: houses = [1,2,3], heaters = [2]
Output: 1
Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
Example 2:
Input: houses = [1,2,3,4], heaters = [1,4]
Output: 1
Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.
Example 3:
Input: houses = [1,5], heaters = [2]
Output: 3

* The idea is to leverage decent Arrays.binarySearch() function provided by Java.

For each house, find its position between those heaters (thus we need the heaters array to be sorted).
Calculate the distances between this house and left heater and right heater, get a MIN value of those two values. Corner cases are there is no left or right heater.
Get MAX value among distances in step 2. It's the answer.
Time complexity: max(O(nlogn), O(mlogn)) - m is the length of houses, n is the length of heaters.*/

import java.util.Arrays;

public class Main {

    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int result = 0;

        for (int house : houses) {
            int index = Arrays.binarySearch(heaters, house);
            if (index < 0) {
                index = ~index;
                int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
                int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;

                result = Math.max(result, Math.min(dist1, dist2));
            }
        }

        return result;
    }
}
