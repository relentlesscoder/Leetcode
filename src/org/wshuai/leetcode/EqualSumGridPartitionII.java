package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 11/17/2025.
 * #3548 https://leetcode.com/problems/equal-sum-grid-partition-ii/
 */
public class EqualSumGridPartitionII {

    // time O(m * n), space O(m * n)
    public boolean canPartitionGrid(int[][] grid) {
        long sum = 0;
        for (int[] row : grid) {
            for (int v : row) {
                sum += v;
            }
        }
        return check(grid, sum) || check(rotate(grid), sum);
    }

    private boolean check(int[][] grid, long total) {
        if (delete(grid, total)) {
            return true;
        }
        reverse(grid);
        return delete(grid, total);
    }

    private int[][] rotate(int[][] a) {
        int m = a.length, n = a[0].length;
        int[][] b = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                b[j][m - 1 - i] = a[i][j];
            }
        }
        return b;
    }

    private void reverse(int[][] a) {
        for (int i = 0, j = a.length - 1; i < j; i++, j--) {
            int[] tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }

    private boolean delete(int[][] grid, long total) {
        int m = grid.length, n = grid[0].length;
        Set<Long> set = new HashSet<>();
        set.add(0L);
        long sum = 0;
        for (int i = 0; i < m - 1; i++) {
            int[] row = grid[i];
            for (int j = 0; j < n; j++) {
                int v = row[j];
                sum += v;
                // Only leftmost and rightmost elements can be discounted for first row
                if (i > 0 || j == 0 || j == n - 1) {
                    set.add((long) v);
                }
            }
            // Only element at first row or at the partition row can be discounted if there is only
            // one column
            if (n == 1) {
                if (sum * 2 == total || sum * 2 - total == grid[0][0] || sum * 2 - total == row[0]) {
                    return true;
                }
                continue;
            }
            if (set.contains(sum * 2 - total)) {
                return true;
            }
            // Add all numbers from row 0 if partition by other rows
            if (i == 0) {
                for (int v : row) {
                    set.add((long) v);
                }
            }
        }
        return false;
    }
}
