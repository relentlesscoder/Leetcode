package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/27/19.
 * #576 https://leetcode.com/problems/out-of-boundary-paths/
 */
public class OutOfBoundaryPaths {
	private long[][][] memo;
	private int MOD = 1_000_000_007;
	private int[][] dir = new int[][]{
		{1, -1, 0, 0}, {0, 0, 1, -1}
	};

	public int findPaths(int m, int n, int N, int i, int j) {
		memo = new long[m][n][N+1];
		for(int x = 0; x < m; x++){
			for(int y = 0; y < n; y++){
				Arrays.fill(memo[x][y], -1);
			}
		}
		return (int) (dfs(m, n, N, i, j) % MOD);
	}

	private long dfs(int m, int n, int N, int i, int j){
		if(N >= 0 && outbound(m, n, i, j)){
			return 1;
		}else if(N < 0){
			return 0;
		}
		if(memo[i][j][N] != -1){
			return memo[i][j][N];
		}
		memo[i][j][N] = 0;
		for(int k = 0; k < 4; k++){
			int x = i + dir[0][k];
			int y = j + dir[1][k];
			memo[i][j][N] = (memo[i][j][N] + dfs(m, n, N - 1, x, y) % MOD) % MOD;
		}
		return memo[i][j][N];
	}

	private boolean outbound(int m, int n, int i, int j){
		return i < 0 || j < 0 || i >= m || j >= n;
	}
}
