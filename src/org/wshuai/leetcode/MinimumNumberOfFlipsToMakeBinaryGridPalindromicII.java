package org.wshuai.leetcode;

/**
 * Created by Wei on 09/28/2025.
 * #3240 https://leetcode.com/problems/minimum-number-of-flips-to-make-binary-grid-palindromic-ii/
 */
public class MinimumNumberOfFlipsToMakeBinaryGridPalindromicII {

    // time O(m * n), space O(1)
    public int minFlips(int[][] grid) {
        // https://leetcode.cn/problems/minimum-number-of-flips-to-make-binary-grid-palindromic-ii/solutions/2868238/fen-lei-tao-lun-pythonjavacgo-by-endless-jl6a/
        int res = 0, m = grid.length, n = grid[0].length;
        for (int i = 0; i < m / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                int ones = grid[i][j] + grid[i][n - 1 - j]
                        + grid[m - 1 - i][j] + grid[m - 1 - i][n - 1 - j];
                res += Math.min(ones, 4 - ones);
            }
        }
        if (m % 2 != 0 && n % 2 != 0) {
            res += grid[m / 2][n / 2];
        }
        int diff = 0, count = 0;
        if (m % 2 == 1) {
            for (int j = 0; j < n / 2; j++) {
                if (grid[m / 2][j] != grid[m / 2][n - 1 - j]) {
                    diff++;
                } else {
                    count += grid[m / 2][j] * 2;
                }
            }
        }
        if (n % 2 == 1) {
            for (int i = 0; i < m / 2; i++) {
                if (grid[i][n / 2] != grid[m - 1 - i][n / 2]) {
                    diff++;
                } else {
                    count += grid[i][n / 2] * 2;
                }
            }
        }
        return res + (diff > 0 ? diff : count % 4);
    }
}
