package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 11/17/2023.
 * #2371 https://leetcode.com/problems/minimize-maximum-value-in-a-grid/
 */
public class MinimizeMaximumValueInAGrid {

    // time O(m * n * log(m * n)), space O(m * n)
    public int[][] minScore(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                minQueue.offer(new int[] {grid[i][j], i, j});
            }
        }
        int[] rowMax = new int[m], colMax = new int[n];
        while (!minQueue.isEmpty()) {
            int[] curr = minQueue.poll();
            // new value of a cell [i,j] is the larger between max of row i and col j plus 1
            int val = Math.max(rowMax[curr[1]], colMax[curr[2]]) + 1;
            rowMax[curr[1]] = val;
            colMax[curr[2]] = val;
            grid[curr[1]][curr[2]] = val;
        }
        return grid;
    }
}
