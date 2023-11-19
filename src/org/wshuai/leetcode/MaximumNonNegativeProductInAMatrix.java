package org.wshuai.leetcode;

/**
 * Created by Wei on 09/20/2020.
 * #1594 https://leetcode.com/problems/maximum-non-negative-product-in-a-matrix/
 */
public class MaximumNonNegativeProductInAMatrix {

    // time O(m*n), space O(m*n)
    public int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[][][] dp = new long[m][n][2];
        long product = 1L;
        for(int i = 0; i < m; i++){
            product *= grid[i][0];
            dp[i][0][0] = dp[i][0][1] = product;
        }
        product = 1L;
        for(int i = 0; i < n; i++){
            product *= grid[0][i];
            dp[0][i][0] = dp[0][i][1] = product;
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(grid[i][j] > 0){
                    dp[i][j][0] = grid[i][j] * Math.max(dp[i - 1][j][0], dp[i][j - 1][0]);
                    dp[i][j][1] = grid[i][j] * Math.min(dp[i - 1][j][1], dp[i][j - 1][1]);
                }else{
                    dp[i][j][1] = grid[i][j] * Math.max(dp[i - 1][j][0], dp[i][j - 1][0]);
                    dp[i][j][0] = grid[i][j] * Math.min(dp[i - 1][j][1], dp[i][j - 1][1]);
                }
            }
        }
        int res = (int)(dp[m - 1][n - 1][0] % 1_000_000_007);
        return res < 0 ? -1 : res;
    }
}
