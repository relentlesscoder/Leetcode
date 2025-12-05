package org.wshuai.leetcode;

/**
 * Created by Wei on 07/01/2025.
 * #3071 https://leetcode.com/problems/minimum-operations-to-write-the-letter-y-on-a-grid/
 */
public class MinimumOperationsToWriteTheLetterYOnAGrid {

    // time O(n^2), space O(1)
    public int minimumOperationsToWriteY(int[][] grid) {
        int n = grid.length;
        int[] countY = new int[3], countNonY = new int[3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j && i < n / 2) {
                    countY[grid[i][j]]++;
                } else if (j == n - 1 - i && i < n / 2) {
                    countY[grid[i][j]]++;
                } else if (j == n / 2 && i >= n / 2) {
                    countY[grid[i][j]]++;
                } else {
                    countNonY[grid[i][j]]++;
                }
            }
        }
        int writeYWithZero = countY[1] + countY[2] + countNonY[0] + Math.min(countNonY[1], countNonY[2]);
        int writeYWithOne = countY[0] + countY[2] + countNonY[1] + Math.min(countNonY[0], countNonY[2]);
        int writeYWithTwo = countY[0] + countY[1] + countNonY[2] + Math.min(countNonY[0], countNonY[1]);
        return Math.min(writeYWithZero, Math.min(writeYWithOne, writeYWithTwo));
    }
}
