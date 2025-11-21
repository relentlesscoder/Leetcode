package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/17/2019.
 * #0735 https://leetcode.com/problems/asteroid-collision/
 */
public class AsteroidCollision {

    // time O(n), space O(n)
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int ast : asteroids) {
			// Keep colliding with the asteroids in the stack
            while (ast < 0 && !stack.isEmpty() && stack.peek() > 0) {
                int collision = ast + stack.peek();
                if (collision == 0) { // Both asteroids are in same size
                    ast = 0;
                    stack.pop();
                } else if (collision < 0) {
					// Current asteroid is bigger, so it crashes the top one of the stack
					// and keep moving to the left
                    stack.pop();
                } else {
					// Current asteroid is smaller so it will explode
                    ast = 0;
                }
            }
            if (ast != 0) { // Push to stack if current asteroid did not explode
                stack.push(ast);
            }
        }
        int m = stack.size();
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            res[i] = stack.pollLast();
        }
        return res;
    }
}
