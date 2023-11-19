package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 08/10/2020.
 * #1547 https://leetcode.com/problems/minimum-cost-to-cut-a-stick/
 */
public class MinimumCostToCutAStick {

    // time O(n^3), space O(n^2)
    public int minCost(int n, int[] cuts) {
        List<Integer> cutsList = new ArrayList<>();
        cutsList.add(0);
        cutsList.add(n);
        for(int cut : cuts){
            cutsList.add(cut);
        }
        Collections.sort(cutsList);
        int size = cutsList.size();
        int[][] dp = new int[size][size];
        // smaller range needs to be calculated first
        for(int j = 0; j < size; j++){
            for(int i = j - 1; i >= 0; i--){
                for(int k = i + 1; k < j; k++){
                    dp[i][j] = Math.min(dp[i][j] == 0 ? Integer.MAX_VALUE : dp[i][j],
                            cutsList.get(j) - cutsList.get(i) + dp[i][k] + dp[k][j]);
                }
            }
        }
        return dp[0][size - 1];
    }

    // time O(n^3), space O(n^2)
    public int minCostMemo(int n, int[] cuts) {
        int size = cuts.length;
        int[] cutsArray = new int[size + 2];
        cutsArray[1] = n;
        System.arraycopy(cuts, 0, cutsArray, 2, size);
        Arrays.sort(cutsArray);
        return dfs(cutsArray, 0, size + 1, new Integer[size + 2][size + 2]);
    }

    private int dfs(int[] cuts, int left, int right, Integer[][] dp){
        if(dp[left][right] != null){
            return dp[left][right];
        }
        if(right - left <= 1){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for(int i = left + 1; i < right; i++){
            min = Math.min(min, cuts[right] - cuts[left] + dfs(cuts, left, i, dp)
                    + dfs(cuts, i, right, dp));
        }
        dp[left][right] = min;
        return min;
    }
}
