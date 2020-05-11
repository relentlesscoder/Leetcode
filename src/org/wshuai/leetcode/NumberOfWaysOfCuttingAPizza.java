package org.wshuai.leetcode;

/**
 * Created by Wei on 05/11/2020.
 * #1444 https://leetcode.com/problems/number-of-ways-of-cutting-a-pizza/
 */
public class NumberOfWaysOfCuttingAPizza {

    private static final int MOD = 1_000_000_007;

    public int ways(String[] pizza, int k) {
        int m = pizza.length, n = pizza[0].length();
        Integer[][][] dp = new Integer[m][n][k];
        int[][] postfix = new int[m + 1][n + 1];
        for(int i = m - 1; i >= 0; i--){
            for(int j = n - 1; j >= 0; j--){
                postfix[i][j] = postfix[i + 1][j] + postfix[i][j + 1]
                        - postfix[i + 1][j + 1] + (pizza[i].charAt(j) == 'A' ? 1 : 0);
            }
        }
        return dfs(0, 0, m, n, k - 1, postfix, dp);
    }

    private int dfs(int x, int y, int m, int n, int k, int[][] postfix, Integer[][][] dp){
        if(postfix[x][y] == 0){
            return 0;
        }
        if(k == 0){
            return 1;
        }
        if(dp[x][y][k] != null){
            return dp[x][y][k];
        }
        int res = 0;
        for(int i = x + 1; i < m; i++){
            if(postfix[i][y] == 0){
                break;
            }
            if(postfix[x][y] - postfix[i][y] > 0){
                res = (res + dfs(i, y, m, n, k - 1, postfix, dp)) % MOD;
            }
        }
        for(int j = y + 1; j < n; j++){
            if(postfix[x][j] == 0){
                break;
            }
            if(postfix[x][y] - postfix[x][j] > 0){
                res = (res + dfs(x, j, m, n, k - 1, postfix, dp)) % MOD;
            }
        }
        dp[x][y][k] = res;
        return res;
    }
}
