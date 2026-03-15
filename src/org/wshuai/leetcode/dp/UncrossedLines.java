package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 10/24/2019.
 * #1035 https://leetcode.com/problems/uncrossed-lines/
 */
public class UncrossedLines {

	// time O(m * n), space O(n)
	public int maxUncrossedLines(int[] nums1, int[] nums2) {
		// 空间优化版 DP
		int m = nums1.length, n = nums2.length;
		int[] dp = new int[n + 1];
		for (int i = 0; i < m; i++) {
			int pre = 0;
			for (int j = 0; j < n; j++) {
				int x = dp[j + 1];
				if (nums1[i] == nums2[j]) {
					dp[j + 1] = 1 + pre;
				} else {
					dp[j + 1] = Math.max(dp[j + 1], dp[j]);
				}
				pre = x;
			}
		}
		return dp[n];
	}

	// time O(m * n), space O(m * n)
	public int maxUncrossedLinesDPWithGrid(int[] nums1, int[] nums2) {
		// 把记忆化搜索翻译成 DP
		int m = nums1.length, n = nums2.length;
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (nums1[i] == nums2[j]) {
					dp[i + 1][j + 1] = 1 + dp[i][j];
				} else {
					dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
				}
			}
		}
		return dp[m][n];
	}

	// time O(m * n), space O(m * n)
	public int maxUncrossedLinesDFSWithMemorization(int[] nums1, int[] nums2) {
		// 记忆化搜索
		int m = nums1.length, n = nums2.length;
		int[][] memo = new int[m][n];
		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}
		return dfs(m - 1, n - 1, nums1, nums2, memo);
	}

	private int dfs(int i, int j, int[] nums1, int[] nums2, int[][] memo) {
		if (i == -1 || j == -1) { // 其中一个数组为空，无法连接任何线
			return 0;
		}
		if (memo[i][j] != -1) {
			return memo[i][j];
		}
		if (nums1[i] == nums2[j]) { // 两个数字匹配则可以连接一条线，状态转移方程 dfs(i, j) = 1 + dfs(i - 1, j - 1)
			return memo[i][j] = 1 + dfs(i - 1, j - 1, nums1, nums2, memo);
		} else { // 不匹配则选择跳过其中的一个，看看哪个能得到更多的连接线
			return memo[i][j] = Math.max(dfs(i - 1, j, nums1, nums2, memo), dfs(i, j - 1, nums1, nums2, memo));
		}
	}
}
