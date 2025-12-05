package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Wei on 09/20/2025.
 * #3446 https://leetcode.com/problems/sort-matrix-by-diagonals/
 */
public class SortMatrixByDiagonals {

    // time O(n^2 * log(n)), space O(n)
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        for (int j = 1; j < n; j++) {
            List<Integer> vals = new ArrayList<>();
            for (int i = 0; j + i < n; i++) {
                vals.add(grid[i][j + i]);
            }
            Collections.sort(vals);
            for (int i = 0; j + i < n; i++) {
                grid[i][j + i] = vals.get(i);
            }
        }
        for (int i = 0; i < n; i++) {
            List<Integer> vals = new ArrayList<>();
            for (int j = 0; j + i < n; j++) {
                vals.add(grid[i + j][j]);
            }
            Collections.sort(vals, Collections.reverseOrder());
            for (int j = 0; j + i < n; j++) {
                grid[i + j][j] = vals.get(j);
            }
        }
        return grid;
    }
}
