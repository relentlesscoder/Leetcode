package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/11/2016.
 * #0132 https://leetcode.com/problems/palindrome-partitioning-ii/
 */
public class PalindromePartitioningII {
	// time O(n^2), space O(n^2)
	public int minCut(String s) {
		if(s == null || s.isEmpty()){
			return 0;
		}
		int n = s.length();
		int[] min = new int[n + 1];
		Arrays.fill(min, n);
		boolean[][] dp = new boolean[n][n];
		for(int l = 1; l <= n; l++){
			for(int i = 0; i + l - 1 < n; i++){
				int j = i + l - 1;
				dp[i][j] = (s.charAt(i) == s.charAt(j)) && (l <= 2 || dp[i + 1][j - 1]);
			}
		}
		min[0] = 0;
		for(int i = 1; i <= n; i++){
			for(int j = i; j >= 1; j--){
				if(dp[j - 1][i - 1]){
					min[i] = Math.min(min[j - 1] + 1, min[i]);
				}
			}
		}
		return min[n] - 1;
	}
}
