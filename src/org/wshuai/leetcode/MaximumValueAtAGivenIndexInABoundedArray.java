package org.wshuai.leetcode;

/**
 * Created by Wei on 09/22/2023.
 * #1802 https://leetcode.com/problems/maximum-value-at-a-given-index-in-a-bounded-array/
 */
public class MaximumValueAtAGivenIndexInABoundedArray {

	// time O(n * log(max sum)), space O(1)
	public int maxValue(int n, int index, int maxSum) {
		int low = 0, high = maxSum;
		while (low < high) {
			int mid = (low + high + 1) >> 1;
			if (canBuildArray(n, index, maxSum, mid)) {
				low = mid;
			} else {
				high = mid - 1;
			}
		}
		return low;
	}

	private boolean canBuildArray(int n, int index, int maxSum, int threshold) {
		long sum = 0;
		// calculate left side of index
		if (threshold >= index + 1) {
			// index: 0 1 2 [3] ...
			// Value: 2 3 4 [5] ...
			// Value: 1 2 3 [4] ...
			sum += 1L * (2 * threshold - index) * (index + 1) / 2;
		} else {
			// index: 0 1 2 [3] ...
			// Value: 1 1 1 [2] ...
			// Value: 1 1 2 [3] ...
			sum += 1L * (1 + threshold) * threshold / 2 + 1L * (index + 1 - threshold); // need to add extra 1s at left
		}
		// calculate right side of index
		if (threshold >= n - index) {
			// index: ... 3 4 5 [6]
			// Value: ... 4 3 2 [1]
			// Value: ... 5 4 3 [2]
			// Value: ... 6 5 4 [3]
			sum += 1L * (2 * threshold - n + index + 1) * (n - index) / 2;
		} else {
			// index: ... 3 4 5 [6]
			// Value: ... 3 2 1 [1]
			// Value: ... 2 1 1 [1]
			sum += 1L * (1 + threshold) * threshold / 2 + 1L * (n - index - threshold); // need to add extra 1s at right
		}
		return sum - threshold <= maxSum;
	}
}
