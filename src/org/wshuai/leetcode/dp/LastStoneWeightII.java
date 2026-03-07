package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 10/18/2019.
 * #1049 https://leetcode.com/problems/last-stone-weight-ii/
 */
public class LastStoneWeightII {

    // time O(n * t), space O(n * t)
    public int lastStoneWeightIIDFSWithMemorization(int[] stones) {
        int n = stones.length, sum = 0, target = 0;
        for (int s : stones) {
            sum += s;
        }
        target = sum / 2;
        int[][] memo = new int[n][target + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return sum - 2 * (target - dfs(n - 1, target, stones, memo));
    }

    private int dfs(int i, int target, int[] stones, int[][] memo) {
        if (target < 0) {
            return 10000;
        }
        if (i == -1) {
            return target;
        }
        if (memo[i][target] != -1) {
            return memo[i][target];
        }
        return memo[i][target] = Math.min(dfs(i - 1, target - stones[i], stones, memo),
                dfs(i - 1, target, stones, memo));
    }
}
