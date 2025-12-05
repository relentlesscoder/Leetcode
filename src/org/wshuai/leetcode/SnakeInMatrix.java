package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 10/03/2025.
 * #3248 https://leetcode.com/problems/snake-in-matrix/
 */
public class SnakeInMatrix {

    // time O(n), space O(1)
    public int finalPositionOfSnake(int n, List<String> commands) {
        int r = 0, c = 0;
        for (String command : commands) {
            if ("UP".equals(command)) {
                r--;
            } else if ("DOWN".equals(command)) {
                r++;
            } else if ("LEFT".equals(command)) {
                c--;
            } else {
                c++;
            }
        }
        return r * n + c;
    }
}
