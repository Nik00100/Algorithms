package Search_SlidingWindow_QuickSelect_TwoPointer.TwoPointerRainWater;

/*Given n non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it can trap after raining.
Example 1:
    ^   ^ ^ ^   X ^
      X       X X   X
  X   X X   X X X X X X
- - - - - - - - - - - -
0,1,0,2,1,0,1,3,2,1,2,1
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6

The main idea is : if we want to find out how much water on a bar (pre index),
we need to find out the left larger bar's index (indexLeft), and right larger bar's index(indexRight),
so that the water is (min(A[indexLeft],A[indexRight])-A[pre])*(indexRight-indexLeft-1),
use min since only the lower boundary can hold water, and we also need to handle the edge case that there is no indexLeft.*/

public class Main {
    public int trap(int[] height) {
        int rain = 0;
        int l = 0, r = height.length - 1;
        int lMax = height[l], rMax = height[r];
        while (l < r) {
            if (lMax < rMax) {
                rain += lMax - height[l++];
                lMax = Math.max(height[l], lMax);
            } else {
                rain += rMax - height[r--];
                rMax = Math.max(height[r], rMax);
            }
        }
        return rain;
    }
}
