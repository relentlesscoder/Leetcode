package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 10/21/2016.
 * #0091 https://leetcode.com/problems/decode-ways/
 */
public class DecodeWays {

	// 三种解法的共同核心思路：
	// 划分型 DP：将数字字符串划分成合法编码段，每段对应一个字母（1-26）
	// 每个位置只有两种选择：
	// 长度 1：s[i] 单独解码（条件：s[i] != '0'）
	// 长度 2：s[i-1..i] 合并解码（条件：10 <= 数值 <= 26）
	// 类似 #3196，最后一段只有长度 1 和 2 两种选择，不需要 for 循环

	// time O(n), space O(1)
	public int numDecodingsDP(String s) {
		// 空间优化版 DP：只保留前两轮的状态，滚动更新
		int n = s.length(), s1 = 0, s2 = 1;
		// s1 = dp[i-1], s2 = dp[i]
		// 初始：dp[0] = 1（空串有 1 种解码方式）
		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);
			int res = 0;
			// 长度 1：s[i] 单独解码为一个字母（'1'-'9' 对应 A-I）
			if (c != '0') {
				res += s2; // s2 = dp[i]
			}
			// 长度 2：s[i-1..i] 合并解码（10-26 对应 J-Z）
			// 条件：s[i-1] == '1'（10-19）或 s[i-1] == '2' 且 s[i] <= '6'（20-26）
			if (i > 0 && (s.charAt(i - 1) == '1' || (s.charAt(i - 1) == '2' && c <= '6'))) {
				res += s1; // s1 = dp[i-1]
			}
			// 滚动
			s1 = s2;
			s2 = res;
		}
		return s2;
	}

	// time O(n), space O(n)
	public int numDecodingsDPWithArray(String s) {
		// 把记忆化搜索翻译成 DP
		int n = s.length();
		int[] dp = new int[n + 1];
		dp[0] = 1; // 空串有 1 种解码方式
		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);
			int res = 0;
			// 长度 1：s[i] 单独解码
			if (c != '0') {
				res += dp[i];
			}
			// 长度 2：s[i-1..i] 合并解码
			if (i > 0 && (s.charAt(i - 1) == '1' || (s.charAt(i - 1) == '2' && c <= '6'))) {
				res += dp[i - 1];
			}
			dp[i + 1] = res;
		}
		return dp[n];
	}

	// time O(n), space O(n)
	public int numDecodingsDFSWithMemorization(String s) {
		// 记忆化搜索
		int n = s.length();
		int[] memo = new int[n];
		Arrays.fill(memo, -1);
		return dfs(n - 1, s.toCharArray(), memo);
	}

	// dfs(i) = s[0..i] 的解码方式数
	private int dfs(int i, char[] s, int[] memo) {
		if (i == -1) {
			return 1; // 所有字符都解码完了，算 1 种方式
		}
		if (memo[i] != -1) {
			return memo[i];
		}
		int res = 0;
		// 长度 1：s[i] 单独解码，递归 s[0..i-1]
		if (s[i] != '0') {
			res += dfs(i - 1, s, memo);
		}
		// 长度 2：s[i-1..i] 合并解码，递归 s[0..i-2]
		if (i > 0 && (s[i - 1] == '1' || (s[i - 1] == '2' && s[i] <= '6'))) {
			res += dfs(i - 2, s, memo);
		}
		return memo[i] = res;
	}
}
