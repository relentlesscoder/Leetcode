package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/04/2023.
 * #1751 https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii/
 */
public class MaximumNumberOfEventsThatCanBeAttendedII {

	// time O(k * n * log(n)), space O(k * n)
	public int maxValueBottomUpDP(int[][] events, int k) {
		int n = events.length;
		Arrays.sort(events, (a, b) -> a[0] - b[0]);
		int[][] dp = new int[k + 1][n + 1];
		for (int j = n - 1; j >= 0; j--) {
			int nextIndex = binarySearch(events, events[j][1]);
			for (int i = 1; i <= k; i++) {
				dp[i][j] = dp[i][j + 1];
				dp[i][j] = Math.max(dp[i][j], events[j][2] + dp[i - 1][nextIndex]);
			}
		}
		return dp[k][0];
	}


	// time O(k * n * log(n)), space O(k * n)
	public int maxValueTopDownDP(int[][] events, int k) {
		int n = events.length;
		Arrays.sort(events, (a, b) -> a[0] - b[0]);
		int[][] dp = new int[k + 1][n];
		for (int[] r : dp) {
			Arrays.fill(r, -1);
		}
		int[] nextStartIndex = new int[n]; // cache next start index to reduce the number of binary searches
		for (int i = 0; i < n; i++) {
			nextStartIndex[i] = binarySearch(events, events[i][1]);
		}
		return dfs(0, k, events, dp, nextStartIndex);
	}

	private int dfs(int curr, int count, int[][] events, int[][] dp, int[] nextStartIndex) {
		if (count == 0 || curr == events.length) {
			return 0;
		}
		if (dp[count][curr] != -1) {
			return dp[count][curr];
		}
		int nextIndex = nextStartIndex[curr];
		dp[count][curr] = Math.max(dfs(nextIndex, count - 1, events, dp, nextStartIndex) + events[curr][2], dfs(curr + 1, count, events, dp, nextStartIndex));
		return dp[count][curr];
	}

	private int binarySearch(int[][] events, int endTime) {
		int low = 0, high = events.length; // n means no value is greater than it
		while (low < high) {
			int mid = (low + high) >> 1;
			if (events[mid][0] <= endTime) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return low;
	}
}
