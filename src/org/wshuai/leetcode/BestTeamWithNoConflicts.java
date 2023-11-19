package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/22/2020.
 * #1626 https://leetcode.com/problems/best-team-with-no-conflicts/
 */
public class BestTeamWithNoConflicts {

	// time O(n^2), space O(n)
	// same as #0300
	public int bestTeamScore(int[] scores, int[] ages) {
		int res = 0, n = scores.length;
		int[][] sortByAge = new int[n][2];
		for(int i = 0; i < n; i++){
			sortByAge[i] = new int[]{scores[i], ages[i]};
		}
		Arrays.sort(sortByAge, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
		// longest increasing subsequence after sorting
		int[] dp = new int[n];
		for(int i = 0; i < n; i++){
			dp[i] = sortByAge[i][0];
			for(int j = i - 1; j >= 0; j--){
				if(sortByAge[j][0] <= sortByAge[i][0]){
					dp[i] = Math.max(dp[i], dp[j] + sortByAge[i][0]);
				}
			}
			res = Math.max(res, dp[i]);
		}
		return res;
	}
}
