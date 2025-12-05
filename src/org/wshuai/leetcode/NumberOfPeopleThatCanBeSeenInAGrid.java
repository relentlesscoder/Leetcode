package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 11/25/2025.
 * #2282 https://leetcode.com/problems/number-of-people-that-can-be-seen-in-a-grid/
 */
public class NumberOfPeopleThatCanBeSeenInAGrid {

    // time O(m * n), space O(m + n)
    public int[][] seePeople(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int[][] res = new int[m][n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            stack.clear();
            for (int j = 0; j < n; j++) {
                int prev = -1; // Deduplicate for input like [[4,2,1,1,3]]
                while (!stack.isEmpty() && heights[i][stack.peek()] < heights[i][j]) {
                    int idx = stack.pop();
                    // People at index [0,2] (1) can't see people [0,4]
                    if (heights[i][idx] != prev) {
                        res[i][idx]++;
                    }
                    prev = heights[i][idx];
                }
                if (!stack.isEmpty()) {
                    res[i][stack.peek()]++;
                }
                stack.push(j);
            }
        }
        for (int j = 0; j < n; j++) {
            stack.clear();
            for (int i = 0; i < m; i++) {
                int prev = -1;
                while (!stack.isEmpty() && heights[stack.peek()][j] < heights[i][j]) {
                    int idx = stack.pop();
                    if (heights[idx][j] != prev) {
                        res[idx][j]++;
                    }
                    prev = heights[idx][j];
                }
                if (!stack.isEmpty()) {
                    res[stack.peek()][j]++;
                }
                stack.push(i);
            }
        }
        return res;
    }
}
