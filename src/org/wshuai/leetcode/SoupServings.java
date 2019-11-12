package org.wshuai.leetcode;

/**
 * Created by Wei on 11/12/19.
 * #808 https://leetcode.com/problems/soup-servings/
 */
public class SoupServings {
	private int[][] serve;

	public double soupServings(int N) {
		if(N >= 4800){
			return 1.0;
		}

		serve = new int[][]{
				{100, 75, 50, 25},
				{0, 25, 50, 75}
		};
		return helper(N, N, new Double[N + 1][N + 1]);
	}

	private double helper(int a, int b, Double[][] dp){
		if(a <= 0 && b <= 0){
			return 0.5;
		}
		if(a <= 0){
			return 1.0;
		}
		if(b <= 0){
			return 0.0;
		}
		if(dp[a][b] != null){
			return dp[a][b];
		}
		dp[a][b] = 0.0;
		for(int i = 0; i < 4; i++){
			dp[a][b] += helper(a - serve[0][i], b - serve[1][i], dp);
		}
		dp[a][b] *= 0.25;
		return dp[a][b];
	}
}
