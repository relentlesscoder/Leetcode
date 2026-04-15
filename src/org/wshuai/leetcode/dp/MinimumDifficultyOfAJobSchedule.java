package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 01/26/2020.
 * #1335 https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/
 */
public class MinimumDifficultyOfAJobSchedule {

	private static final int MAX = (int) 1e6;

	// time O(d * n^2), space O(n)
	public int minDifficultyDP(int[] jobDifficulty, int d) {
		// 核心思路: 划分型 DP, 将 n 个工作恰好分配到 d 天, 每天难度 = 该天工作难度的最大值
		// 滚动数组优化: j 从大到小遍历, 保证 dp[k] 读到的是上一轮 (i 天) 的值
		int n = jobDifficulty.length;
		if (n < d) {
			// 工作数少于天数, 无法每天至少安排一个工作
			return -1;
		}
		// dp[j+1] = 用当前天数安排 jobs[0..j] 的最小难度总和
		int[] dp = new int[n + 1];
		Arrays.fill(dp, MAX);
		dp[0] = 0; // 0 天安排空任务, 难度为 0
		// 外层: 枚举天数 (第 1 天, 第 2 天, ..., 第 d 天)
		for (int i = 0; i < d; i++) {
			// 中层: 枚举当前天的最后一个工作 j, 逆序保证读到上一轮的 dp 值
			// j >= i: 前面至少需要 i 个工作给前 i 天
			for (int j = n - 1; j >= i; j--) {
				int res = MAX;
				// 内层: 枚举当前天的第一个工作 k, 边扫边维护区间 [k, j] 的最大难度
				for (int k = j, max = 0; k >= i; k--) {
					max = Math.max(max, jobDifficulty[k]); // 区间 [k, j] 的最大难度
					// dp[k] 是上一轮的值: i 天安排 jobs[0..k-1] 的最小难度总和
					res = Math.min(res, max + dp[k]);
				}
				dp[j + 1] = res;
			}
		}
		return dp[n];
	}

	// time O(d * n^2), space O(d * n)
	public int minDifficultyDPWithGrid(int[] jobDifficulty, int d) {
		// 核心思路: 与滚动数组版相同, 但用二维数组保存全部状态, 便于理解和调试
		int n = jobDifficulty.length;
		if (n < d) {
			return -1;
		}
		// dp[i][j+1] = 用 i 天安排 jobs[0..j] 的最小难度总和
		int[][] dp = new int[d + 1][n + 1];
		Arrays.fill(dp[0], MAX);
		dp[0][0] = 0; // 0 天安排空任务, 难度为 0
		for (int i = 0; i < d; i++) {
			dp[i + 1][0] = MAX; // 用 i+1 天安排 0 个工作, 不合法
			for (int j = n - 1; j >= i; j--) {
				int res = MAX;
				for (int k = j, max = 0; k >= i; k--) {
					max = Math.max(max, jobDifficulty[k]); // 区间 [k, j] 的最大难度
					// dp[i][k]: 用 i 天安排 jobs[0..k-1] 的最小难度总和
					res = Math.min(res, max + dp[i][k]);
				}
				dp[i + 1][j + 1] = res;
			}
		}
		return dp[d][n];
	}

	// time O(d * n^2), space O(d * n)
	public int minDifficultyDFSWithMemorization(int[] jobDifficulty, int d) {
		// 核心思路: 记忆化搜索, dfs(i, j) = 用 i+1 天安排 jobs[0..j] 的最小难度总和
		int n = jobDifficulty.length;
		if (n < d) {
			return -1;
		}
		int[][] memo = new int[d][n];
		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}
		return dfs(d - 1, n - 1, jobDifficulty, memo);
	}

	// dfs(i, j) = 用 i+1 天安排 jobs[0..j] 的最小难度总和
	private int dfs(int i, int j, int[] nums, int[][] memo) {
		if (i == -1) {
			// 天数用完: 工作也用完 -> 合法, 返回 0; 否则 -> 不合法
			return j == -1 ? 0 : MAX;
		}
		if (j == -1) {
			// 工作用完但还有剩余天数, 无法每天安排至少一个工作 -> 不合法
			return MAX;
		}
		if (memo[i][j] != -1) {
			return memo[i][j];
		}
		int res = MAX;
		// 枚举第 i+1 天的第一个工作 k, 当天安排 jobs[k..j]
		for (int k = j, max = 0; k >= i; k--) {
			max = Math.max(max, nums[k]); // 区间 [k, j] 的最大难度
			// 当天难度 max + 前 i 天安排 jobs[0..k-1] 的最小难度总和
			res = Math.min(res, max + dfs(i - 1, k - 1, nums, memo));
		}
		return memo[i][j] = res;
	}
}
