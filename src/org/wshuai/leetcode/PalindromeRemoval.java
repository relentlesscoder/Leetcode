package org.wshuai.leetcode;

/**
 * Created by Wei on 12/6/2019.
 * #1246 https://leetcode.com/problems/palindrome-removal/
 */
public class PalindromeRemoval {
	// https://www.geeksforgeeks.org/minimum-steps-to-delete-a-string-after-repeated-deletion-of-palindrome-substrings/
	public int minimumMoves(int[] arr) {
		int n = arr.length;
		int[][] dp = new int[n + 1][n + 1];
		for(int len = 1; len <= n; len++){
			for(int i = 0; i + len <= n; i++){
				int j = i + len - 1;
				if(len == 1){
					dp[i][j] = 1;
				}else{
					dp[i][j] = 1 + dp[i + 1][j];
					if(arr[i] == arr[i + 1]){
						dp[i][j] = Math.min(dp[i][j], 1 + dp[i + 2][j]);
					}
					for(int k = i + 2; k <= j; k++){
						if(arr[k] == arr[i]){
							dp[i][j] = Math.min(dp[i][j], dp[i + 1][k - 1] + dp[k + 1][j]);
						}
					}
				}
			}
		}
		return dp[0][n-1];
	}
}
