package org.wshuai.leetcode;

/**
 * Created by Wei on 09/26/2023.
 * #2140 https://leetcode.com/problems/solving-questions-with-brainpower/
 */
public class SolvingQuestionsWithBrainpower {

	// time O(n), space O(n)
	public long mostPoints(int[][] questions) {
		int n = questions.length;
		long[] dp = new long[n]; // dp[i] denotes the maximum score we can get starting from question i
		dp[n - 1] = questions[n - 1][0]; // calculate backwards the maximum score
		for (int i = n - 2; i >= 0; i--) {
			int j = i + questions[i][1] + 1;
			dp[i] = questions[i][0] + (j < n ? dp[j] : 0); // take the current question i and skip the next questions[i][1] questions
			dp[i] = Math.max(dp[i], dp[i + 1]); // skip the current question i
		}
		return dp[0];
	}

	// time O(n), space O(n)
	public long mostPointsRecursive(int[][] questions) {
		Long[] dp = new Long[questions.length];
		return dfs(0, questions, dp);
	}

	private long dfs(int curr, int[][] questions, Long[] dp) {
		if (curr >= questions.length) {
			return 0;
		}
		if (dp[curr] != null) {
			return dp[curr];
		}
		dp[curr] = Math.max(questions[curr][0] + dfs(curr + questions[curr][1] + 1, questions, dp),
				dfs(curr + 1, questions, dp));
		return dp[curr];
	}
}
