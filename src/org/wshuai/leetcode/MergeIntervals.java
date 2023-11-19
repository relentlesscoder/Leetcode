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
		List<int[]> res = new ArrayList<>();
		for (int[] in : intervals) {
			if (res.size() > 0 && in[0] <= res.get(res.size() - 1)[1]) {
				res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], in[1]);
			} else {
				res.add(in);
			}
		}
		return res.toArray(new int[res.size()][]);
	}
}
