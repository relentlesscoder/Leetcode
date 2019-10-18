package org.wshuai.leetcode;

/**
 * Created by Wei on 10/18/2019.
 * #1049 https://leetcode.com/problems/last-stone-weight-ii/
 */
public class LastStoneWeightII {
	public int lastStoneWeightII(int[] stones) {
		int s2 = 0;
		int sum = 0;
		for(int s: stones){
			sum += s;
		}
		int n = stones.length;
		boolean[][] dp = new boolean[n + 1][sum + 1];
		for(int i = 0; i <= n; i++){
			dp[i][0] = true;
		}
		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= sum / 2; j++){
				if(dp[i - 1][j] || (j >= stones[i - 1] && dp[i - 1][j - stones[i - 1]])){
					dp[i][j] = true;
					s2 = Math.max(s2, j);
				}
			}
		}
		return sum - 2 * s2;
	}
}
