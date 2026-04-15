package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 04/15/2026.
 * #2786
 * https://leetcode.com/problems/visit-array-positions-to-maximize-score/
 */
public class VisitArrayPositionsToMaximizeScore {

	// time O(n), space O(1)
	public long maxScoreDP(int[] nums, int x) {
		// 核心思路: 状态机DP, 按奇偶性分两个状态, 同奇偶免费跳转, 切换奇偶扣x分
		// d0: 最后访问的是偶数时的最大得分, d1: 最后访问的是奇数时的最大得分
		int n = nums.length;
		long d0 = nums[0] % 2 == 0 ? nums[0] : Long.MIN_VALUE / 2; // 首元素为偶数则初始化, 否则不合法
		long d1 = nums[0] % 2 == 1 ? nums[0] : Long.MIN_VALUE / 2; // 首元素为奇数则初始化
		for (int i = 1; i < n; i++) {
			if (nums[i] % 2 == 0) {
				// 当前为偶数: 不选(保持d0) 或 选(从偶数状态免费跳 或 从奇数状态扣x)
				d0 = Math.max(d0, Math.max(d0, d1 - x) + nums[i]);
			} else {
				// 当前为奇数: 不选(保持d1) 或 选(从奇数状态免费跳 或 从偶数状态扣x)
				d1 = Math.max(d1, Math.max(d1, d0 - x) + nums[i]);
			}
		}
		return Math.max(d0, d1);
	}

	// time O(n), space O(n)
	public long maxScoreDPWithGrid(int[] nums, int x) {
		// 核心思路: 与滚动变量版相同, 但保留完整二维数组
		// dp[i][j]: 前i+1个元素中, 最后访问的奇偶性为j(0=偶, 1=奇)时的最大得分
		int n = nums.length;
		long[][] dp = new long[n][2];
		dp[0][nums[0] % 2] = nums[0]; // 首元素对应的奇偶状态
		dp[0][1 - nums[0] % 2] = Long.MIN_VALUE / 2; // 另一个奇偶状态不合法
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < 2; j++) {
				if (nums[i] % 2 == j) {
					// nums[i]的奇偶性 == j: 不选(继承) 或 选(同奇偶免费 或 异奇偶扣x)
					dp[i][j] = Math.max(dp[i - 1][j], Math.max(dp[i - 1][j], dp[i - 1][1 ^ j] - x) + nums[i]);
				} else {
					// nums[i]的奇偶性 != j: 无法通过选nums[i]到达状态j, 直接继承
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		return Math.max(dp[n - 1][0], dp[n - 1][1]);
	}

	// time O(n), space O(n)
	public long maxScoreDFSWithMemorization(int[] nums, int x) {
		// 核心思路: 记忆化DFS, 从右往左决策每个位置选或不选
		int n = nums.length;
		// memo[i][j]: 前i+1个元素中, 最后访问的奇偶性为j时的最大得分
		long[][] memo = new long[n][2];
		for (long[] row : memo) {
			Arrays.fill(row, Long.MIN_VALUE);
		}
		// 最后访问偶数或奇数取较大值
		return Math.max(dfs(n - 1, 0, x, nums, memo), dfs(n - 1, 1, x, nums, memo));
	}

	// i: 当前位置, j: 目标奇偶性(0=偶, 1=奇)
	private long dfs(int i, int j, int x, int[] nums, long[][] memo) {
		if (i == 0) {
			// 首元素: 奇偶性匹配则取值, 否则不合法
			return nums[0] % 2 == j ? nums[0] : Long.MIN_VALUE / 2;
		}
		if (memo[i][j] != Long.MIN_VALUE) {
			return memo[i][j];
		}
		if (nums[i] % 2 == j) {
			// nums[i]奇偶性匹配: 不选(继承) 或 选(同奇偶免费 或 异奇偶扣x)
			return memo[i][j] = Math.max(dfs(i - 1, j, x, nums, memo),
					Math.max(dfs(i - 1, j, x, nums, memo), dfs(i - 1, 1 ^ j, x, nums, memo) - x) + nums[i]);
		}
		// 奇偶性不匹配: 无法选nums[i]到达状态j, 跳过
		return memo[i][j] = dfs(i - 1, j, x, nums, memo);
	}
}
