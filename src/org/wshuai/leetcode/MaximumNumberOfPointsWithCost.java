package org.wshuai.leetcode;

/**
 * Created by Wei on 09/23/2023.
 * #1937 https://leetcode.com/problems/maximum-number-of-points-with-cost/description/
 */
public class MaximumNumberOfPointsWithCost {

	// time O(m * n), space O(n)
	public long maxPoints(int[][] points) {
		long res = 0L;
		int m = points.length, n = points[0].length;
		long[] prev = new long[n];
		for (int i = 0; i < n; i++) {
			prev[i] = points[0][i];
		}
		for (int i = 1; i < m; i++) {
			// https://leetcode.com/problems/maximum-number-of-points-with-cost/solutions/1344908/c-java-python-3-dp-explanation-with-pictures-o-mn-time-o-n-space/
			long[] left = new long[n], right = new long[n], curr = new long[n];
			left[0] = prev[0];
			for (int j = 1; j < n; j++) {
				left[j] = Math.max(left[j - 1] - 1, prev[j]); // since all the values in left shift one, we can compare to left[j - 1] - 1
			}
			right[n - 1] = prev[n - 1];
			for (int j = n - 2; j >= 0; j--) {
				right[j] = Math.max(right[j + 1] - 1, prev[j]);
			}
			for (int j = 0; j < n; j++) {
				curr[j] = points[i][j] + Math.max(left[j], right[j]);
			}
			prev = curr;
		}
		for (int i = 0; i < n; i++) {
			res = Math.max(res, prev[i]);
		}
		return res;
	}
}
