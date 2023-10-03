package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/08/2020.
 * #1641 https://leetcode.com/problems/count-sorted-vowel-strings/
 */
public class CountSortedVowelStrings {

	// time O(n), space O(n)
	public int countVowelStrings(int n) {
		// dp[i][j] denotes number of strings has length i + 1 which
		// ends with mapping[j]. mapping is ["a","e","i","o","u"].
		int[][] dp = new int[n][5];
		Arrays.fill(dp[0], 1);
		for (int i = 1; i < n; i++) {
			int sum = 0;
			for (int j = 0; j < 5; j++) {
				sum += dp[i - 1][j];
				dp[i][j] = sum;
			}
		}
		return dp[n - 1][0] + dp[n - 1][1] + dp[n - 1][2] + dp[n - 1][3] + dp[n - 1][4];
	}
}
