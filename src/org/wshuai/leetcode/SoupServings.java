package org.wshuai.leetcode;

/**
 * Created by Wei on 11/12/2019.
 * #0808 https://leetcode.com/problems/soup-servings/
 */
public class SoupServings {

	// https://leetcode.com/problems/soup-servings/discuss/121711/C%2B%2BJavaPython-When-N-greater-4800-just-return-1
	public double soupServings(int N) {
		// check the link to see why 3500
		if(N >= 3_500){
			return 1.0;
		}
		return dfs(N, N, new Double[N + 1][N + 1]);
	}

	private double dfs(int A, int B, Double[][] dp){
		if(A <= 0 || B <= 0){
			if(A <= 0 && B <= 0){
				return 0.5;
			}
			return A <= 0 ? 1.0 : 0.0;
		}
		if(dp[A][B] != null){
			return dp[A][B];
		}
		dp[A][B] = 0.25 * dfs(A - 100, B, dp) + 0.25 * dfs(A - 75, B - 25, dp)
				+ 0.25 * dfs(A - 50, B - 50, dp) + 0.25 * dfs(A - 25, B - 75, dp);
		return dp[A][B];
	}
}
