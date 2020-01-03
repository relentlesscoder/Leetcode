package org.wshuai.leetcode;

/**
 * Created by Wei on 12/4/19.
 * #887 https://leetcode.com/problems/super-egg-drop/
 */
public class SuperEggDrop {
	// https://leetcode.com/problems/super-egg-drop/discuss/159508/easy-to-understand
	public int superEggDrop(int K, int N) {
		int[] dp = new int[K + 1];
		int step = 0;
		for(; dp[K] < N; step++){
			for(int i = K; i > 0; i--){
				dp[i] = 1 + dp[i] + dp[i - 1];
			}
		}
		return step;
	}
}
