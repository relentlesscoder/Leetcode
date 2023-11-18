package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/18/2023.
 * #2218 https://leetcode.com/problems/maximum-value-of-k-coins-from-piles/
 */
public class MaximumValueOfKCoinsFromPiles {

    // time O(k^2 * n), space O(k * n)
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        List<int[]> prefixSum = new ArrayList<>();
        for (List<Integer> coins : piles) { // Pre-process prefix sum for each pile
            int[] p = new int[coins.size() + 1];
            for (int i = 1; i <= coins.size(); i++) {
                p[i] = p[i - 1] + coins.get(i - 1);
            }
            prefixSum.add(p);
        }
        int n = piles.size();
        int[][] dp = new int[k + 1][n + 1];
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                int[] curr = prefixSum.get(j - 1);
                for (int s = 0; s <= Math.min(i, curr.length - 1); s++) {
                    dp[i][j] = Math.max(dp[i][j], curr[s] + dp[i - s][j - 1]);
                }
            }
        }
        return dp[k][n];
    }
}
