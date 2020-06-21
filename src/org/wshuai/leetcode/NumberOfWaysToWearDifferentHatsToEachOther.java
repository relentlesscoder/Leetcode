package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 05/03/2020.
 * #1434 https://leetcode.com/problems/number-of-ways-to-wear-different-hats-to-each-other/
 */
public class NumberOfWaysToWearDifferentHatsToEachOther {

    private static final int MOD = 1_000_000_007;

    // time O(40*n*2^n), space O(40*2^10)
    public int numberWays(List<List<Integer>> hats) {
        int n = hats.size();
        // assign hats to people
        List<Integer>[] hatToPeople = new ArrayList[41];
        for(int i = 1; i < 41; i++){
            hatToPeople[i] = new ArrayList<>();
        }
        for(int p = 0; p < n; p++){
            for(int h : hats.get(p)){
                hatToPeople[h].add(p);
            }
        }
        return dfs(1, 0, (1 << n) - 1, hatToPeople, new Integer[41][1024]);
    }

    private int dfs(int start, int status, int target, List<Integer>[] hatToPeople, Integer[][] dp){
        if(status == target){
            return 1;
        }
        if(start > 40){
            return 0;
        }
        if(dp[start][status] != null){
            return dp[start][status];
        }
        // do not assign this hat to anybody
        int res = dfs(start + 1, status, target, hatToPeople, dp);
        for(int p : hatToPeople[start]){
            if(((1 << p) & status) != 0){
                continue;
            }
            res = (res + dfs(start + 1, status | (1 << p), target, hatToPeople, dp)) % MOD;
        }
        dp[start][status] = res;
        return res;
    }
}
