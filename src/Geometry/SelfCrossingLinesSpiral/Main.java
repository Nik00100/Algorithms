package Geometry.SelfCrossingLinesSpiral;

/*You are given an array of integers distance.
You start at the point (0, 0) on an X-Y plane, and you move distance[0] meters to the north, then distance[1]
meters to the west, distance[2] meters to the south, distance[3] meters to the east, and so on.
In other words, after each move, your direction changes counter-clockwise.
Return true if your path crosses itself or false if it does not.
Example 1:   _
            |_|_
              |
Input: distance = [2,1,1,2]
Output: true
Explanation: The path crosses itself at the point (0, 1).*/

public class Main {
    /*               i-2
    case 1 : i-1┌─┐
                └─┼─>i
                 i-3

                    i-2
    case 2 : i-1 ┌────┐
                 └─══>┘i-3
                 i  i-4      (i overlapped i-4)

    case 3 :    i-4
               ┌──┐
               │i<┼─┐
            i-3│ i-5│i-1
               └────┘
                i-2

*/
    static boolean isSelfCrossing(int[] x) {
        for(int i=3, l=x.length; i<l; i++) {
            // Case 1: current line crosses the line 3 steps ahead of it
            if(x[i]>=x[i-2] && x[i-1]<=x[i-3]) return true;
                // Case 2: current line crosses the line 4 steps ahead of it
            else if(i>=4 && x[i-1]==x[i-3] && x[i]+x[i-4]>=x[i-2]) return true;
                // Case 3: current line crosses the line 6 steps ahead of it
            else if(i>=5 && x[i-2]>=x[i-4] && x[i]+x[i-4]>=x[i-2] && x[i-1]<=x[i-3] && x[i-1]+x[i-5]>=x[i-3]) return true;
        }
        return false;
    }


}
