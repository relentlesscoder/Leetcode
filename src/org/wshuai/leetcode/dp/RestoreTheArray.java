package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 04/20/2020.
 * #1416 https://leetcode.com/problems/restore-the-array/
 */
public class RestoreTheArray {

	private static final int MOD = (int) 1e9 + 7;

	// 两种解法的共同核心思路:
	// 划分型 DP: 将数字字符串划分成若干段, 每段代表一个 [1, k] 范围内的正整数 (不能有前导零)
	// 枚举最后一段的起点 j, 最后一段为 s[j..i], 从右往左逐位构建数值
	// 与 #0091 Decode Ways 类似, 但范围从 1-26 扩展到 1-k
	// m = k 的位数, 用于限制内循环的最大长度 (超过 m 位一定 > k)

	// time O(n * m), space O(n)
	public int numberOfArraysDP(String s, int k) {
		// dp[i+1] = s[0..i] 的合法划分方案数
		int n = s.length(), m = Integer.toString(k).length();
		int[] dp = new int[n + 1];
		dp[0] = 1; // 空串有 1 种方案
		for (int i = 0; i < n; i++) {
			long res = 0, num = 0, t = 1; // t = 当前位的权重 (1, 10, 100, ...)
			// 从 i 往左扫, 枚举最后一段的起点 j, 最远扫 m 位
			for (int j = i; j >= Math.max(i - m + 1, 0); j--) {
				if (s.charAt(j) == '0') {
					// 前导零: 跳过, 但权重仍需乘 10
					t *= 10;
					continue;
				}
				// 从右往左逐位构建 s[j..i] 的数值
				int d = s.charAt(j) - '0';
				num += d * t;
				if (num > k) {
					break; // 超过 k, 更长的段也一定超过, 直接退出
				}
				// s[j..i] 是合法的 [1, k] 正整数, 累加 dp[j]
				res = (res + dp[j]) % MOD;
				t *= 10;
			}
			dp[i + 1] = (int) res;
		}
		return dp[n];
	}

	// time O(n * m), space O(n)
	public int numberOfArraysDFSWithMemorization(String s, int k) {
		// dfs(i) = s[0..i] 的合法划分方案数
		int n = s.length(), m = Integer.toString(k).length();
		int[] memo = new int[n];
		Arrays.fill(memo, -1);
		return dfs(n - 1, s.toCharArray(), k, m, memo);
	}

	// dfs(i) = s[0..i] 的合法划分方案数
	private int dfs(int i, char[] s, int k, int m, int[] memo) {
		if (i == -1) {
			return 1; // 所有字符都处理完了
		}
		if (memo[i] != -1) {
			return memo[i];
		}
		long res = 0, num = 0, t = 1;
		// 从 i 往左扫, 枚举最后一段起点 j
		for (int j = i; j >= Math.max(i - m + 1, 0); j--) {
			if (s[j] == '0') {
				t *= 10; // 前导零, 跳过但权重仍需更新
				continue;
			}
			int d = s[j] - '0';
			num += d * t; // 逐位构建数值
			if (num > k) {
				break; // 超过 k, 退出
			}
			// s[j..i] 合法, 递归处理 s[0..j-1]
			res = (res + dfs(j - 1, s, k, m, memo)) % MOD;
			t *= 10;
		}
		return memo[i] = (int) res;
	}
}
