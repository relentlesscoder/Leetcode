package org.wshuai.algorithm.dynamicProgramming;

import java.util.Arrays;

public class LongestPathInMatrix {

	private int[][] move;

	public LongestPathInMatrix(){
		move = new int[2][4];
		move[0] = new int[]{1, -1, 0, 0};
		move[1] = new int[]{0, 0, 1, -1};
	}

	public int longestPath(int[][] M){
		int longest = 0;

		int r = M.length;
		int c = M[0].length;

		int[][] dp = new int[r][c];

		for(int i = 0; i < r; i++){
			Arrays.fill(dp[i], -1);
		}
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				if(dp[i][j] == -1){
					dp[i][j] = findPathStartAt(i, j, M, dp);
				}
				longest = Math.max(dp[i][j], longest);
			}
		}

		return longest;
	}

	private int findPathStartAt(int i, int j, int[][] M, int[][] dp){
		if(dp[i][j] != -1){
			return dp[i][j];
		}
		int res = 1;
		for(int k = 0; k < 4; k++){
			int x = i + move[0][k];
			int y = j + move[1][k];
			if(x >= 0 && y >= 0 && x < M.length && y < M[0].length && M[x][y] - M[i][j] == 1){
				res = Math.max(findPathStartAt(x, y, M, dp) + 1, res);
			}
		}
		dp[i][j] = res;
		return res;
	}
}
