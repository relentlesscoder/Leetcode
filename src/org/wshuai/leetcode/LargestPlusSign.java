package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/6/19.
 * #764 https://leetcode.com/problems/largest-plus-sign/
 */
public class LargestPlusSign {
	public int orderOfLargestPlusSign(int N, int[][] mines) {
		int res = 0;
		int[][] dp = new int[N][N];
		for(int i = 0; i < N; i++){
			Arrays.fill(dp[i], 1);
		}
		for(int[] m : mines){
			dp[m[0]][m[1]] = 0;
		}
		for(int i = 0; i < N; i++){
			//left
			int count = 0;
			for(int j = 0; j < N; j++){
				if(dp[i][j] != 0){
					count++;
				}else{
					count = 0;
				}
				dp[i][j] = count;
			}
			//right
			count = 0;
			for(int j = N - 1; j >= 0; j--){
				if(dp[i][j] != 0){
					count++;
				}else{
					count = 0;
				}
				dp[i][j] = Math.min(count, dp[i][j]);
			}
		}
		for(int j = 0; j < N; j++){
			//top
			int count = 0;
			for(int i = 0; i < N; i++){
				if(dp[i][j] != 0){
					count++;
				}else{
					count = 0;
				}
				dp[i][j] = Math.min(count, dp[i][j]);
			}
			//down
			count = 0;
			for(int i = N - 1; i >= 0; i--){
				if(dp[i][j] != 0){
					count++;
				}else{
					count = 0;
				}
				dp[i][j] = Math.min(count, dp[i][j]);
				res = Math.max(res, dp[i][j]);
			}
		}
		return res;
	}
}
