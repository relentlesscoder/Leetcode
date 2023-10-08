package org.wshuai.leetcode;

/**
 * Created by Wei on 10/08/2023.
 * #1947 https://leetcode.com/problems/maximum-compatibility-score-sum/
 */
public class MaximumCompatibilityScoreSum {

	// time O(m * 256), space O(m * 256)
	public int maxCompatibilitySum(int[][] students, int[][] mentors) {
		int m = students.length, n = students[0].length;
		int[] studentAnswers = new int[m], mentorAnswers = new int[m];
		Integer[][] dp = new Integer[m + 1][256]; // dp[i][j] denotes the optimal score to pick when student i has picked bit map j to pick a mentor
		for (int i = 0; i < m; i++) {
			int sa = 0, ma = 0;
			for (int j = 0; j < n; j++) {
				int shift = (1 & students[i][j]) << j; // use bit map to represent answer sheet
				sa += shift;
				shift = (1 & mentors[i][j]) << j;
				ma += shift;
			}
			studentAnswers[i] = sa;
			mentorAnswers[i] = ma;
		}
		return dfs(0, studentAnswers, mentorAnswers, 0, m, n, dp);
	}

	private int dfs(int curr, int[] students, int[] mentors, int picked, int m, int n, Integer[][] dp) {
		if (curr == m) {
			return 0;
		}
		if (dp[curr][picked] != null) {
			return dp[curr][picked];
		}
		int res = 0;
		for (int i = 0; i < m; i++) {
			if (((1 << i) & picked) == 0) { // if the mentor is not picked
				int bitMap = (picked | (1 << i)); // pick the mentor and update the bit map
				res = Math.max(res, getScore(students[curr], mentors[i], n)
						+ dfs(curr + 1, students, mentors, bitMap, m, n, dp));
			}
		}
		return dp[curr][picked] = res;
	}

	private int getScore(int s, int m, int n) {
		int score = 0; // calculate the score
		for (int i = 0; i < n; i++) {
			score += ((s & 1) == (m & 1) ? 1 : 0);
			s >>= 1;
			m >>= 1;
		}
		return score;
	}
}
