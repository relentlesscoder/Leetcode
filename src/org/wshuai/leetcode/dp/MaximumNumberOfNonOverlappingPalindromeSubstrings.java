package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 03/24/2026.
 * #2472
 * https://leetcode.com/problems/maximum-number-of-non-overlapping-palindrome-substrings/
 */
public class MaximumNumberOfNonOverlappingPalindromeSubstrings {

	// time O(n * k), space O(n)
	public int maxPalindromesDP(String s, int k) {
		// 核心思路:
		// 枚举每个中心, 向两边扩展找回文
		// 找到第一个长度 >= k 的回文就 break (贪心: 选最短的, 消耗最少字符)
		// dp[i] = s[0..i-1] 中最多能选几个不重叠的长度 >= k 的回文子串
		int n = s.length();
		if (k == 1) {
			return n;
		}
		char[] cs = s.toCharArray();
		int[] dp = new int[n + 1];
		for (int i = 0; i < n; i++) {
			dp[i + 1] = Math.max(dp[i + 1], dp[i]); // 不选以 i 为中心的回文
			// 奇数长度回文: 以 i 为中心
			for (int l = i, r = i; l >= 0 && r < n && cs[l] == cs[r]; l--, r++) {
				if (r - l + 1 >= k) {
					dp[r + 1] = Math.max(dp[r + 1], 1 + dp[l]);
					break; // 贪心: 选最短的 >= k 回文, 不需要更长的
				}
			}
			// 偶数长度回文: 以 i 和 i+1 为中心
			for (int l = i, r = i + 1; l >= 0 && r < n && cs[l] == cs[r]; l--, r++) {
				if (r - l + 1 >= k) {
					dp[r + 1] = Math.max(dp[r + 1], 1 + dp[l]);
					break; // 同上
				}
			}
		}
		return dp[n];
	}

	// time O(n^2), space O(n^2)
	// 预计算 isPalin[][] + DP (与 #0132 相同的预处理方式)
	public int maxPalindromesDPWithIsPalin(String s, int k) {
		int n = s.length();
		if (k == 1) {
			return n;
		}
		// O(n^2) 预计算所有回文子串, 之后每次查询 O(1)
		boolean[][] isPalin = new boolean[n][n];
		for (int i = n - 1; i >= 0; i--) {
			for (int j = i; j < n; j++) {
				isPalin[i][j] = s.charAt(i) == s.charAt(j)
						&& (j - i <= 2 || isPalin[i + 1][j - 1]);
			}
		}
		// dp[i] = s[0..i-1] 中最多能选几个不重叠的长度 >= k 的回文子串
		int[] dp = new int[n + 1];
		for (int i = 0; i < n; i++) {
			dp[i + 1] = dp[i]; // 不选以 i 结尾的回文
			// 贪心: 只检查长度 k 和 k+1, 选最短的 >= k 回文
			for (int len = k; len <= k + 1; len++) {
				int start = i - len + 1;
				if (start >= 0 && isPalin[start][i]) {
					dp[i + 1] = Math.max(dp[i + 1], 1 + dp[start]);
				}
			}
		}
		return dp[n];
	}

	// time O(n), space O(n)
	public int maxPalindromesManacher(String s, int k) {
		// 核心思路:
		// 1. 贪心: 只需要检查长度恰好为 k 和 k+1 的回文 (最短的 >= k 的回文)
		// 选更短的回文消耗更少字符, 给后面留更多机会, 一定不比选更长的差
		// 2. Manacher O(n) 预处理每个中心的最大回文半径
		// 3. O(n) DP, 每个位置 O(1) 判断是否有长度 k 或 k+1 的回文结尾于此
		int n = s.length();
		if (k == 1) {
			return n;
		}
		char[] cs = s.toCharArray();
		// oddR[i] = 以 i 为中心的最长奇数回文的半径 (含中心)
		// 例如 "aba" 中 oddR[1] = 2 (回文 "aba" 半径 2)
		int[] oddR = new int[n];
		// evenR[i] = 以 i 和 i+1 之间为中心的最长偶数回文的半径
		// 例如 "abba" 中 evenR[1] = 2 (回文 "abba" 半径 2)
		int[] evenR = new int[n];
		manacher(cs, n, oddR, evenR);
		// dp[i] = s[0..i-1] 中最多能选几个不重叠的长度 >= k 的回文子串
		int[] dp = new int[n + 1];
		for (int i = 0; i < n; i++) {
			dp[i + 1] = dp[i]; // 不选以 i 结尾的回文
			// 只检查长度 k 和 k+1 (贪心: 最短的奇/偶回文)
			for (int len = k; len <= k + 1; len++) {
				int start = i - len + 1;
				if (start < 0) {
					continue;
				}
				boolean isPalin;
				if (len % 2 == 1) {
					// 奇数长度: 中心在 (start + i) / 2
					int center = (start + i) / 2;
					// 半径需要 >= (len+1)/2 才能覆盖长度 len
					isPalin = oddR[center] >= (len + 1) / 2;
				} else {
					// 偶数长度: 左中心在 (start + i) / 2 = (start + i) / 2
					int center = (start + i - 1) / 2;
					// 半径需要 >= len/2 才能覆盖长度 len
					isPalin = evenR[center] >= len / 2;
				}
				if (isPalin) {
					dp[i + 1] = Math.max(dp[i + 1], 1 + dp[start]);
				}
			}
		}
		return dp[n];
	}

	// Manacher 算法: O(n) 求每个中心的最大回文半径
	// 核心思想: 利用已知的回文信息, 镜像推导新位置的半径, 避免重复比较
	private void manacher(char[] s, int n, int[] oddR, int[] evenR) {
		// 求奇数长度回文半径
		// center = 当前最靠右的回文的中心, right = 该回文的右边界
		for (int i = 0, center = 0, right = 0; i < n; i++) {
			// mirror = i 关于 center 的镜像点
			int r = (i < right) ? Math.min(oddR[2 * center - i], right - i) : 1;
			// 尝试继续扩展
			while (i - r >= 0 && i + r < n && s[i - r] == s[i + r]) {
				r++;
			}
			oddR[i] = r;
			// 如果扩展后超过了 right, 更新 center 和 right
			if (i + r > right) {
				center = i;
				right = i + r;
			}
		}
		// 求偶数长度回文半径 (以 i 和 i+1 之间为中心)
		for (int i = 0, center = 0, right = 0; i < n - 1; i++) {
			int r = (i < right) ? Math.min(evenR[2 * center - i], right - i) : 0;
			// 扩展: 比较 s[i-r] 和 s[i+1+r]
			while (i - r >= 0 && i + 1 + r < n && s[i - r] == s[i + 1 + r]) {
				r++;
			}
			evenR[i] = r;
			if (i + r > right) {
				center = i;
				right = i + r;
			}
		}
	}
}
