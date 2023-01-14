package Geometry.PerfectRectangle;

/*
Given an array rectangles where rectangles[i] = [xi, yi, ai, bi] represents an axis-aligned rectangle.
The bottom-left point of the rectangle is (xi, yi) and the top-right point of it is (ai, bi).

Return true if all the rectangles together form an exact cover of a rectangular region.
Example 1:
Input: rectangles = [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
Output: true
Explanation: All 5 rectangles together form an exact cover of a rectangular region.*/

import java.util.*;

public class Main {

    /*The right answer must satisfy two conditions:

    - the large rectangle area should be equal to the sum of small rectangles
    - count of all the points should be 4*/

    static class Solution {
        class Point {
            int x;
            int y;

            Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public boolean equals(Object o) {
                if (o instanceof Point) {
                    Point p = (Point)o;
                    return this.x == p.x && this.y == p.y;
                }
                return false;
            }

            public int hashCode() {
                int code = 1;
                code = code * 31 + x;
                code = code * 31 + y;
                return code;
            }
        }

        public boolean isRectangleCover(int[][] rectangles) {
            if (rectangles == null || rectangles.length == 0) {
                return false;
            }

            // bottome left
            int x1 = Integer.MAX_VALUE;
            int y1 = Integer.MAX_VALUE;
            // top right
            int x2 = Integer.MIN_VALUE;
            int y2 = Integer.MIN_VALUE;
            int area = 0;
            Set<Point> set = new HashSet<>();
            for (int[] rec : rectangles) {
                area += (rec[2] - rec[0]) * (rec[3] - rec[1]);

                x1 = Math.min(x1, rec[0]);
                y1 = Math.min(y1, rec[1]);
                x2 = Math.max(x2, rec[2]);
                y2 = Math.max(y2, rec[3]);

                Point p1 = new Point(rec[0], rec[1]);
                if (!set.add(p1)) {
                    set.remove(p1);
                }
                Point p2 = new Point(rec[0], rec[3]);
                if (!set.add(p2)) {
                    set.remove(p2);
                }
                Point p3 = new Point(rec[2], rec[3]);
                if (!set.add(p3)) {
                    set.remove(p3);
                }
                Point p4 = new Point(rec[2], rec[1]);
                if (!set.add(p4)) {
                    set.remove(p4);
                }
            }
            if (set.size() != 4 || !set.contains(new Point(x1, y1)) || !set.contains(new Point(x1, y2)) ||
                    !set.contains(new Point(x2, y2)) || !set.contains(new Point(x2, y1))) {
                return false;
            }
            return area == (x2 - x1) * (y2 - y1);
        }
    }

}
