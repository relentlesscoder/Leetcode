package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/27/2019.
 * #0576 https://leetcode.com/problems/out-of-boundary-paths/
 */
public class OutOfBoundaryPaths {
	private static final int mod = 1_000_000_007;
	private static int[] dirs = new int[]{0, -1, 0, 1, 0};

	// time O(N*m*n*4), space O(N*m*n)
	public int findPaths(int m, int n, int N, int i, int j) {
		if (N <= 0) {
			return 0;
		}
		int res = 0;
		int[][][] dp = new int[N + 1][m][n];
		dp[0][i][j] = 1;
		for (int s = 1; s <= N; s++) {
			for (int r = 0; r < m; r++) {
				for (int c = 0; c < n; c++) {
					for (int k = 0; k < 4; k++) {
						int x = r + dirs[k];
						int y = c + dirs[k + 1];
						if (x < 0 || x >= m || y < 0 || y >= n) {
							res = (res + dp[s - 1][r][c]) % mod;
						} else {
							dp[s][x][y] = (dp[s][x][y] + dp[s - 1][r][c]) % mod;
						}
					}
				}
			}
		}
		return res;
	}

	// time O(4^N), space O(m*n*N)
	public int findPathsMemorization(int m, int n, int N, int i, int j) {
		if (N <= 0) {
			return 0;
		}
		int[][][] dp = new int[m][n][N];
		for (int x = 0; x < m; x++) {
			for (int y = 0; y < n; y++) {
				Arrays.fill(dp[x][y], -1);
			}
		}
		return dfs(m, n, 0, N, i, j, dp);
	}

	public int dfs(int m, int n, int c, int N, int i, int j, int[][][] dp) {
		if (i < 0 || i >= m || j < 0 || j >= n) {
			return 1;
		}
		if (c == N) {
			return 0;
		}
		if (dp[i][j][c] != -1) {
			return dp[i][j][c];
		}
		int res = 0;
		for (int k = 0; k < 4; k++) {
			res = (res + dfs(m, n, c + 1, N, i + dirs[k], j + dirs[k + 1], dp)) % mod;
		}
		dp[i][j][c] = res;
		return res;
	}
}
