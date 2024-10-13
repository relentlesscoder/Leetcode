package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 01/09/2020.
 * #0056 https://leetcode.com/problems/merge-intervals/
 */
public class MergeIntervals {

	// time O(n * log(n)), space O(n)
	public int[][] merge(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
		List<int[]> merged = new ArrayList<>();
		for (int[] in : intervals) {
			if (!merged.isEmpty() && in[0] <= merged.get(merged.size() - 1)[1]) {
				merged.get(merged.size() - 1)[1] = Math.max(in[1], merged.get(merged.size() - 1)[1]);
			} else {
				merged.add(in);
			}
		}
		return merged.toArray(new int[merged.size()][]);
	}
}
