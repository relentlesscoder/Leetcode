package org.wshuai.leetcode;

/**
 * Created by Wei on 12/16/2019.
 * #1289 https://leetcode.com/problems/minimum-falling-path-sum-ii/
 */
public class MinimumFallingPathSumII {
	public int minFallingPathSum(int[][] arr) {
		int n = arr.length;
		if(n == 1){
			return arr[0][0];
		}
		int[][] dp = new int[2][n];
		int[][] mins = new int[][]{
				{-1, Integer.MAX_VALUE},
				{-1, Integer.MAX_VALUE}
		};
		int cur = 1;
		int prev = 0;
		for(int i = 0; i < n; i++){
			int[][] cur_mins = new int[][]{
					{-1, Integer.MAX_VALUE},
					{-1, Integer.MAX_VALUE}
			};
			for(int j = 0; j < n; j++){
				dp[cur][j] = arr[i][j] + (i == 0 ? 0 : getMin(mins, j));
				if(dp[cur][j] < cur_mins[0][1]){
					cur_mins[1] = cur_mins[0];
					cur_mins[0] = new int[]{j, dp[cur][j]};
				}else if(dp[cur][j] < cur_mins[1][1]){
					cur_mins[1] = new int[]{j, dp[cur][j]};
				}
			}
			mins = cur_mins;
			prev = cur;
			cur = 1 - cur;
		}
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < n; i++){
			min = Math.min(min, dp[prev][i]);
		}
		return min;
	}

	private int getMin(int[][] arr, int j){
		return j == arr[0][0] ? arr[1][1] : arr[0][1];
	}
}
