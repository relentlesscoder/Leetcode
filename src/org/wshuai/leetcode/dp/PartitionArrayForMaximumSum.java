package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 10/28/2019.
 * #1043 https://leetcode.com/problems/partition-array-for-maximum-sum/
 */
public class PartitionArrayForMaximumSum {

	// 两种解法的共同核心思路:
	// 划分型 DP: 将数组划分成若干段, 每段长度 <= k
	// 每段内所有元素变成该段的最大值, 求变换后数组的最大总和
	// 枚举最后一段的起点 j, 最后一段为 arr[j..i], 长度 = i-j+1 <= k
	// 该段的贡献 = max(arr[j..i]) * (i-j+1)

	// time O(n * k), space O(n)
	public int maxSumAfterPartitioningDP(int[] arr, int k) {
		// dp[i+1] = arr[0..i] 的最大总和
		int n = arr.length;
		int[] dp = new int[n + 1];
		for (int i = 0; i < n; i++) {
			int res = 0, max = 0;
			// 枚举最后一段的起点 j, 从 i 往左扫, 最远到 i-k+1 (段长度不超过 k)
			for (int j = i; j >= Math.max(i - k + 1, 0); j--) {
				// 维护 arr[j..i] 的最大值
				max = Math.max(max, arr[j]);
				// 该段贡献 = max * 段长度, 前面 arr[0..j-1] 的最优值 = dp[j]
				res = Math.max(res, max * (i - j + 1) + dp[j]);
			}
			dp[i + 1] = res;
		}
		return dp[n];
	}

	// time O(n * k), space O(n)
	public int maxSumAfterPartitioningDFSWithMemorization(int[] arr, int k) {
		// dfs(i) = arr[0..i] 的最大总和
		int n = arr.length;
		int[] memo = new int[n];
		Arrays.fill(memo, -1);
		return dfs(n - 1, k, arr, memo);
	}

	// dfs(i) = arr[0..i] 的最大总和
	private int dfs(int i, int k, int[] arr, int[] memo) {
		if (i == -1) {
			return 0; // 所有元素都处理完了
		}
		if (memo[i] != -1) {
			return memo[i];
		}
		int res = 0, max = 0;
		// 枚举最后一段的起点 j, 最后一段为 arr[j..i]
		for (int j = i; j >= Math.max(i - k + 1, 0); j--) {
			max = Math.max(max, arr[j]); // arr[j..i] 的最大值
			// 该段所有元素变成 max, 贡献 = max * (i-j+1), 递归处理 arr[0..j-1]
			res = Math.max(res, max * (i - j + 1) + dfs(j - 1, k, arr, memo));
		}
		return memo[i] = res;
	}
}
