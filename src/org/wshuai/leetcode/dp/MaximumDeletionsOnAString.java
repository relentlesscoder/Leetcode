package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 03/29/2026.
 * #2430
 * https://leetcode.com/problems/maximum-deletions-on-a-string/
 */
public class MaximumDeletionsOnAString {

	// 两种解法的共同核心思路:
	// 每次操作: 如果 s[i..i+j-1] == s[i+j..i+2j-1] (前 j 个字符 == 后 j 个字符), 可以删掉前 j 个
	// 求最多能执行多少次操作
	//
	// 预处理 LCP (Longest Common Prefix) 数组:
	// lcp[i][j] = 从位置 i 开始和从位置 j 开始的子串的最长公共前缀长度
	// 用 lcp[i][i+j] >= j 判断 s[i..i+j-1] == s[i+j..i+2j-1] (O(1) 查询)
	// 注意用 >= 而不是 ==: lcp 是"最长"公共前缀, 可能比 j 更长, 我们只需前 j 个字符匹配

	// time O(n^2), space O(n)
	// 单哈希: MOD 不能用 2^61-1, 因为 hash[i] * pow[j] 会溢出 long
	// (2^61)^2 = 2^122 >> long 上限 2^63. 用 1e9+7 则乘积 ≈ 10^18, 不溢出
	private static final long MOD1 = (long) 1e9 + 7;
	private static final long BASE1 = 131L;

	public int deleteStringRollingHash(String s) {
		int n = s.length();
		char[] sc = s.toCharArray();
		long[] hash = new long[n + 1], pow = new long[n + 1];
		pow[0] = 1L;
		for (int i = 0; i < n; i++) {
			hash[i + 1] = (hash[i] * BASE1 + sc[i]) % MOD1;
			pow[i + 1] = pow[i] * BASE1 % MOD1;
		}
		int[] dp = new int[n];
		for (int i = n - 1; i >= 0; i--) {
			int res = 0;
			for (int j = 1; i + 2 * j <= n; j++) {
				if (getHash(i, i + j - 1, hash, pow, MOD1)
						== getHash(i + j, i + 2 * j - 1, hash, pow, MOD1)) {
					res = Math.max(res, dp[i + j]);
				}
			}
			dp[i] = res + 1;
		}
		return dp[0];
	}

	// time O(n^2), space O(n)
	// 双哈希: 用两组不同的 BASE/MOD, 冲突概率从 1/10^9 降到 1/10^18
	// 两个哈希值都相等才认为子串相同, 几乎不可能冲突
	private static final long MOD2 = (long) 1e9 + 9;
	private static final long BASE2 = 137L;

	public int deleteStringDoubleHash(String s) {
		int n = s.length();
		char[] sc = s.toCharArray();
		// 两组前缀哈希和幂次
		long[] hash1 = new long[n + 1], pow1 = new long[n + 1];
		long[] hash2 = new long[n + 1], pow2 = new long[n + 1];
		pow1[0] = pow2[0] = 1L;
		for (int i = 0; i < n; i++) {
			hash1[i + 1] = (hash1[i] * BASE1 + sc[i]) % MOD1;
			pow1[i + 1] = pow1[i] * BASE1 % MOD1;
			hash2[i + 1] = (hash2[i] * BASE2 + sc[i]) % MOD2;
			pow2[i + 1] = pow2[i] * BASE2 % MOD2;
		}
		int[] dp = new int[n];
		for (int i = n - 1; i >= 0; i--) {
			int res = 0;
			for (int j = 1; i + 2 * j <= n; j++) {
				// 两个哈希都相等才认为子串相同
				if (getHash(i, i + j - 1, hash1, pow1, MOD1)
						== getHash(i + j, i + 2 * j - 1, hash1, pow1, MOD1)
						&& getHash(i, i + j - 1, hash2, pow2, MOD2)
						== getHash(i + j, i + 2 * j - 1, hash2, pow2, MOD2)) {
					res = Math.max(res, dp[i + j]);
				}
			}
			dp[i] = res + 1;
		}
		return dp[0];
	}

	// 获取 s[i..j] 的哈希值
	private long getHash(int i, int j, long[] hash, long[] pow, long mod) {
		return (hash[j + 1] - hash[i] * pow[j - i + 1] % mod + mod) % mod;
	}

	// time O(n^2), space O(n^2)
	public int deleteStringDP(String s) {
		// dp[i] = 从位置 i 开始的子串 s[i..n-1] 最多能执行多少次删除操作
		int n = s.length();
		char[] sc = s.toCharArray();
		// O(n^2) 预计算 LCP 数组, 从右下角往左上角填表
		// lcp[i][j] 依赖 lcp[i+1][j+1] (右下方), 所以 i 从大到小
		int[][] lcp = new int[n + 1][n + 1];
		for (int i = n - 1; i >= 0; i--) {
			for (int j = n - 1; j > i; j--) {
				if (sc[i] == sc[j]) {
					lcp[i][j] = lcp[i + 1][j + 1] + 1;
				}
			}
		}
		// dp[i] = s[i..n-1] 最多执行多少次操作
		// 从右往左填, 因为 dp[i] 依赖 dp[i+j] (右边的值)
		int[] dp = new int[n];
		for (int i = n - 1; i >= 0; i--) {
			int res = 0;
			// 枚举删除长度 j: 删掉前 j 个字符 s[i..i+j-1]
			// 条件: s[i..i+j-1] == s[i+j..i+2j-1], 即 lcp[i][i+j] >= j
			// i + j * 2 <= n 保证后半段不越界
			for (int j = 1; i + j * 2 <= n; j++) {
				if (lcp[i][i + j] >= j) {
					// 删掉前 j 个, 剩下 s[i+j..n-1], 继续操作 dp[i+j] 次
					res = Math.max(res, dp[i + j]);
				}
			}
			// +1: 最后剩余的字符串整体删掉算一次操作
			dp[i] = res + 1;
		}
		return dp[0];
	}

	// time O(n^2), space O(n^2)
	public int deleteStringDFSWithMemorization(String s) {
		// dfs(i) = s[i..n-1] 最多执行多少次删除操作
		int n = s.length();
		char[] sc = s.toCharArray();
		// 预计算 LCP 数组
		int[][] lcp = new int[n + 1][n + 1];
		for (int i = n - 1; i >= 0; i--) {
			for (int j = n - 1; j > i; j--) {
				if (sc[i] == sc[j]) {
					lcp[i][j] = lcp[i + 1][j + 1] + 1;
				}
			}
		}
		int[] memo = new int[n];
		Arrays.fill(memo, -1);
		return dfs(0, sc, lcp, memo);
	}

	// dfs(i) = s[i..n-1] 最多执行多少次删除操作
	private int dfs(int i, char[] sc, int[][] lcp, int[] memo) {
		int n = sc.length;
		if (i == n) {
			return 0; // 字符串已空
		}
		if (memo[i] != -1) {
			return memo[i];
		}
		int res = 0;
		// 枚举删除长度 j
		for (int j = 1; i + j * 2 <= n; j++) {
			// s[i..i+j-1] == s[i+j..i+2j-1] → 可以删掉前 j 个
			if (lcp[i][i + j] >= j) {
				res = Math.max(res, dfs(i + j, sc, lcp, memo));
			}
		}
		// +1: 剩余字符串整体删掉算一次
		return memo[i] = res + 1;
	}
}
