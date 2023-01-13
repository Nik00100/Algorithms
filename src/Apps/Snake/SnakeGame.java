package Apps.Snake;

/*Design a Snake game that is played on a device with screen size = width x height.
Play the game online if you are not familiar with the game.
The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
You are given a list of food's positions in row-column order. When a snake eats the food,
its length and the game's score both increase by 1.
Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.
When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

Example:
Given width = 3, height = 2, and food = [[1,2],[0,1]].
Snake snake = new Snake(width, height, food);
Initially the snake appears at position (0,0) and the food at (1,2).
|S| | |
| | |F|
snake.move("R"); -> Returns 0
| |S| |
| | |F|
snake.move("D"); -> Returns 0
| | | |
| |S|F|
snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )
| |F| |
| |S|S|
snake.move("U"); -> Returns 1
| |F|S|
| | |S|
snake.move("L"); -> Returns 2 (Snake eats the second food)
| |S|S|
| | |S|
snake.move("U"); -> Returns -1 (Game over because snake collides with border)*/


import java.util.*;

public class SnakeGame {
    private int width;
    private int height;
    private int score = 0;
    private int k = 0; // food's index
    private int[][] food;
    private Set<Integer> lookup = new HashSet<>();
    private Deque<Integer> body = new ArrayDeque<>(); // snake's body

    /**
     * Initialize your data structure here.
     *
     * @param width  - screen width
     * @param height - screen height
     * @param food   - A list of food positions E.g food = [[1,1], [1,0]] means the
     *               first food is positioned at [1,1], the second is at [1,0].
     */
    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        lookup.add(getId(0, 0));
        body.offerLast(getId(0, 0));
    }

    /**
     * Moves the snake.
     *
     * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     * @return The game's score after the move. Return -1 if game over. Game over
     *         when snake crosses the screen boundary or bites its body.
     */
    public int move(String direction) {
        // Old head's position
        int i = body.peekFirst() / width;
        int j = body.peekFirst() % width;

        // Update head's position and check if out of bound
        if (direction.equals("U") && --i < 0)
            return -1;
        if (direction.equals("L") && --j < 0)
            return -1;
        if (direction.equals("R") && ++j == width)
            return -1;
        if (direction.equals("D") && ++i == height)
            return -1;

        final int newHead = getId(i, j);

        // Case 1: eat food and increase size by 1
        if (k < food.length && i == food[k][0] && j == food[k][1]) {
            lookup.add(newHead);
            body.offerFirst(newHead);
            ++k;
            return ++score;
        }

        // Case 2: new head != old tail and eat body!
        if (newHead != body.peekLast() && lookup.contains(newHead))
            return -1;

        // Case 3: normal case
        // Remove old tail first (important), then add new head
        // Because new head may be in old tail's position
        lookup.remove(body.peekLast());
        lookup.add(newHead);
        body.pollLast();
        body.offerFirst(newHead);

        return score;
    }

    private int getId(int i, int j) {
        return i * width + j;
    }
}