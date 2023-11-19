package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/17/2023.
 * #2500 https://leetcode.com/problems/delete-greatest-value-in-each-row/
 */
public class DeleteGreatestValueInEachRow {

    // time O(m * n * log(n)), space O(1)
    public int deleteGreatestValue(int[][] grid) {
        int res = 0, m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            Arrays.sort(grid[i]);
        }
        for (int j = 0; j < n; j++) {
            int max = 0;
            for (int i = 0; i < m; i++) {
                max = Math.max(max, grid[i][j]);
            }
            res += max;
        }
        return res;
    }
}
