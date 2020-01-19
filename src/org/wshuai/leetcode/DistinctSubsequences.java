package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 02/06/2017.
 * #0115 https://leetcode.com/problems/distinct-subsequences/
 */
public class DistinctSubsequences {
	// time O(m*n), space O(m*n)
	public int numDistinct(String s, String t) {
		int n = s.length();
		int m = t.length();
		int[][] dp = new int[m + 1][n + 1];
		Arrays.fill(dp[0], 1);
		for(int i = 1; i <= m; i++){
			for(int j = 1; j <= n; j++){
				dp[i][j] = dp[i][j - 1];
				if(t.charAt(i - 1) == s.charAt(j - 1)){
					dp[i][j] += dp[i - 1][j - 1];
				}
			}
		}
		return dp[m][n];
	}

	// time O(m*n), space O(2*n)
	public int numDistinctRollingArray(String s, String t) {
		int n = s.length(), m = t.length(), prev = 0, cur = 1;
		int[][] dp = new int[2][n + 1];
		Arrays.fill(dp[0], 1);
		for(int i = 1; i <= m; i++){
			for(int j = 1; j <= n; j++){
				dp[cur][j] = dp[cur][j - 1];
				if(t.charAt(i - 1) == s.charAt(j - 1)){
					dp[cur][j] += dp[prev][j - 1];
				}
			}
			prev = cur;
			cur = 1 - prev;
			Arrays.fill(dp[cur], 0);
		}
		return dp[prev][n];
	}
}

/*
	*	r	a	b	b	b	i	t
*	1	1	1	1	1	1	1	1
r	0	1	1	1	1	1	1	1
a	0	0	1	1	1	1	1	1
b	0	0	0	1	2	3	3	3
b	0	0	0	0	1	3	3	3
i	0	0	0	0	0	0	3	0
t	0	0	0	0	0	0	0	3
 */
