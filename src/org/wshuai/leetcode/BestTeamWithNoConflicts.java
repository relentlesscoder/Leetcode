package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/22/2020.
 * #1626 https://leetcode.com/problems/best-team-with-no-conflicts/
 */
public class BestTeamWithNoConflicts {

	private static final int MAX = 1_000;

	// O(n * log(n) + n * log(MAX)), space O(n + MAX)
	public int bestTeamScoreBinaryIndexedTree(int[] scores, int[] ages) {
		int n = scores.length;
		Integer[] indexes = new Integer[n];
		Arrays.setAll(indexes, i -> i); // O(n)
		Arrays.sort(indexes, (i, j) ->
				scores[i] != scores[j] ? scores[i] - scores[j] : ages[i] - ages[j]); // O(n * log(n))
		BIT bit = new BIT(MAX + 1);
		for (int i : indexes) { // O(n)
			int index = ages[i] + 1;
			int sum = bit.query(index); // O(log(MAX))
			bit.update(index, sum + scores[i]); // O(log(MAX))
		}
		return bit.query(MAX + 1); // O(log(MAX))
	}

	private static class BIT {

		private int[] tree;

		public BIT(int n) {
			tree = new int[n + 1];
		}

		public int query(int index) {
			int res = 0;
			while (index > 0) {
				res = Math.max(res, tree[index]);
				index -= index & -index;
			}
			return res;
		}

		public void update(int index, int sum) {
			while (index < tree.length) {
				tree[index] = Math.max(sum, tree[index]);
				index += index & -index;
			}
		}
	}

	// time O(n * log(n) + n * max(age)), space O(n + max(age))
	public int bestTeamScoreDPByAge(int[] scores, int[] ages) {
		int res = 0, n = scores.length, m = 0;
		Integer[] indexes = new Integer[n];
		for (int i = 0; i < n; i++) {
			indexes[i] = i;
			m = Math.max(m, ages[i]);
		}
		int[] dp = new int[m + 1];
		Arrays.sort(indexes, (i, j) ->
				scores[i] != scores[j] ? scores[i] - scores[j] : ages[i] - ages[j]);
		// After sorting by score and age:
		// scores: [4, 5, 5, 6]
		// ages:   [2, 1, 1, 2]
		// dp:     dp[2] = 4
		//         dp[1] = 5
		//         dp[1] = 10
		//         dp[2] = 16
		for (int i = 0; i < n; i++) {
			int age = ages[indexes[i]];
			for (int j = 1; j <= age; j++) {
				dp[age] = Math.max(dp[age], dp[j]);
			}
			dp[age] += scores[indexes[i]];
			res = Math.max(res, dp[age]);
		}
		return res;
	}

	// time O(n^2), space O(n)
	public int bestTeamScoreDPByScore(int[] scores, int[] ages) {
		// Same as #0300 longest increasing subsequence
		int res = 0, n = scores.length;
		Integer[] indexes = new Integer[n];
		int[] dp = new int[n];
		Arrays.setAll(indexes, i -> i);
		Arrays.sort(indexes, (i, j) ->
				scores[i] != scores[j] ? scores[i] - scores[j] : ages[i] - ages[j]);
		// After sorting by score and age:
		// scores: [4, 5, 5, 6]
		// ages:   [2, 1, 1, 2]
		// dp:     [4, 5, 10, 16]
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (ages[indexes[j]] <= ages[indexes[i]]) {
					dp[i] = Math.max(dp[i], dp[j]);
				}
			}
			dp[i] += scores[indexes[i]];
			res = Math.max(res, dp[i]);
		}
		return res;
	}
}
