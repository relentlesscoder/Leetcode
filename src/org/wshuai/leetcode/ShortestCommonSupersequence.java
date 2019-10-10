package org.wshuai.leetcode;

/**
 * Created by Wei on 10/5/2019.
 * #1092 https://leetcode.com/problems/shortest-common-supersequence/
 */
public class ShortestCommonSupersequence {
	public String shortestCommonSupersequence(String str1, String str2) {
		int r = str1.length();
		int c = str2.length();
		int[][] dp = new int[r + 1][c + 1];

		for(int i = 0; i <= r; i++){
			dp[i][0] = i;
		}
		for(int j = 1; j <= c; j++){
			dp[0][j] = j;
		}
		for(int i = 1; i <= r; i++){
			for(int j = 1; j <= c; j++){
				if(str1.charAt(i - 1) == str2.charAt(j - 1)){
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}else{
					dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
				}
			}
		}

		int i = r;
		int j = c;
		char[] arr = new char[dp[r][c]];
		int k = dp[r][c] - 1;
		while(i > 0 && j > 0){
			if(str1.charAt(i - 1) == str2.charAt(j - 1)){
				arr[k--] = str1.charAt(i - 1);
				i--;
				j--;
			}else if(dp[i - 1][j] <= dp[i][j - 1]){
				arr[k--] = str1.charAt(i - 1);
				i--;
			}else{
				arr[k--] = str2.charAt(j - 1);
				j--;
			}
		}
		while (i > 0) {
			arr[k--] = str1.charAt(i-1);
			i--;
		}
		while (j > 0) {
			arr[k--] = str2.charAt(j-1);
			j--;
		}
		return new String(arr);
	}
}
