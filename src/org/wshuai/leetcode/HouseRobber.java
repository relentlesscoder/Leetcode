package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/17/2016.
 * #0198 https://leetcode.com/problems/house-robber/
 */
public class HouseRobber {

    // time O(n), space O(1)
    public int rob(int[] nums) {
		// 因为 dp[i] 只与 dp[i - 1] 和 dp[i - 2] 有关，我们可以优化为使用两个变量来存 i 前面
		// 两个值。
        int n = nums.length,
				h1 = 0, // 上一个房子
				h2 = 0; // 上上个房子
        for (int i = 0; i < n; i++) {
            int h = Math.max(h1, h2 + nums[i]); // 计算到当前房子的最大收益
            h2 = h1; // 将上上个房子的收益更新为上个房子的收益
            h1 = h; // 将上一个房子的收益更新为到当前房子的收益
        }
        return h1;
    }

    // time O(n), space O(n)
    public int robDPWithArray(int[] nums) {
        int n = nums.length;
        // 为了方便处理 i - 1 和 i - 2, 将数组长度设为 n + 2. 则房子 i 的答案位置为 dp[i + 2]。
        int[] dp = new int[n + 2];
        for (int i = 0; i < n; i++) {
            dp[i + 2] = Math.max(dp[i + 1], dp[i] + nums[i]);
        }
        return dp[n + 1];
    }

    // time O(n), space O(n)
    public int robDFSWithMemorization(int[] nums) {
        int n = nums.length;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return dfs(n - 1, nums, memo);
    }

    private int dfs(int i, int[] nums, int[] memo) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        // 设 dp[i] 为 打劫到第 i 个房子的最大收益，则 dp[i] 是下面两者的较大值
        //   1. 不打劫房子 i - dp[i - 1]
        //   2. 打劫房子 i - dp[i - 2] + nums[i]
        return memo[i] = Math.max(dfs(i - 1, nums, memo),
                dfs(i - 2, nums, memo) + nums[i]);
    }
}
