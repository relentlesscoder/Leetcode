package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 10/28/2016.
 * #0377 https://leetcode.com/problems/combination-sum-iv/
 */
public class CombinationSumIV {

    // time O(n * t), space O(t)
    public int combinationSum4(int[] nums, int target) {
        // 背包
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int x : nums) {
                if (i >= x) {
                    dp[i] += dp[i - x];
                }
            }
        }
        return dp[target];
    }

    // time O(n * t), space O(t)
    public int combinationSum4DFSWithMemorization(int[] nums, int target) {
        // 记忆化搜索
        int[] memo = new int[target + 1];
        Arrays.fill(memo, -1);
        return dfs(nums, target, memo);
    }

    private int dfs(int[] nums, int target, int[] memo) {
        if (target <= 0) {
            return target == 0 ? 1 : 0;
        }
        if (memo[target] != -1) {
            return memo[target];
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += dfs(nums, target - nums[i], memo);
        }
        return memo[target] = res;
    }
}
