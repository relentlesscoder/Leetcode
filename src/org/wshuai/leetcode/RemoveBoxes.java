package org.wshuai.leetcode;

/**
 * Created by Wei on 11/21/19.
 * #546 https://leetcode.com/problems/remove-boxes/
 */
public class RemoveBoxes {
	//https://www.youtube.com/watch?v=wT7aS5fHZhs
	private int[][][] dp;

	public int removeBoxes(int[] boxes) {
		int N = boxes.length;
		dp = new int[N][N][N];
		return dfs(boxes, 0, N - 1, 0);
	}

	private int dfs(int[] boxes, int l, int r, int k){
		if(l > r){
			return 0;
		}
		while(l < r && boxes[r - 1] == boxes[r]){
			r--;
			k++;
		}
		if(dp[l][r][k] > 0){
			return dp[l][r][k];
		}
		dp[l][r][k] = (k + 1) * (k + 1) + dfs(boxes, l, r - 1, 0);
		for(int i = l; i < r; i++){
			if(boxes[i] == boxes[r]){
				dp[l][r][k] = Math.max(dp[l][r][k], dfs(boxes, l, i, k + 1)
					+ dfs(boxes, i + 1, r - 1, 0));
			}
		}
		return dp[l][r][k];
	}
}
