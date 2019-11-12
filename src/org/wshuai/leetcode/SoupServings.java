package org.wshuai.leetcode;

import org.junit.Test;

/**
 * Created by Wei on 11/12/19.
 * #808 https://leetcode.com/problems/soup-servings/
 */
public class SoupServings {

	public double soupServings(int N) {
		if(N >= 3500){
			return 1.0;
		}
		Double[][] dp = new Double[N + 1][N + 1];
		return servingSoup(0, 0, N, dp);
	}

	private double servingSoup(int a, int b, int N, Double[][] dp){
		if(b >= N && a < N){
			return 0.0;
		}
		if(b >= N && a >= N){
			return 0.5;
		}
		if(a >= N){
			return 1.0;
		}
		int x = a >= N ? N : a;
		int y = b >= N ? N : b;
		if(dp[x][y] != null){
			return dp[x][y];
		}
		double res = 0.0;
		res += servingSoup(a + 100, b, N, dp);
		res += servingSoup(a + 75, b + 25, N, dp);
		res += servingSoup(a + 50, b + 50, N, dp);
		res += servingSoup(a + 25, b + 75, N, dp);
		dp[x][y] = res * 0.25;
		return dp[x][y];
	}

	private int[][] serve;

	public double soupServingsTopDown(int N) {
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
