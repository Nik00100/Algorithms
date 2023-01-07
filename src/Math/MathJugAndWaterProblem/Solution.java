package Math.MathJugAndWaterProblem;

/*You are given two jugs with capacities jug1Capacity and jug2Capacity liters.
There is an infinite amount of water supply available. Determine whether it is possible
to measure exactly targetCapacity liters using these two jugs.

If targetCapacity liters of water are measurable, you must have targetCapacity liters of water
contained within one or both buckets by the end.

Operations allowed:

Fill any of the jugs with water.
Empty any of the jugs.
Pour water from one jug into another till the other jug is completely full, or the first jug itself is empty.


Example 1:
Input: jug1Capacity = 3, jug2Capacity = 5, targetCapacity = 4
Output: true
Explanation: The famous Die Hard example*/

class Solution {
    private int GCD(int x, int y)
    {
        while(y!=0)
        {
            int tmp = y;
            y = x % y;
            x = tmp;
        }
        // System.out.println(x);
        return x;
    }

    public boolean canMeasureWater(int x, int y, int z) {
        if(x+y<z)
            return false;
        if(x==z || y==z)
            return true;
        return (z % GCD(x,y)) == 0;
    }
}