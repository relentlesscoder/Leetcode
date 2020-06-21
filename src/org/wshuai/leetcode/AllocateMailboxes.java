package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 06/17/2020.
 * #1478 https://leetcode.com/problems/allocate-mailboxes/
 */
public class AllocateMailboxes {

    private static final int MAX = 100, INF = 1_000_000;

    // time O(n^3), space O(n^2)
    public int minDistance(int[] houses, int k) {
        int[][] cost = new int[MAX][MAX], dp = new int[MAX][MAX];
        int n = houses.length;
        Arrays.sort(houses);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int t = i; t <= j; t++){
                    // to get min distance of cost[i][j], the mailbox needs to
                    // be at the median of i and j
                    cost[i][j] += Math.abs(houses[(i + j) / 2] - houses[t]);
                }
            }
        }
        return dfs(houses, n, k, 0, cost, dp);
    }

    private int dfs(int[] houses, int n, int k, int i, int[][] cost, int[][] dp){
        if(k == 0 && i == n){
            return 0;
        }
        if(k == 0 || i == n){
            return INF;
        }
        if(dp[k][i] != 0){
            return dp[k][i];
        }
        int ans = INF;
        for(int j = i; j < n; j++){
            ans = Math.min(ans, cost[i][j] + dfs(houses, n, k - 1, j + 1, cost, dp));
        }
        dp[k][i] = ans;
        return ans;
    }
}
