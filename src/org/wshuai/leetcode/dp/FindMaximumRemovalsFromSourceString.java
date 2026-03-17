package org.wshuai.leetcode.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 03/16/2026.
 * #3316
 * https://leetcode.com/problems/find-maximum-removals-from-source-string/
 */
public class FindMaximumRemovalsFromSourceString {

	// time O(m * n), space O(n)
	public int maxRemovals(String source, String pattern, int[] targetIndices) {
		// 空间优化版 DP
		int m = source.length(), n = pattern.length();
		Set<Integer> idx = new HashSet<>();
		for (int x : targetIndices) {
			idx.add(x);
		}
		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MIN_VALUE / 2);
		dp[0] = 0;
		for (int i = 0; i < m; i++) {
			int pre = dp[0], v = idx.contains(i) ? 1 : 0;
			dp[0] += v;
			for (int j = 0; j < n; j++) {
				int x = dp[j + 1];
				dp[j + 1] += v;
				if (source.charAt(i) == pattern.charAt(j)) {
					dp[j + 1] = Math.max(dp[j + 1], pre);
				}
				pre = x;
			}
		}
		return dp[n];
	}

	// time O(m * n), space O(m * n)
	public int maxRemovalsDPWithGrid(String source, String pattern, int[] targetIndices) {
		// 把记忆化搜索翻译成 DP
		int m = source.length(), n = pattern.length();
		Set<Integer> idx = new HashSet<>();
		for (int x : targetIndices) {
			idx.add(x);
		}
		int[][] dp = new int[m + 1][n + 1];
		Arrays.fill(dp[0], Integer.MIN_VALUE / 2);
		dp[0][0] = 0;
		for (int i = 0; i < m; i++) {
			int v = idx.contains(i) ? 1 : 0;
			dp[i + 1][0] = dp[i][0] + v;
			for (int j = 0; j < n; j++) {
				dp[i + 1][j + 1] = dp[i][j + 1] + v;
				if (source.charAt(i) == pattern.charAt(j)) {
					dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], dp[i][j]);
				}
			}
		}
		return dp[m][n];
	}

	// time O(m * n * log(t)), space O(m * n)
	public int maxRemovalsDFSWithMemorization(String source, String pattern, int[] targetIndices) {
		// 记忆化搜索
		int m = source.length(), n = pattern.length();
		Set<Integer> idx = new HashSet<>();
		for (int x : targetIndices) {
			idx.add(x);
		}
		int[][] memo = new int[m + 1][n + 1];
		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}
		return dfs(m - 1, n - 1, source.toCharArray(), pattern.toCharArray(), idx, targetIndices, memo);
	}

	private int dfs(int i, int j, char[] s, char[] p, Set<Integer> set, int[] idx, int[][] memo) {
		// 如果模式串已经匹配完了，说明我们可以删除 idx 中的所有剩余的字符，返回 idx 中剩余的字符数量
		if (j == -1) {
			// 二分查找所有小于等于 i 的元素数量
			return binarySearch(idx, i + 1);
		}
		// 如果源字符串已经匹配完了但模式串还没有匹配完，说明当前的删除方案无法匹配模式串，返回一个很大的
		// 数表示不合法
		if (i == -1) {
			return Integer.MIN_VALUE / 2;
		}
		if (memo[i][j] != -1) {
			return memo[i][j];
		}
		// 总是可以选择不用当前的字符来匹配，如果当前字符在 idx 中，则删除它否则不删除
		int res = dfs(i - 1, j, s, p, set, idx, memo) + (set.contains(i) ? 1 : 0);
		// 如果当前字符和模式串的当前字符相等，则也可以选择用当前的字符来匹配
		if (s[i] == p[j]) {
			res = Math.max(res, dfs(i - 1, j - 1, s, p, set, idx, memo));
		}
		return memo[i][j] = res;
	}

	private int binarySearch(int[] nums, int target) {
		int low = 0, high = nums.length;
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
}
