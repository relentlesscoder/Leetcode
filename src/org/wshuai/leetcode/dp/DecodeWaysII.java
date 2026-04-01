package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 11/26/2019.
 * #0639 https://leetcode.com/problems/decode-ways-ii/
 */
public class DecodeWaysII {

	private static final int MOD = (int) 1e9 + 7;

	// 三种解法的共同核心思路：
	// #0091 Decode Ways 的扩展，增加了 '*' 通配符（代表 1-9）
	// 划分型 DP，与 #0091 完全相同的结构：每个位置选择长度 1 或长度 2 的解码段
	// 区别在于 '*' 引入了多种解码可能，需要乘以对应的方案数
	//
	// 长度 1 单独解码的方案数：
	// s[i] = '1'-'9' → 1 种
	// s[i] = '*' → 9 种（* 代表 1-9）
	// s[i] = '0' → 0 种（'0' 不能单独解码）
	//
	// 长度 2 合并解码的方案数（s[i-1], s[i] 组成 10-26）：
	// *, * → 15 种（11-19 有 9 个 + 21-26 有 6 个）
	// 1, * → 9 种（11-19）
	// 2, * → 6 种（21-26）
	// *, 0-6 → 2 种（* 可以是 1 或 2，即 10-16 和 20-26）
	// *, 7-9 → 1 种（* 只能是 1，即 17/18/19）
	// 1, 0-9 → 1 种（10-19）
	// 2, 0-6 → 1 种（20-26）

	// time O(n), space O(1)
	public int numDecodingsDP(String s) {
		// 空间优化版 DP：只保留前两轮的状态，滚动更新
		char[] sc = s.toCharArray();
		int n = s.length(), s0 = 0, s1 = 1;
		// s0 = dp[i-1], s1 = dp[i]
		for (int i = 0; i < n; i++) {
			long res = 0;
			// 长度 1：s[i] 单独解码
			if (sc[i] >= '1' && sc[i] <= '9') {
				res = (res + s1) % MOD; // 1 种
			} else if (sc[i] == '*') {
				res = (res + 9L * s1 % MOD) % MOD; // 9 种（* → 1-9）
			}
			// 长度 2：s[i-1], s[i] 合并解码
			if (i > 0) {
				if (sc[i - 1] == '*' && sc[i] == '*') {
					res = (res + 15L * s0 % MOD) % MOD; // 15 种：11-19(9) + 21-26(6)
				} else if (sc[i - 1] == '1' && sc[i] == '*') {
					res = (res + 9L * s0 % MOD) % MOD; // 9 种：11-19
				} else if (sc[i - 1] == '2' && sc[i] == '*') {
					res = (res + 6L * s0 % MOD) % MOD; // 6 种：21-26
				} else if (sc[i - 1] == '*' && sc[i] >= '0' && sc[i] <= '6') {
					res = (res + 2L * s0 % MOD) % MOD; // 2 种：* → 1 或 2
				} else if (sc[i - 1] == '*' && sc[i] >= '7' && sc[i] <= '9') {
					res = (res + s0) % MOD; // 1 种：* 只能是 1
				} else if (sc[i - 1] == '1' && sc[i] >= '0' && sc[i] <= '9') {
					res = (res + s0) % MOD; // 1 种：10-19
				} else if (sc[i - 1] == '2' && sc[i] >= '0' && sc[i] <= '6') {
					res = (res + s0) % MOD; // 1 种：20-26
				}
			}
			// 滚动
			s0 = s1;
			s1 = (int) res;
		}
		return s1;
	}

	// time O(n), space O(n)
	public int numDecodingsDPWithArray(String s) {
		// 把记忆化搜索翻译成 DP
		char[] sc = s.toCharArray();
		int n = s.length();
		int[] dp = new int[n + 1];
		dp[0] = 1; // 空串有 1 种解码方式
		for (int i = 0; i < n; i++) {
			long res = 0;
			if (sc[i] >= '1' && sc[i] <= '9') {
				res = (res + dp[i]) % MOD;
			} else if (sc[i] == '*') {
				res = (res + 9L * dp[i] % MOD) % MOD;
			}
			if (i > 0) {
				if (sc[i - 1] == '*' && sc[i] == '*') {
					res = (res + 15L * dp[i - 1] % MOD) % MOD;
				} else if (sc[i - 1] == '1' && sc[i] == '*') {
					res = (res + 9L * dp[i - 1] % MOD) % MOD;
				} else if (sc[i - 1] == '2' && sc[i] == '*') {
					res = (res + 6L * dp[i - 1] % MOD) % MOD;
				} else if (sc[i - 1] == '*' && sc[i] >= '0' && sc[i] <= '6') {
					res = (res + 2L * dp[i - 1] % MOD) % MOD;
				} else if (sc[i - 1] == '*' && sc[i] >= '7' && sc[i] <= '9') {
					res = (res + dp[i - 1]) % MOD;
				} else if (sc[i - 1] == '1' && sc[i] >= '0' && sc[i] <= '9') {
					res = (res + dp[i - 1]) % MOD;
				} else if (sc[i - 1] == '2' && sc[i] >= '0' && sc[i] <= '6') {
					res = (res + dp[i - 1]) % MOD;
				}
			}
			dp[i + 1] = (int) res;
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
		long res = 0;
		// 长度 1：s[i] 单独解码，递归 s[0..i-1]
		if (s[i] >= '1' && s[i] <= '9') {
			res = (res + dfs(i - 1, s, memo)) % MOD;
		} else if (s[i] == '*') {
			res = (res + 9L * dfs(i - 1, s, memo) % MOD) % MOD; // * → 1-9，9 种
		}
		// 长度 2：s[i-1], s[i] 合并解码，递归 s[0..i-2]
		if (i > 0) {
			if (s[i - 1] == '*' && s[i] == '*') {
				res = (res + 15L * dfs(i - 2, s, memo) % MOD) % MOD; // 15 种
			} else if (s[i - 1] == '1' && s[i] == '*') {
				res = (res + 9L * dfs(i - 2, s, memo) % MOD) % MOD; // 9 种
			} else if (s[i - 1] == '2' && s[i] == '*') {
				res = (res + 6L * dfs(i - 2, s, memo) % MOD) % MOD; // 6 种
			} else if (s[i - 1] == '*' && s[i] >= '0' && s[i] <= '6') {
				res = (res + 2L * dfs(i - 2, s, memo) % MOD) % MOD; // 2 种
			} else if (s[i - 1] == '*' && s[i] >= '7' && s[i] <= '9') {
				res = (res + dfs(i - 2, s, memo)) % MOD; // 1 种
			} else if (s[i - 1] == '1' && s[i] >= '0' && s[i] <= '9') {
				res = (res + dfs(i - 2, s, memo)) % MOD; // 1 种
			} else if (s[i - 1] == '2' && s[i] >= '0' && s[i] <= '6') {
				res = (res + dfs(i - 2, s, memo)) % MOD; // 1 种
			}
		}
		return memo[i] = (int) res;
	}
}
