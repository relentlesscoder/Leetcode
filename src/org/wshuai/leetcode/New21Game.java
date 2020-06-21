package org.wshuai.leetcode;

/**
 * Created by Wei on 11/11/2019.
 * #0837 https://leetcode.com/problems/new-21-game/
 */
public class New21Game {

	// O(K + W)
	// https://leetcode.com/problems/new-21-game/discuss/132358/Java-O(K-%2B-W)-DP-solution-with-explanation
	public double new21Game(int N, int K, int W) {
		/*
		Then I realize that the transition equation dp[i] = (dp[i - W] + dp[i - W + 1] + ... + dp[i - 1]) / W
		could be simplified to dp[i] = (sum[i - 1] - sum[i - W - 1]) / W.
		Furthermore, if we use dp[i] to directly represent the sum[i], we can get dp[i] = dp[i - 1] + (dp[i - 1]
		 - dp[i - W - 1]) / W. This equation takes us to the final O(K + W) solution. Just take care with the
		 beginning and the end of the array.*/
		if(K == 0){
			return 1;
		}
		int max = K + W - 1;
		double[] dp = new double[max + 1];
		dp[0] = 1;
		for(int i = 1; i <= max; i++){
			dp[i] = dp[i - 1];
			if(i <= W){
				dp[i] += dp[i - 1] / W;
			}else{
				dp[i] += (dp[i - 1] - dp[i - W - 1]) / W;
			}
			// When i > K, game stops. Actually when i > K, dp[i] = dp[i - 1] + (dp[K - 1] - dp[i - W - 1]) / W.
			// i > K part won't contribute.
			if(i > K){
				dp[i] -= (dp[i - 1] - dp[K - 1]) / W;
			}
		}
		return dp[N] - dp[K - 1];
	}

	// time O((K + W)*W) - TLE
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

	// TLE
	public double new21GameDFS(int N, int K, int W) {
		return dfs(0, N, K, W, new Double[K]);
	}

	private double dfs(int P, int N, int K, int W, Double[] dp){
		if(P >= K){
			return P <= N ? 1 : 0;
		}
		if(dp[P] != null){
			return dp[P];
		}
		double res = 0.0;
		for(int i = 1; i <= W; i++){
			res += 1.0 / W * dfs(P + i, N, K, W, dp);
		}
		dp[P] = res;
		return res;
	}
}
