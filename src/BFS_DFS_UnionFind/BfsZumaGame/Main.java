package BFS_DFS_UnionFind.BfsZumaGame;

/*488. Zuma Game https://leetcode.com/problems/zuma-game/

You are playing a variation of the game Zuma. In this variation of Zuma, there is a single row of colored balls on a board,
where each ball can be colored red 'R', yellow 'Y', blue 'B', green 'G', or white 'W'. You also have several colored balls
in your hand. Your goal is to clear all of the balls from the board. On each turn:
Pick any ball from your hand and insert it in between two balls in the row or on either end of the row.
If there is a group of three or more consecutive balls of the same color, remove the group of balls from the board.
If this removal causes more groups of three or more of the same color to form, then continue removing each group until
there are none left. If there are no more balls on the board, then you win the game. Repeat this process until you either
win or do not have any more balls in your hand. Given a string board, representing the row of balls on the board,
and a string hand, representing the balls in your hand, return the minimum number of balls you have to insert to clear
all the balls from the board. If you cannot clear all the balls from the board using the balls in your hand, return -1.

Example 1:
Input: board = "WRRBBW", hand = "RB"
Output: -1
Explanation: It is impossible to clear all the balls. The best you can do is:
- Insert 'R' so the board becomes WRRRBBW. WRRRBBW -> WBBW.
- Insert 'B' so the board becomes WBBBW. WBBBW -> WW.
There are still balls remaining on the board, and you are out of balls to insert.
Example 2:
Input: board = "WWRRBBWW", hand = "WRBRW"
Output: 2
Explanation: To make the board empty:
- Insert 'R' so the board becomes WWRRRBBWW. WWRRRBBWW -> WWBBWW.
- Insert 'B' so the board becomes WWBBBWW. WWBBBWW -> WWWW -> empty.
2 balls from your hand were needed to clear the board.*/

import java.util.*;

public class Main {
    static class Solution {
        // end flag
        private static final String END = "$";

        public int findMinStep(String board, String hand) {
            // count frequency of hand letter occurrence
            final Map<Character, Integer> map = new HashMap<>();
            for (char c : hand.toCharArray()) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
            // basic data structure for bfs
            final Set<String> visited = new HashSet<>();
            final Queue<String[]> queue = new LinkedList<>();
            queue.offer(new String[]{board + END, hand});
            int level = 0;
            // breadth first search start
            while (!queue.isEmpty()) {
                // iterate by level
                final int currentSize = queue.size();
                for (int size = 0; size < currentSize; size++) {
                    final String[] currentPair = queue.poll();
                    final String nowBoard = removeSame(currentPair[0]);
                    // end condition
                    if (nowBoard.equals(END)) {
                        return level;
                    }
                    final String nowHand = currentPair[1];
                    // for each position, for each hand, make new board and new hand,
                    // if new board not visited yet, add it into visited and queue.
                    for (int i = 0; i < nowBoard.length(); i++) {
                        for (char h : nowHand.toCharArray()) {
                            // if we has only one letter, then it should equal to its neighbor
                            if (1 == map.getOrDefault(h, 0) && i > 1 && i < nowBoard.length() - 1
                                    && nowBoard.charAt(i) != h && h != nowBoard.charAt(i + 1)) {
                                continue;
                            }
                            final String newString =
                                    nowBoard.substring(0, i) + h + nowBoard.substring(i);
                            if (!visited.contains(newString)) {
                                final String newHand = removeCharFromHand(nowHand, h);
                                visited.add(newString);
                                queue.offer(new String[]{newString, newHand});
                            }
                        }
                    }
                }
                ++level;
            }
            return -1;
        }

        private String removeCharFromHand(String hand, char c) {
            final StringBuilder handBuild = new StringBuilder(hand);
            for (int i = 0; i < handBuild.length(); i++) {
                if (handBuild.charAt(i) == c) {
                    handBuild.deleteCharAt(i);
                    break;
                }
            }
            return handBuild.toString();
        }

        private String removeSame(String b) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0, j = 0; j <= b.length(); j++) {
                if (j < b.length() && b.charAt(i) == b.charAt(j)) continue;
                if (j - i >= 3)
                    return removeSame(b.substring(0, i) + b.substring(j));
                i = j;
            }
            return b;
        }

    }
}
