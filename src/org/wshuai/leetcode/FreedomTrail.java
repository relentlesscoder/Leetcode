package org.wshuai.leetcode;

/**
 * Created by Wei on 11/21/2019.
 * #0514 https://leetcode.com/problems/freedom-trail/
 */
public class FreedomTrail {
	private Integer[][] dp;

	// time O(2^n), space O(m^n)
	public int findRotateSteps(String ring, String key) {
		dp = new Integer[ring.length()][key.length()];
		return dfs(0, 0, ring.toCharArray(), key.toCharArray());
	}

	private int dfs(int i, int j, char[] ring, char[] key){
		if(j == key.length){
			return 0;
		}
		if(dp[i][j] != null){
			return dp[i][j];
		}
		int clockwise = 0, clockwiseIndex = i;
		while(ring[clockwiseIndex] != key[j]){
			clockwiseIndex++;
			clockwise++;
			if(clockwiseIndex == ring.length){
				clockwiseIndex = 0;
			}
		}
		int counterClockwise = 0, counterClockwiseIndex = i;
		while(ring[counterClockwiseIndex] != key[j]){
			counterClockwiseIndex--;
			counterClockwise++;
			if(counterClockwiseIndex == -1){
				counterClockwiseIndex = ring.length - 1;
			}
		}
		dp[i][j] = 1 + Math.min(clockwise + dfs(clockwiseIndex, j + 1, ring, key),
			counterClockwise + dfs(counterClockwiseIndex, j + 1, ring, key));
		return dp[i][j];
	}
}
