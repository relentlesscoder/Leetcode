package org.wshuai.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 03/11/2026.
 * #3610
 * https://leetcode.com/problems/minimum-number-of-primes-to-sum-to-target/
 */
public class MinimumNumberOfPrimesToSumToTarget {

	// time O(), space O()
	private static final int MAX = 1_000;
	private static final boolean[] NON_PRIME = new boolean[MAX + 1];

	static {
		// 埃氏筛预处理出前 MAX 个数中哪些是质数，哪些不是质数
		for (int i = 2; i <= MAX; i++) {
			if (!NON_PRIME[i]) {
				for (int j = i; j <= MAX / i; j++) {
					NON_PRIME[j * i] = true;
				}
			}
		}
	}

	private static List<Integer> getPrimes(int m) {
		List<Integer> primes = new ArrayList<>();
		// 预处理出前 m 个质数
		for (int i = 2, j = 0; i <= MAX && j < m; i++) {
			if (!NON_PRIME[i]) {
				j++;
				primes.add(i);
			}
		}
		return primes;
	}

	// time O(n * m), space O(m)
	public int minNumberOfPrimes(int n, int m) {
		// 空间优化版 DP
		List<Integer> primes = getPrimes(m);
		int k = primes.size();
		int[] dp = new int[n + 1];
		Arrays.fill(dp, MAX);
		dp[0] = 0;
		for (int i = 0; i < k; i++) {
			for (int j = 1; j <= n; j++) {
				if (primes.get(i) <= j) {
					dp[j] = Math.min(dp[j], 1 + dp[j - primes.get(i)]);
				}
			}
		}
		return dp[n] >= MAX ? -1 : dp[n];
	}

	// time O(n * m), space O(n * m)
	public int minNumberOfPrimesDPWithGrid(int n, int m) {
		// 把记忆化搜索翻译成 DP
		List<Integer> primes = getPrimes(m);
		int k = primes.size();
		int[][] dp = new int[k + 1][n + 1];
		Arrays.fill(dp[0], MAX);
		dp[0][0] = 0;
		for (int i = 0; i < k; i++) {
			for (int j = 1; j <= n; j++) {
				if (primes.get(i) > j) { // 当前质数比剩余的 n 还大，选不了当前质数了，只能跳过它
					dp[i + 1][j] = dp[i][j];
				} else { // 当前质数不比剩余的 n 大，既可以选当前质数，也可以跳过当前质数，取两者的较小值
					dp[i + 1][j] = Math.min(dp[i][j], 1 + dp[i + 1][j - primes.get(i)]);
				}
			}
		}
		return dp[k][n] >= MAX ? -1 : dp[k][n];
	}

	// time O(n * m), space O(n * m)
	public int minNumberOfPrimesDFSWithMemorization(int n, int m) {
		// 记忆化搜索
		List<Integer> primes = getPrimes(m);
		int k = primes.size();
		int[][] memo = new int[k][n + 1];
		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}
		int res = dfs(k - 1, n, primes, memo);
		return res >= MAX ? -1 : res;
	}

	private int dfs(int i, int n, List<Integer> primes, int[][] memo) {
		if (n == 0) { // 还剩 0 了，说明之前选的质数加起来正好等于目标数 n
			return 0;
		}
		if (i == -1) { // 没有质数可选了，但还剩 n > 0，说明之前选的质数加起来小于目标数 n，无法凑出 n 来
			return MAX;
		}
		if (memo[i][n] != -1) {
			return memo[i][n];
		}
		if (primes.get(i) > n) { // 当前质数比剩余的 n 还大，选不了当前质数了，只能跳过它
			return memo[i][n] = dfs(i - 1, n, primes, memo);
		} else { // 当前质数不比剩余的 n 大，既可以选当前质数，也可以跳过当前质数，取两者的较小值
			return memo[i][n] = Math.min(dfs(i - 1, n, primes, memo), 1 + dfs(i, n - primes.get(i), primes, memo));
		}
	}
}
