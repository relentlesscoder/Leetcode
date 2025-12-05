package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/10/2025.
 * #2751 https://leetcode.com/problems/robot-collisions/
 */
public class RobotCollisions {

    // time O(n * log(n)), space O(n)
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Integer[] indexes = new Integer[n];
        Arrays.setAll(indexes, index -> index);
        // Sort array by indexes since input may not be sorted
        Arrays.sort(indexes, (i, j) -> positions[i] - positions[j]);
        Deque<int[]> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int index = indexes[i]; // Find the actual index in the input positions
            char dir = directions.charAt(index);
            int h1 = healths[index];
            if (dir == 'L') {// Collision only happens for two robots move in `RL`
                // Collision handling: find if there are any robots moving to right
                // on top of the stack
                while (!stack.isEmpty() && stack.peek()[1] == 'R' && h1 > 0) {
                    int h2 = stack.peek()[0];
                    if (h1 >= h2) { // Current robot (moving to left) has more or same health
                        h1 = h1 == h2 ? 0 : h1 - 1;
                        stack.pop();
                    } else { // Current robot (moving to left) has less health
                        h1 = 0;
                        stack.peek()[0]--;
                    }
                }
            }
            // Push to top of the stack if:
            //   1. Robot moves to `L`, its health stays positive after all collisions (if any) happened
            //   2. Robot moves to `R`, always push it
            if (h1 > 0) {
                stack.push(new int[]{h1, dir, index});
            }
        }
        Integer[] arr = new Integer[n];
        while (!stack.isEmpty()) {
            arr[stack.peek()[2]] = stack.pop()[0]; // set survival's health
        }
        List<Integer> res = new ArrayList<>();
        for (Integer h : arr) {
            if (h != null) {
                res.add(h);
            }
        }
        return res;
    }
}
