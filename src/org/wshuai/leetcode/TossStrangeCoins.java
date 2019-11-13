package org.wshuai.leetcode;

/**
 * Created by Wei on 11/12/2019.
 * #1230 https://leetcode.com/problems/toss-strange-coins/
 */
public class TossStrangeCoins {

	// 7 ms
	public double probabilityOfHeads(double[] prob, int target) {
		int N = prob.length;
		double[] dp = new double[target + 1];
		dp[0] = 1.0;
		for(int i = 1; i <= N; i++){
			double prev = dp[0];
			dp[0] = dp[0] * (1 - prob[i - 1]);
			for(int j = 1; j <= target; j++){
				double temp = dp[j];
				dp[j] = prev * prob[i - 1]
					+ dp[j] * (1 - prob[i - 1]);
				prev = temp;
			}
		}
		return dp[target];
	}

	// 19 ms
	public double probabilityOfHeadsDP2D(double[] prob, int target) {
		int N = prob.length;
		double[][] dp = new double[N + 1][target + 1];
		dp[0][0] = 1.0;
		for(int i = 1; i <= N; i++){
			dp[i][0] = dp[i - 1][0] * (1 - prob[i - 1]);
		}
		for(int i = 1; i <= N; i++){
			for(int j = 1; j <= target; j++){
				dp[i][j] = dp[i - 1][j - 1] * prob[i - 1]
					+ dp[i - 1][j] * (1 - prob[i - 1]);
			}
		}
		return dp[N][target];
	}

	private Double[][] dp;

	// 85 ms
	public double probabilityOfHeadsDFSWithMemo(double[] prob, int target) {
		int N = prob.length;
		dp = new Double[N + 1][N + 1];
		return dfs(0, prob, target);
	}

	private double dfs(int i, double[] prob, int target){
		if(target < 0){
			return 0.0;
		}
		if(i == prob.length){
			return target == 0 ? 1.0 : 0.0;
		}
		if(dp[i][target] != null){
			return dp[i][target];
		}
		dp[i][target] = 0.0;
		dp[i][target] += prob[i] * dfs(i + 1, prob, target - 1);
		dp[i][target] += (1 - prob[i]) * dfs(i + 1, prob, target);
		return dp[i][target];
	}
}
