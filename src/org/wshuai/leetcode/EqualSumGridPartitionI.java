package org.wshuai.leetcode;

/**
 * Created by Wei on 11/17/2025.
 * #3546 https://leetcode.com/problems/equal-sum-grid-partition-i/
 */
public class EqualSumGridPartitionI {

    // time O(m * n), space O(m + n)
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[] rowSum = new long[m], colSum = new long[n];
        long sum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum += grid[i][j];
                rowSum[i] += grid[i][j];
                colSum[j] += grid[i][j];
            }
        }
        if (sum % 2 == 1) {
            return false;
        }
        sum /= 2;
        long cur = 0;
        for (int i = 0; i < m; i++) {
            cur += rowSum[i];
            if (cur == sum) {
                return true;
            }
        }
        cur = 0;
        for (int j = 0; j < n; j++) {
            cur += colSum[j];
            if (cur == sum) {
                return true;
            }
        }
        return false;
    }
}
