package org.wshuai.leetcode;

/**
 * Created by Wei on 12/29/2023.
 * #2965 https://leetcode.com/problems/find-missing-and-repeated-values/
 */
public class FindMissingAndRepeatedValues {

    // time O(n^2), space O(1)
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int[] res = new int[2];
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // try to set grid[i][j] to i * n + j + 1 by swapping
                for (int r = (grid[i][j] - 1) / n, c = (grid[i][j] - 1) % n; grid[i][j] != n * i + j + 1 && grid[r][c] != n * r + c + 1; r = (grid[i][j] - 1) / n, c = (grid[i][j] - 1) % n) {
                    int temp = grid[i][j];
                    grid[i][j] = grid[r][c];
                    grid[r][c] = temp;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int val = i * n + j + 1;
                if (grid[i][j] != val) {
                    res[0] = grid[i][j];
                    res[1] = val;
                }
            }
        }
        return res;
    }
}
