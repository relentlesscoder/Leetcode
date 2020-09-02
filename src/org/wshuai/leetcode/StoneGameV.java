package org.wshuai.leetcode;

/**
 * Created by Wei on 09/01/2020.
 * #1563 https://leetcode.com/problems/stone-game-v/
 */
public class StoneGameV {

	// time O(n^3), space O(n^2)
	public int stoneGameV(int[] stoneValue) {
		int n = stoneValue.length;
		int[] prefix = new int[n + 1];
		for(int i = 1; i <= n; i++){
			prefix[i] = prefix[i - 1] + stoneValue[i - 1];
		}
		return dfs(stoneValue, 0, n - 1, new int[n][n], prefix);
	}

	private int dfs(int[] stoneValue, int start, int end, int[][] dp, int[] prefix){
		if(start == end){
			return 0;
		}
		if(dp[start][end] > 0){
			return dp[start][end];
		}
		int max = Integer.MIN_VALUE;
		for(int i = start + 1; i <= end; i++){
			int left = prefix[i] - prefix[start];
			int right = prefix[end + 1] - prefix[i];
			if(left > right){
				max = Math.max(max, right + dfs(stoneValue, i, end, dp, prefix));
			}else if(right > left){
				max = Math.max(max, left + dfs(stoneValue, start, i - 1, dp, prefix));
			}else{
				max = Math.max(max, left + Math.max(dfs(stoneValue, i, end, dp, prefix), dfs(stoneValue, start, i - 1, dp, prefix)));
			}
		}
		dp[start][end] = max;
		return max;
	}
}
