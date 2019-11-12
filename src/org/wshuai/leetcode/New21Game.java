package org.wshuai.leetcode;

/**
 * Created by Wei on 11/11/2019.
 * #837 https://leetcode.com/problems/new-21-game/
 */
public class New21Game {

	// O(n)
	// https://leetcode.com/problems/new-21-game/discuss/132358/Java-O(K-%2B-W)-DP-solution-with-explanation
	public double new21Game(int N, int K, int W) {
		if (K == 0) {
			return 1;
		}
		int max = K + W - 1;
		double[] dp = new double[max + 1];
		dp[0] = 1;
		for (int i = 1; i <= max; i++) {
			dp[i] = dp[i - 1];
			if (i <= W) {
				dp[i] += dp[i - 1] / W;
			} else {
				dp[i] += (dp[i - 1] - dp[i - W - 1]) / W;
			}
			if (i > K) {
				dp[i] -= (dp[i - 1] - dp[K - 1]) / W;
			}
		}
		return dp[N] - dp[K - 1];
	}

	// TLE but easy to understand
	// https://leetcode.com/problems/new-21-game/discuss/228406/Logical-Thinking
	public double new21GameDP(int N, int K, int W) {
		if(K == 0){
			return 1;
		}

		int max = K + W - 1;
		double[] dp = new double[max + 1];
		dp[0] = 1;
		for(int i = 1; i <= max; i++){
			for(int w = 1; w <= W; w++){
				if(i - w >= 0 && i - w < K){
					dp[i] += dp[i - w] * 1 / W;
				}
			}
		}

		double res = 0;
		for(int i = K; i <= N; i++){
			res += dp[i];
		}
		return res;
	}
}
