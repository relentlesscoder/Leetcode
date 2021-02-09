package org.wshuai.leetcode;

/**
 * Created by Wei on 10/07/2019.
 * #1041 https://leetcode.com/problems/robot-bounded-in-circle/
 */
public class RobotBoundedInCircle {

    private static final int[][] DIRECTIONS = new int[][]{
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    // time O(n)
    public boolean isRobotBounded(String instructions) {
        int x = 0, y = 0, d = 0;
        for (char c : instructions.toCharArray()) {
            if (c == 'L') {
                d = (d + 1) % 4;
            } else if (c == 'R') {
                d = (d + 3) % 4; // (d - 1 + 4) % 4
            } else {
                x += DIRECTIONS[d][0];
                y += DIRECTIONS[d][1];
            }
        }
        return (x == 0 && y == 0) || d != 0; // if direction not equals to north, then it will form a circle
    }
}
