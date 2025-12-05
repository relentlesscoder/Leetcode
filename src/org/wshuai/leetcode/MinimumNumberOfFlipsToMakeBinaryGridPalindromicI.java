package org.wshuai.leetcode;

/**
 * Created by Wei on 09/28/2025.
 * #3239 https://leetcode.com/problems/minimum-number-of-flips-to-make-binary-grid-palindromic-i/
 */
public class MinimumNumberOfFlipsToMakeBinaryGridPalindromicI {

    // time O(m * n), space O(1)
    public int minFlips(int[][] grid) {
        return Math.min(flipAllRows(grid), flipAllColumns(grid));
    }

    private int flipAllRows(int[][] grid) {
        int res = 0, m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            int l = (n - 1) / 2, r = n / 2;
            while (l >= 0 && r < n) {
                if (grid[i][l--] != grid[i][r++]) {
                    res++;
                }
            }
        }
        return res;
    }

    private int flipAllColumns(int[][] grid) {
        int res = 0, m = grid.length, n = grid[0].length;
        for (int i = 0; i < n; i++) {
            int l = (m - 1) / 2, r = m / 2;
            while (l >= 0 && r < m) {
                if (grid[l--][i] != grid[r++][i]) {
                    res++;
                }
            }
        }
        return res;
    }
}
