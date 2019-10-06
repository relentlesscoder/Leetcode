package org.wshuai.algorithm.dynamicProgramming;

public class MinimumCostToReachLastCell {

	public int minimumCost(int[][] M){

		int r = M.length;
		int c = M[0].length;

		for(int i = 1; i < r; i++){
			M[i][0] += M[i - 1][0];
		}

		for(int j = 1; j < c; j++){
			M[0][j] += M[0][j - 1];
		}

		for(int i = 1; i < r; i++){
			for(int j = 1; j < c; j++){
				M[i][j] += Math.min(M[i - 1][j], M[i][j - 1]);
			}
		}

		return M[r - 1][c - 1];
	}
}
