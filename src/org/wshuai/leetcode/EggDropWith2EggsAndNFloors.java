package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/12/2023.
 * #1884 https://leetcode.com/problems/egg-drop-with-2-eggs-and-n-floors/
 */
public class EggDropWith2EggsAndNFloors {

    // time O(E * F^2), space O(E * F)
    public int twoEggDropDFS(int n) {
        int[][] dp = new int[3][n + 1];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        return dfs(2, n, dp);
    }

    private int dfs(int eggs, int floors, int[][] dp) {
        if (floors == 0 || floors == 1 || eggs == 1) {
            return floors;
        }
        if (dp[eggs][floors] != -1) {
            return dp[eggs][floors];
        }
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= floors; i++) {
            res = Math.min(res, 1 + Math.max(dfs(eggs - 1, i - 1, dp), dfs(eggs, floors - i, dp)));
        }
        dp[eggs][floors] = res;
        return res;
    }

}
