package BFS_DFS_UnionFind.BfsSmallestRectEncloseBlackPix;

/*An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel.
The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally
and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest
(axis-aligned) rectangle that encloses all black pixels.

Example:
char[][] image = {
  {'0','0','1','0'},
  {'0','1','1','0'},
  {'0','1','0','0'},
};
int x = 0, y = 2;

Output: 6*/

import java.util.*;

public class Main {
    static int minArea(char[][] image, int x, int y) {
        int n = image.length;
        int m = image[0].length;
        Queue<int[]> queue = new LinkedList<>(Arrays.asList(new int[]{x,y}));
        int[] topLeft = {x,y};
        int[] bottomRight = {x,y};
        int[] dirs = {1,0,-1,0,1}; // search directions
        image[x][y] = 'v'; // mark as visited
        while (!queue.isEmpty()) {
            int i = queue.peek()[0];
            int j = queue.poll()[1];
            for(int k=0; k< dirs.length-1; k++) {
                int r = i + dirs[k]; // new row for search
                int c = j + dirs[k+1]; // new column for search
                // check for boundaries and valid char
                if (r<0 || c<0 || r>=n || c>=m || image[r][c] != '1')
                    continue;
                topLeft[0] = Math.min(r,topLeft[0]);
                topLeft[1] = Math.min(c,topLeft[1]);
                bottomRight[0] = Math.max(r,bottomRight[0]);
                bottomRight[1] = Math.max(c,bottomRight[1]);
                queue.offer(new int[] {r,c});
                image[r][c] = 'v';
            }
        }

        int width = Math.abs(topLeft[0] - bottomRight[0]) + 1;
        int height = Math.abs(topLeft[1] - bottomRight[1]) + 1;
        return width*height;
    }

    public static void main(String[] args) {
        char[][] image = {
                {'0','0','1','0'},
                {'0','1','1','0'},
                {'0','1','0','0'},
        };
        int x = 0, y = 2;
        System.out.println(minArea(image,x,y));
    }
}
