package org.wshuai.leetcode;

/**
 * Created by Wei on 01/12/2020.
 * #1320 https://leetcode.com/problems/minimum-distance-to-type-a-word-using-two-fingers/
 */
public class MinimumDistanceToTypeAWordUsingTwoFingers {
	private Integer[][][] dp;

	public int minimumDistance(String word) {
		int n = word.length();
		int res = Integer.MAX_VALUE;
		dp = new Integer[26][26][word.length()];
		for(int i = 0; i < 26; i++){
			for(int j = 0; j < 26; j++){
				res = Math.min(res, dfs(0, n, i, j, word));
			}
		}
		return res;
	}

	private int dfs(int k, int n, int i, int j, String word){
		if(k == n){
			return 0;
		}
		if(dp[i][j][k] != null){
			return dp[i][j][k];
		}
		int next = word.charAt(k) - 'A';
		int dist = Math.min(getDistance(i, next) + dfs(k + 1, n, next, j, word),
			getDistance(j, next) + dfs(k + 1, n, i, next, word));
		dp[i][j][k] = dist;
		return dp[i][j][k];
	}

	private int getDistance(int p1, int p2){
		int x1 = p1/6, y1 = p1%6, x2 = p2/6, y2 = p2%6;
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}
