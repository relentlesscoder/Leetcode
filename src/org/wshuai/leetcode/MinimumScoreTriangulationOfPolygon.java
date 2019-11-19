package org.wshuai.leetcode;

/**
 * Created by Wei on 11/18/2019.
 * #1039 https://leetcode.com/problems/minimum-score-triangulation-of-polygon/
 */
public class MinimumScoreTriangulationOfPolygon {
	// https://leetcode.com/problems/minimum-score-triangulation-of-polygon/discuss/286753/C%2B%2B-with-picture
	public int minScoreTriangulation(int[] A) {
		int[][] dp = new int[A.length][A.length];

		for (int i = A.length - 1; i >= 0; --i) {
			for (int j = i+2; j < A.length; ++j) {
				dp[i][j] = Integer.MAX_VALUE;
				for (int k = i + 1; k < j; ++k) {
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[k] * A[j]);
				}
			}
		}

		return dp[0][A.length - 1];
	}

	public int minScoreTriangulationMemo(int[] A) {
		return dfs(A, 0, A.length - 1, new Integer[A.length][A.length]);
	}

	private int dfs(int[] A, int i, int j, Integer[][] memo){
		if(j < i + 2){
			return 0;
		}
		if(memo[i][j] != null){
			return memo[i][j];
		}
		int res = Integer.MAX_VALUE;
		for(int k = i + 1; k < j; k++){
			res = Math.min(res, dfs(A, i, k, memo) + dfs(A, k, j, memo) + A[i] * A[k] * A[j]);
		}
		memo[i][j] = res;
		return memo[i][j];
	}
}
