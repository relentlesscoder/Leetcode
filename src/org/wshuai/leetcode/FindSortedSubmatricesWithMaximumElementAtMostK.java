package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 11/27/2025.
 * #3359 https://leetcode.com/problems/find-sorted-submatrices-with-maximum-element-at-most-k/
 */
public class FindSortedSubmatricesWithMaximumElementAtMostK {

    // time O(m * n), space O(n)
    public long countSubmatrices(int[][] grid, int k) {
        // Same idea as #1504
        // "Rotate" the grid counterclockwise by 90 then
        // we can convert it to the same problem as #1504.
        // We extend the height for column (row in original
        // grid) by 1 if
        //   grid[i][j - 1] >= grid[i][j]
        // then the problem becomes #1504
        long res = 0;
        int n = grid.length, m = grid[0].length;
        int[] heights = new int[n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (grid[j][i] > k) {
                    heights[j] = 0;
                } else {
                    heights[j] = i > 0 && grid[j][i - 1] >= grid[j][i] ? heights[j] + 1 : 1;
                }
            }
            res += countRow(heights);
        }
        return res;
    }

    private long countRow(int[] heights) {
        long res = 0;
        int n = heights.length;
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{-1, 0, -1});
        for (int i = 0; i < n; i++) {
            while (stack.size() > 1 && stack.peek()[2] >= heights[i]) {
                stack.pop();
            }
            int count = stack.peek()[1] + (i - stack.peek()[0]) * heights[i];
            stack.push(new int[]{i, count, heights[i]});
            res += count;
        }
        return res;
    }
}
