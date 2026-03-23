package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 10/04/2019.
 * #0673 https://leetcode.com/problems/number-of-longest-increasing-subsequence/
 */
public class NumberOfLongestIncreasingSubsequence {

	// time O(n * log(n)), space O(n)
	public int findNumberOfLISBinaryIndexedTree(int[] nums) {
		// 核心思路：用 BIT 同时维护 LIS 长度和对应的方案数来优化 O(n²) DP
		// 普通 LIS 只关心长度，这题还需要统计最长子序列有多少个
		// BIT 的每个位置存 [最长长度, 方案数]，支持前缀查询"值 < 当前值的最优 LIS 信息"
		// 离散化：去重 + 排序，将值映射到连续的下标，压缩 BIT 的值域
		int[] sorted = Arrays.stream(nums).distinct().sorted().toArray();
		int res = 0, lis = 0, n = nums.length, m = sorted.length;
		// BIT 以离散化后的值作为下标，tree[v] = [以值 v 结尾的最长 LIS 长度, 方案数]
		BIT bit = new BIT(m);
		for (int i = 0; i < n; i++) {
			// 二分找到 nums[i] 在离散化数组中的位置，+1 转为 BIT 的 1-indexed
			int idx = binarySearch(sorted, nums[i]) + 1;
			// 查询值 < nums[i] 的所有元素中，最长 LIS 长度和方案数
			// idx-1 对应所有严格小于 nums[i] 的值（因为 sorted 是去重排序的）
			int[] q = bit.pre(idx - 1);
			// 加上 nums[i] 自身，LIS 长度 +1，方案数继承（至少为 1，表示 nums[i] 单独成序列）
			int len = q[0] + 1, cnt = Math.max(q[1], 1);
			// 将 [len, cnt] 更新到 BIT 的 idx 位置
			bit.update(idx, len, cnt);
			// 维护全局最长 LIS 长度和对应方案数
			if (len > lis) {
				lis = q[0] + 1;
				res = cnt;
			} else if (len == lis) {
				res += cnt;
			}
		}
		return res;
	}

	// time O(n^2), space O(n)
	public int findNumberOfLISDP(int[] nums) {
		// 经典 O(n²) DP，dp[i] = [以 nums[i] 结尾的 LIS 长度, 方案数]
		int res = 0, lis = 0, n = nums.length;
		// dp[i][0] = 以 nums[i] 结尾的 LIS 长度
		// dp[i][1] = 以 nums[i] 结尾的 LIS 方案数
		// 初始化：每个元素自身构成长度为 1 的子序列，方案数为 1
		int[][] dp = new int[n][2];
		Arrays.setAll(dp, i -> new int[] { 1, 1 });
		for (int i = 0; i < n; i++) {
			// 遍历 i 之前的所有元素 j，尝试将 nums[i] 接在 nums[j] 后面
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) { // 严格递增，可以接在后面
					if (dp[j][0] + 1 > dp[i][0]) {
						// 发现更长的 LIS，更新长度，方案数继承自 j
						dp[i][0] = dp[j][0] + 1;
						dp[i][1] = dp[j][1];
					} else if (dp[j][0] + 1 == dp[i][0]) {
						// LIS 长度相同，累加方案数
						dp[i][1] += dp[j][1];
					}
				}
			}
			// 维护全局最长 LIS 长度和对应方案数
			if (dp[i][0] > lis) {
				lis = dp[i][0];
				res = dp[i][1];
			} else if (dp[i][0] == lis) {
				res += dp[i][1];
			}
		}
		return res;
	}

	private int binarySearch(int[] nums, int target) {
		// lower bound: 找 sorted 中第一个 >= target 的位置（用于离散化映射）
		int low = 0, high = nums.length - 1;
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (nums[mid] < target) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return low;
	}

	// 树状数组：每个位置存 [LIS 长度, 方案数]
	// 与普通 BIT（求前缀和/前缀最大值）不同，这里的合并逻辑需要同时维护长度和计数
	private static class BIT {

		private final int[][] tree; // tree[i] = [最长 LIS 长度, 方案数]

		public BIT(int n) {
			tree = new int[n + 1][2];
		}

		// 单点更新：在 index 位置更新 [len, count]
		public void update(int index, int len, int count) {
			while (index < tree.length) {
				int lis = tree[index][0];
				if (len > lis) {
					// 发现更长的 LIS，替换长度和方案数
					tree[index][0] = len;
					tree[index][1] = count;
				} else if (len == lis) {
					// LIS 长度相同，累加方案数
					tree[index][1] += count;
				}
				index += index & -index; // lowbit 跳到下一个管辖区间
			}
		}

		// 前缀查询：查询 [1, index] 范围内的最长 LIS 长度和方案数
		public int[] pre(int index) {
			int lis = 0, count = 0;
			while (index > 0) {
				if (tree[index][0] > lis) {
					// 发现更长的 LIS，替换
					lis = tree[index][0];
					count = tree[index][1];
				} else if (tree[index][0] == lis) {
					// LIS 长度相同，累加方案数
					count += tree[index][1];
				}
				index -= index & -index; // lowbit 跳到前一个管辖区间
			}
			return new int[] { lis, count };
		}
	}
}
