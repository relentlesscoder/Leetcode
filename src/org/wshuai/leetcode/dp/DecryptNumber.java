package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 03/24/2026.
 * LCR 165
 * https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/
 */
public class DecryptNumber {

	// 三种解法的共同核心思路：
	// 与 #0091 Decode Ways 几乎相同的划分型 DP
	// 数字 0-25 对应字母 a-z，将数字串划分成合法编码段
	// 每个位置选择长度 1 (单个数字 0-9) 或长度 2 (两位数 10-25)
	//
	// 与 #0091 的区别：
	// #0091：1-26 对应 A-Z，'0' 不能单独解码
	// 本题：0-25 对应 a-z，'0' 可以单独解码 (对应 'a')
	// 所以长度 1 始终有 1 种方案 (不需要判断 != '0')
	// 长度 2 条件：s[i-1]s[i] 组成 10-25 (而非 10-26)

	// time O(n), space O(1)
	public int crackNumberDP(int ciphertext) {
		// 空间优化版 DP：只保留前两轮的状态，滚动更新
		char[] s = Integer.toString(ciphertext).toCharArray();
		int n = s.length, p0 = 0, p1 = 1;
		// p0 = dp[i-1], p1 = dp[i]
		for (int i = 0; i < n; i++) {
			// 长度 1：s[i] 单独解码，始终有 1 种方案 (0-9 都合法)
			int p = p1;
			// 长度 2：s[i-1]s[i] 合并解码 (10-25 对应 k-z)
			// 条件：s[i-1] == '1'(10-19) 或 s[i-1] == '2' 且 s[i] <= '5' (20-25)
			if (i > 0 && (s[i - 1] == '1' || (s[i - 1] == '2' && s[i] <= '5'))) {
				p += p0;
			}
			// 滚动
			p0 = p1;
			p1 = p;
		}
		return p1;
	}

	// time O(n), space O(n)
	public int crackNumberDPWithArray(int ciphertext) {
		// 把记忆化搜索翻译成 DP
		char[] s = Integer.toString(ciphertext).toCharArray();
		int n = s.length;
		int[] dp = new int[n + 1];
		dp[0] = 1; // 空串有 1 种解码方式
		for (int i = 0; i < n; i++) {
			// 长度 1：s[i] 单独解码
			dp[i + 1] = dp[i];
			// 长度 2：s[i-1]s[i] 合并解码(10-25)
			if (i > 0 && (s[i - 1] == '1' || (s[i - 1] == '2' && s[i] <= '5'))) {
				dp[i + 1] += dp[i - 1];
			}
		}
		return dp[n];
	}

	// time O(n), space O(n)
	public int crackNumberDFSWithMemorization(int ciphertext) {
		// dfs(i) = s[0..i] 的解码方式数
		char[] s = Integer.toString(ciphertext).toCharArray();
		int n = s.length;
		int[] memo = new int[n];
		Arrays.fill(memo, -1);
		return dfs(n - 1, s, memo);
	}

	// dfs(i) = s[0..i] 的解码方式数
	private int dfs(int i, char[] s, int[] memo) {
		if (i == -1) {
			return 1; // 所有字符都解码完了，算 1 种方式
		}
		if (memo[i] != -1) {
			return memo[i];
		}
		// 长度 1：s[i] 单独解码，递归 s[0..i-1]
		int res = dfs(i - 1, s, memo);
		// 长度 2：s[i-1]s[i] 合并解码(10-25)，递归 s[0..i-2]
		if (i > 0 && (s[i - 1] == '1' || (s[i - 1] == '2' && s[i] <= '5'))) {
			res += dfs(i - 2, s, memo);
		}
		return memo[i] = res;
	}
}
