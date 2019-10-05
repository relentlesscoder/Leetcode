package org.wshuai.algorithm.dynamicProgramming;

public class LongestCommonSubsequence {

	private int[][] dp;
	private String A;
	private String B;

	public LongestCommonSubsequence(String A, String B) {
		this.A = A;
		this.B = B;
		dp = new int[A.length() + 1][B.length() + 1];
	}

	private int getLCSLength() {
		int r = A.length();
		int c = B.length();

		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				if (A.charAt(i - 1) == B.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		return dp[r][c];
	}
}
