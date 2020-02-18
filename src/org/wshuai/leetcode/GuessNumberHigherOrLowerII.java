package org.wshuai.leetcode;

/**
 * Created by Wei on 03/21/2017.
 * #0375 https://leetcode.com/problems/guess-number-higher-or-lower-ii/
 */
public class GuessNumberHigherOrLowerII {
	// time O(n^3)
	public int getMoneyAmount(int n) {
		int[][] dp = new int[n + 1][n + 1];
		for (int l = 1; l <= n; l++) {
			for (int i = 1; i + l <= n; i++) {
				int j = i + l;
				dp[i][j] = Integer.MAX_VALUE;
				for (int k = i; k <= j; k++) {
					int temp = k + Math.max(k - 1 >= i ? dp[i][k - 1] : 0, k + 1 <= j ? dp[k + 1][j] : 0);
					dp[i][j] = Math.min(dp[i][j], temp);
				}
			}
		}
		return dp[1][n];
	}

	// time O(n^3)
	public int getMoneyAmountRecursionWithMemo(int n) {
		int[][] dp = new int[n + 1][n + 1];
		return dfs(dp, 1, n);
	}

	private int dfs(int[][] dp, int s, int e) {
		if (s >= e) {
			return 0;
		}
		if (dp[s][e] != 0) {
			return dp[s][e];
		}
		int res = Integer.MAX_VALUE;
		for (int i = s; i <= e; i++) {
			res = Math.min(res, i + Math.max(dfs(dp, s, i - 1), dfs(dp, i + 1, e)));
		}
		dp[s][e] = res;
		return res;
	}
}
