package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 09/09/2019.
 * #0410 https://leetcode.com/problems/split-array-largest-sum/
 */
public class SplitArrayLargestSum {

	// time O(n * log(sum)), space O(1)
	public int splitArrayBinarySearch(int[] nums, int k) {
		// 核心思路: 二分答案, 在[最大元素, 总和]范围内二分子数组最大和
		// 对每个候选值贪心检查能否在k组内完成划分
		// low = 单个最大元素(子数组和的下界), high = 总和(所有元素一组)
		int low = 0, high = 0;
		for (int x : nums) {
			low = Math.max(low, x);
			high += x;
		}
		// lower bound: 找最小的合法子数组最大和
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (isValid(nums, mid, k)) {
				// mid可行, 尝试更小的值
				high = mid;
			} else {
				// mid不可行, 需要更大的值
				low = mid + 1;
			}
		}
		return low;
	}

	// 贪心检查: 每个子数组和不超过target时, 能否在k组内划分完所有元素
	private boolean isValid(int[] nums, int target, int k) {
		int count = 1, sum = 0; // count从1开始, 因为至少有一组
		for (int x : nums) {
			if (sum + x > target) {
				// 当前组放不下x, 开新组
				sum = x;
				count++;
			} else {
				sum += x;
			}
		}
		return count <= k;
	}

	// time O(n^2 * k), space O(n)
	public int splitArrayDPWithArray(int[] nums, int k) {
		// 核心思路: 划分型DP, dp[j] = 前j个元素划分成i+1组时的最小最大子数组和
		// 滚动数组优化: 从右往左更新避免覆盖上一轮的值
		int n = nums.length;
		// dp[j]: 前j个元素在当前轮(i+1组)下的最优解
		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0; // 0个元素, 0组, 最大和为0
		// 每轮增加一组
		for (int i = 0; i < k; i++) {
			// 从右往左更新, 防止覆盖上一轮dp[x]
			for (int j = n - 1; j >= i; j--) {
				int res = Integer.MAX_VALUE;
				// 枚举最后一组的起点x, sum = nums[x..j]的和
				for (int x = j, sum = 0; x >= i; x--) {
					sum += nums[x];
					if (sum >= res) {
						// 剪枝: 当前组的和已经 >= 当前最优, 再往左扩只会更大
						continue;
					}
					// max(当前组的和, 前x个元素的最优解) 取全局最小
					res = Math.min(res, Math.max(sum, dp[x]));
				}
				dp[j + 1] = res;
			}
		}
		return dp[n];
	}

	// time O(n^2 * k), space O(n * k)
	public int splitArrayDPWithGrid(int[] nums, int k) {
		// 核心思路: 与滚动数组版相同, 但保留完整二维数组
		// dp[i][j]: 前j个元素划分成i组时的最小最大子数组和
		int n = nums.length;
		int[][] dp = new int[k + 1][n + 1];
		Arrays.fill(dp[0], Integer.MAX_VALUE);
		dp[0][0] = 0; // 0个元素, 0组, 最大和为0
		// 每轮增加一组
		for (int i = 0; i < k; i++) {
			for (int j = n - 1; j >= i; j--) {
				int res = Integer.MAX_VALUE;
				// 枚举最后一组的起点x, sum = nums[x..j]的和
				for (int x = j, sum = 0; x >= i; x--) {
					sum += nums[x];
					if (sum >= res) {
						// 剪枝: 当前组的和已经 >= 当前最优
						continue;
					}
					// max(当前组的和, 前x个元素划分成i组的最优解)
					res = Math.min(res, Math.max(sum, dp[i][x]));
				}
				dp[i + 1][j + 1] = res;
			}
		}
		return dp[k][n];
	}

	// time O(n^2 * k), space O(n * k)
	public int splitArrayDFSWithMemorization(int[] nums, int k) {
		// 核心思路: 记忆化DFS, 从右往左划分, 每次枚举最后一组的起点
		int n = nums.length;
		// memo[i][j]: 前j+1个元素划分成i+1组的最小最大子数组和
		int[][] memo = new int[k][n];
		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}
		// 初始: k-1表示还需划分k组, j=n-1为最右元素
		return dfs(k - 1, n - 1, nums, memo);
	}

	// i: 剩余需要划分的组数-1, j: 当前处理到的最右元素索引
	private int dfs(int i, int j, int[] nums, int[][] memo) {
		if (i == -1) {
			// 所有组已划分完, j==-1表示元素恰好用完
			return j == -1 ? 0 : Integer.MAX_VALUE;
		}
		if (j == -1) {
			// 元素用完但还有组未划分, 不合法
			return Integer.MAX_VALUE;
		}
		if (memo[i][j] != -1) {
			return memo[i][j];
		}
		int res = Integer.MAX_VALUE;
		// 枚举当前组的起点k, sum = nums[k..j]的和
		for (int k = j, sum = 0; k >= i; k--) {
			sum += nums[k];
			if (sum >= res) {
				// 剪枝: 当前组的和已经 >= 当前最优
				continue;
			}
			// max(当前组的和, 剩余元素划分的最优解) 取全局最小
			res = Math.min(res, Math.max(sum, dfs(i - 1, k - 1, nums, memo)));
		}
		return memo[i][j] = res;
	}
}
