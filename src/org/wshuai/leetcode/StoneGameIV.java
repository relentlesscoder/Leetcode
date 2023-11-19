package org.wshuai.leetcode;

/**
 * Created by Wei on 07/23/2020.
 * #1510 https://leetcode.com/problems/stone-game-iv/
 */
public class StoneGameIV {

    public boolean winnerSquareGame(int n) {
        int[] dp = new int[n + 1];
        return dfs(n, dp) > 0;
    }

    private int dfs(int n, int[] dp){
        if(n <= 0){
            return -1;
        }
        if(dp[n] != 0){
            return dp[n];
        }
        for(int i = (int)Math.sqrt(n); i >= 1; i--){
            if(dfs(n - i*i, dp) < 0){
                return dp[n] = 1;
            }
        }
        return dp[n] = -1;
    }
}
