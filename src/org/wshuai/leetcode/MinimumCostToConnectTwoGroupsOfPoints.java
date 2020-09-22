package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 09/21/2020.
 * #1595 https://leetcode.com/problems/minimum-cost-to-connect-two-groups-of-points/
 */
public class MinimumCostToConnectTwoGroupsOfPoints {

    // time O(), space O(m*n)
    public int connectTwoGroups(List<List<Integer>> cost) {
        int m = cost.size(), n = cost.get(0).size();
        int[] right = new int[n];
        Arrays.fill(right, Integer.MAX_VALUE);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                right[i] = Math.min(right[i], cost.get(j).get(i));
            }
        }
        return dfs(0, cost, 0, right, new int[m + 1][1 << n]);
    }

    private int dfs(int start, List<List<Integer>> cost, int mask, int[] right, int[][] dp){
        if(dp[start][mask] > 0){
            return dp[start][mask];
        }
        int res = 0;
        if(start == cost.size()){
            for(int i = 0; i < right.length; i++){
                if((mask & (1 << i)) == 0){
                    res += right[i];
                }
            }
        }else{
            res = Integer.MAX_VALUE;
            for(int i = 0; i < cost.get(0).size(); i++){
                res = Math.min(res, cost.get(start).get(i) + dfs(start + 1, cost, mask | (1 << i), right, dp));
            }
        }
        return dp[start][mask] = res;
    }
}
