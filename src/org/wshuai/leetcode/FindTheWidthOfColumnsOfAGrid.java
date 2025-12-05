package org.wshuai.leetcode;

/**
 * Created by Wei on 01/09/2024.
 * #2639 https://leetcode.com/problems/find-the-width-of-columns-of-a-grid/
 */
public class FindTheWidthOfColumnsOfAGrid {

    // time O(m * n), space O(n)
    public int[] findColumnWidth(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] res = new int[n];
        for (int j = 0; j < n; j++) {
            int maxWidth = 0;
            for (int i = 0; i < m; i++) {
                int width = grid[i][j] <= 0 ? 1 : 0, val = grid[i][j] < 0 ? -grid[i][j] : grid[i][j];
                while (val > 0) {
                    val /= 10;
                    width++;
                }
                maxWidth = Math.max(maxWidth, width);
            }
            res[j] = maxWidth;
        }
        return res;
    }
}
