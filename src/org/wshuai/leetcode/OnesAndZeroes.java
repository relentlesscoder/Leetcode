package org.wshuai.leetcode;

/**
 * Created by Wei on 02/25/2017.
 * #0474 https://leetcode.com/problems/ones-and-zeroes/
 */
public class OnesAndZeroes {
	// time O(m*n*k), space O(m*n)
	// Knapsack
	public int findMaxForm(String[] strs, int m, int n) {
		int[][] dp = new int[m + 1][n + 1];
		for(String str : strs){
			int ones = 0, zeros = 0;
			for(char c : str.toCharArray()){
				if(c == '1'){
					ones++;
				}else{
					zeros++;
				}
			}
			for(int i = m; i >= zeros; i--){
				for(int j = n; j >= ones; j--){
					dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
				}
			}
		}
		return dp[m][n];
	}
}
