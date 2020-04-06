package org.wshuai.leetcode;

/**
 * Created by Wei on 04/06/2020.
 * #1406 https://leetcode.com/problems/stone-game-iii/
 */
public class StoneGameIII {
	// time O(3^n)
	public String stoneGameIII(int[] stoneValue) {
		int score = dfs(stoneValue, 0, new Integer[stoneValue.length]);
		return score >= 0 ? (score == 0 ? "Tie" : "Alice") : "Bob";
	}

	private int dfs(int[] stoneValue, int start, Integer[] dp){
		if(start == stoneValue.length){
			return 0;
		}
		if(dp[start] != null){
			return dp[start];
		}
		int score = Integer.MIN_VALUE, sum = 0;
		for(int i = start; i < Math.min(start + 3, stoneValue.length); i++){
			sum += stoneValue[i];
			score = Math.max(score, sum - dfs(stoneValue, i + 1, dp));
		}
		dp[start] = score;
		return score;
	}
}
