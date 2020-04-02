package org.wshuai.leetcode;

/**
 * Created by Wei on 10/07/2019.
 * #0688 https://leetcode.com/problems/knight-probability-in-chessboard/
 */
public class KnightProbabilityInChessboard {

	private static final int[][] dirs = new int[][]{
			{-1, -2, -2, -1, 1, 2, 2, 1},
			{-2, -1, 1, 2, 2, 1, -1, -2}
	};

	// time O(8^k), space O(n^2*k)
	public double knightProbability(int N, int K, int r, int c) {
		double[][][] dp = new double[N][N][K + 1];
		return dfs(r, c, K, N, dp);
	}

	private double dfs(int r, int c, int k, int n, double[][][] dp){
		if(invalid(r, c, n)){
			return 0;
		}
		if(k == 0){
			return 1;
		}
		if(dp[r][c][k] != 0){
			return dp[r][c][k];
		}
		double res = 0;
		for(int i = 0; i < 8; i++){
			int x = r + dirs[0][i], y = c + dirs[1][i];
			res += 0.125 * dfs(x, y, k - 1, n, dp);
		}
		dp[r][c][k] = res;
		return res;
	}

	private boolean invalid(int r, int c, int n){
		return r < 0 || r >= n || c < 0 || c >= n;
	}
}
