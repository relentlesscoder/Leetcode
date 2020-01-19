package org.wshuai.leetcode;

/**
 * Created by Wei on 12/04/2019.
 * #1278 https://leetcode.com/problems/palindrome-partitioning-iii/
 */
public class PalindromePartitioningIII {
	// time O(k*n^2), space O(n^2)
	// https://www.youtube.com/watch?v=kD6ShM6jr3g
	public int palindromePartition(String s, int k) {
		int N = s.length();
		int[][] dp = new int[N][k + 1];
		int[][] cost = new int[N][N];
		for(int l = 2; l <= N; l++){
			for(int i = 0; i + l <= N; i++){
				int j = i + l - 1;
				cost[i][j] = (s.charAt(i) == s.charAt(j) ? 0 : 1) + cost[i + 1][j - 1];
			}
		}
		for(int i = 0; i < N; i++){
			dp[i][1] = cost[0][i];
			for(int x = 2; x <= k; x++){
				dp[i][x] = N + 1;
				// j start from i - 1 because x is at least 2 (two partitions)
				for(int j = i - 1; j >= 0; j--){
					dp[i][x] = Math.min(dp[i][x], dp[j][x - 1] + cost[j + 1][i]);
				}
			}
		}
		return dp[N - 1][k];
	}
}
