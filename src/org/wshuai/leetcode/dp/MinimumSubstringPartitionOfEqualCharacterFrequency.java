package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 03/24/2026.
 * #3144
 * https://leetcode.com/problems/minimum-substring-partition-of-equal-character-frequency/
 */
public class MinimumSubstringPartitionOfEqualCharacterFrequency {

	// 两种解法的共同核心思路:
	// 划分型 DP: 将字符串划分成最少的子串, 每个子串中所有出现的字符频率相等
	// 枚举最后一段的起点 j, 最后一段为 s[j..i]
	// 判断 s[j..i] 是否 "balanced" (所有出现的字符频率相同):
	// cnt = 不同字符的种类数, max = 出现最多的字符的频率
	// 如果 max * cnt == 子串长度, 说明每种字符都恰好出现了 max 次 → balanced
	// 例如 "aabb": cnt=2, max=2, 2*2=4=长度 → balanced
	// 例如 "aab": cnt=2, max=2, 2*2=4≠3 → not balanced

	// time O(n^2), space O(n)
	public int minimumSubstringsInPartitionDP(String s) {
		// dp[i+1] = s[0..i] 的最少划分段数
		int n = s.length();
		int[] dp = new int[n + 1];
		for (int i = 0; i < n; i++) {
			int res = Integer.MAX_VALUE, cnt = 0, max = 0;
			// freq[c] = 字符 c 在 s[j..i] 中出现的次数
			int[] freq = new int[26];
			// 从 i 往左扫, 枚举最后一段的起点 j
			for (int j = i; j >= 0; j--) {
				int idx = s.charAt(j) - 'a';
				if (freq[idx]++ == 0) {
					cnt++; // 新出现的字符, 种类数 +1
				}
				max = Math.max(max, freq[idx]); // 更新最高频率
				// 判断 s[j..i] 是否 balanced: 每种字符都出现 max 次
				if (max * cnt == i - j + 1) {
					res = Math.min(res, 1 + dp[j]);
				}
			}
			dp[i + 1] = res;
		}
		return dp[n];
	}

	// time O(n^2), space O(n)
	public int minimumSubstringsInPartitionDFSWithMemorization(String s) {
		// dfs(i) = s[0..i] 的最少划分段数
		int n = s.length();
		int[] memo = new int[n];
		Arrays.fill(memo, -1);
		return dfs(n - 1, s.toCharArray(), memo);
	}

	// dfs(i) = s[0..i] 的最少划分段数
	private int dfs(int i, char[] s, int[] memo) {
		if (i == -1) {
			return 0; // 所有字符都处理完了
		}
		if (memo[i] != -1) {
			return memo[i];
		}
		int res = Integer.MAX_VALUE, cnt = 0, max = 0;
		int[] freq = new int[26];
		// 从 i 往左扫, 枚举最后一段的起点 j
		for (int j = i; j >= 0; j--) {
			if (freq[s[j] - 'a']++ == 0) {
				cnt++; // 新出现的字符
			}
			max = Math.max(max, freq[s[j] - 'a']); // 最高频率
			// s[j..i] 是 balanced → 递归处理 s[0..j-1]
			if (max * cnt == i - j + 1) {
				res = Math.min(res, 1 + dfs(j - 1, s, memo));
			}
		}
		return memo[i] = res;
	}
}
