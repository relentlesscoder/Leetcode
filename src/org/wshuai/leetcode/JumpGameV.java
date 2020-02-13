package org.wshuai.leetcode;

/**
 * Created by Wei on 02/13/2020.
 * #1340 https://leetcode.com/problems/jump-game-v/
 */
public class JumpGameV {
	// time O(n*d), space O(n)
	public int maxJumps(int[] arr, int d) {
		int res = 1, n = arr.length;
		int[] dp = new int[n];
		for(int i = 0; i < n; i++){
			dfs(i, arr, d, dp);
			res = Math.max(res, dp[i]);
		}
		return res;
	}

	private int dfs(int i, int[] arr, int d, int[] dp){
		if(dp[i] > 0){
			return dp[i];
		}
		int res = 0, l = i - 1, r = i + 1;;
		while(l >= 0 && l >= i - d && arr[i] > arr[l]){
			res = Math.max(res, dfs(l, arr, d, dp));
			l--;
		}
		while(r < arr.length && r <= i + d && arr[i] > arr[r]){
			res = Math.max(res, dfs(r, arr, d, dp));
			r++;
		}
		dp[i] = res + 1;
		return dp[i];
	}
}
