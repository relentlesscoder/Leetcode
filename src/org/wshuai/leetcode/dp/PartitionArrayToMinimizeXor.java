package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 03/29/2026.
 * #3599
 * https://leetcode.com/problems/partition-array-to-minimize-xor/
 */
public class PartitionArrayToMinimizeXor {

	// 三种解法的共同核心思路:
	// 划分型 DP: 将数组恰好划分成 k 段, 最小化所有段 XOR 值的最大值
	// 与之前的划分型 DP 不同:
	// 1. 多了一维状态: 需要记录已使用的段数 i
	// 2. 目标不是 sum/count 而是 min(max(...)), 即最小化瓶颈
	// 3. XOR 无单调性, 无法用滑动窗口/前缀和优化, O(k * n²) 已是最优
	//
	// 剪枝: xor >= res 时 continue, 当前段 XOR 已经 >= 已知最优解, 这个分割点不可能更好

	// time O(k * n^2), space O(n)
	public int minXorDPWithArray(int[] nums, int k) {
		// 空间优化: 原地滚动, 不需要 next 数组
		// 关键: j 从大到小遍历, 保证 dp[l] 读到的是上一轮 (i 段) 的值
		// 原理和 0/1 背包的内循环从大到小一样: 防止用到本轮已更新的值
		int n = nums.length;
		// dp[j+1] = 用当前段数划分 nums[0..j] 时, 所有段 XOR 最大值的最小值
		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0; // 0 段划分空数组
		// 外层: 枚举段数 (第 1 段, 第 2 段, ..., 第 k 段)
		for (int i = 0; i < k; i++) {
			// 中层: 枚举当前段的终点 j, 从大到小 (滚动数组必须逆序)
			// j >= i: 前面至少需要 i 个元素给前 i 段
			for (int j = n - 1; j >= i; j--) {
				int res = Integer.MAX_VALUE;
				// 内层: 枚举当前段的起点 l, 边扫边算 XOR
				for (int l = j, xor = 0; l >= i; l--) {
					xor ^= nums[l]; // 段 [l, j] 的 XOR
					if (xor >= res) {
						continue; // 剪枝: 当前段 XOR >= 已知最优
					}
					// dp[l] 是上一轮的值 (i 段划分 nums[0..l-1]), 因为 j 逆序保证未被覆盖
					res = Math.min(res, Math.max(xor, dp[l]));
				}
				dp[j + 1] = res;
			}
		}
		return dp[n];
	}

	// time O(k * n^2), space O(k * n)
	public int minXorDPWithGrid(int[] nums, int k) {
		// 把记忆化搜索翻译成 DP
		int n = nums.length;
		// dp[i][j+1] = 用 i 段划分 nums[0..j] 时, 所有段 XOR 最大值的最小值
		int[][] dp = new int[k + 1][n + 1];
		Arrays.fill(dp[0], Integer.MAX_VALUE);
		dp[0][0] = 0; // 0 段划分空数组
		for (int i = 0; i < k; i++) {
			for (int j = n - 1; j >= i; j--) {
				int res = Integer.MAX_VALUE;
				for (int l = j, xor = 0; l >= i; l--) {
					xor ^= nums[l];
					if (xor >= res) {
						continue;
					}
					res = Math.min(res, Math.max(xor, dp[i][l]));
				}
				dp[i + 1][j + 1] = res;
			}
		}
		return dp[k][n];
	}

	// time O(k * n^2), space O(k * n)
	public int minXorDFSWithMemorization(int[] nums, int k) {
		// 记忆化搜索
		// dfs(i, j) = 用 i+1 段划分 nums[0..j] 时, 所有段 XOR 最大值的最小值
		int n = nums.length;
		int[][] memo = new int[k][n];
		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}
		return dfs(k - 1, n - 1, nums, memo);
	}

	// dfs(i, j) = 用 i+1 段划分 nums[0..j] 时, 所有段 XOR 最大值的最小值
	private int dfs(int i, int j, int[] nums, int[][] memo) {
		if (i == -1) {
			return j == -1 ? 0 : Integer.MAX_VALUE; // 段数用完: 元素也用完→合法, 否则→不合法
		}
		if (memo[i][j] != -1) {
			return memo[i][j];
		}
		int res = Integer.MAX_VALUE;
		// 枚举第 i+1 段的起点 k, 段 [k, j] 的 XOR 值边扫边算
		for (int k = j, xor = 0; k >= i; k--) {
			xor ^= nums[k]; // 段 [k, j] 的 XOR
			if (xor >= res) {
				continue; // 剪枝
			}
			// max(当前段 XOR, 前 i 段的最大 XOR) 取最小
			res = Math.min(res, Math.max(xor, dfs(i - 1, k - 1, nums, memo)));
		}
		return memo[i][j] = res;
	}
}
