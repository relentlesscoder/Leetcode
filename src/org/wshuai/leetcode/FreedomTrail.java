package org.wshuai.leetcode;

/**
 * Created by Wei on 11/21/19.
 * #514 https://leetcode.com/problems/freedom-trail/
 */
public class FreedomTrail {
	private Integer[][] dp;

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
		int cw = 0;
		int ci = i;
		while(ring[ci] != key[j]){
			ci++;
			cw++;
			if(ci == ring.length){
				ci = 0;
			}
		}
		int ccw = 0;
		int cci = i;
		while(ring[cci] != key[j]){
			cci--;
			ccw++;
			if(cci == -1){
				cci = ring.length - 1;
			}
		}
		dp[i][j] = 1 + Math.min(cw + dfs(ci, j + 1, ring, key),
				ccw + dfs(cci, j + 1, ring, key));
		return dp[i][j];
	}
}
