package BFS_DFS_UnionFind.DfsContainVirus;

/*A virus is spreading rapidly, and your task is to quarantine the infected area by installing walls.

The world is modeled as an m x n binary grid isInfected, where isInfected[i][j] == 0 represents uninfected cells, and
isInfected[i][j] == 1 represents cells contaminated with the virus. A wall (and only one wall) can be installed between
any two 4-directionally adjacent cells, on the shared boundary.

Every night, the virus spreads to all neighboring cells in all four directions unless blocked by a wall. Resources are limited.

Each day, you can install walls around only one region (i.e., the affected area (continuous block of infected cells) that
threatens the most uninfected cells the following night). There will never be a tie.

Return the number of walls used to quarantine all the infected regions. If the world will become fully infected, return the number
of walls used.

Example 1:
Input: isInfected = [[0,1,0,0,0,0,0,1],[0,1,0,0,0,0,0,1],[0,0,0,0,0,0,0,1],[0,0,0,0,0,0,0,0]]
Output: 10
Explanation: There are 2 contaminated regions.
On the first day, add 5 walls to quarantine the viral region on the left. The board after the virus spreads is:
On the second day, add 5 walls to quarantine the viral region on the right. The virus is fully contained.
Example 2:
Input: isInfected = [[1,1,1],[1,0,1],[1,1,1]]
Output: 4
Explanation: Even though there is only one cell saved, there are 4 walls built.
Notice that walls are only built on the shared boundary of two different cells.
Example 3:
Input: isInfected = [[1,1,1,0,0,0,0,0,0],[1,0,1,0,1,1,1,1,1],[1,1,1,0,0,0,0,0,0]]
Output: 13
Explanation: The region on the left only builds two new walls.*/

import java.util.*;

class Solution {
    int m, n;
    public int containVirus(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int ans = 0;
        while(true) {
            // list of regions can spread virus
            List<Region> regions = new ArrayList<>();
            boolean[][] visited = new boolean[m][n];
            for(int i=0; i < m; i++) {
                for(int j=0; j < n; j++) {
                    if(grid[i][j] == 1 && !visited[i][j]) {
                        Region region = new Region();
                        dfs(grid, i, j, region, visited);
                        if(region.uninfected.size() > 0)    regions.add(region);
                    }
                }
            }

            if(regions.size() == 0)  break;
            Collections.sort(regions, (a, b) -> {
                return a.uninfected.size()-b.uninfected.size();
            });

            Region mostToBeInfected = regions.remove(regions.size()-1);
            ans += mostToBeInfected.wallNeeded;
            for(int x: mostToBeInfected.infected) {
                int i = x/n, j = x%n;
                // mark all infected cell of the mostToBeInfected as qurantined
                grid[i][j] = 2;
            }

            for(Region region: regions) {
                for(int x: region.uninfected) {
                    int i = x/n, j = x%n;
                    // mark the neighbor of the other reigion as infected
                    grid[i][j] = 1;
                }
            }
        }

        return ans;
    }

    public void dfs(int[][] grid, int i, int j, Region region, boolean[][] visited) {
        if(i < 0 || i == m || j < 0 || j == n)  return;

        if(grid[i][j] == 1 && !visited[i][j]) {
            visited[i][j] = true;
            region.infected.add(i*n+j);
            dfs(grid, i-1, j, region, visited);
            dfs(grid, i+1, j, region, visited);
            dfs(grid, i, j-1, region, visited);
            dfs(grid, i, j+1, region, visited);
        } else if(grid[i][j] == 0) {
            region.wallNeeded += 1;
            region.uninfected.add(i*n+j);
        }
    }

    private class Region {
        int wallNeeded;
        HashSet<Integer> infected, uninfected;
        Region() {
            wallNeeded = 0;
            infected = new HashSet<>();
            uninfected = new HashSet<>();
        }
    }
}