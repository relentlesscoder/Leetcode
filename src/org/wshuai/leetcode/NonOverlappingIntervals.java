package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/13/16.
 * #435 https://leetcode.com/problems/non-overlapping-intervals/
 */
public class NonOverlappingIntervals {
	//O(n*lg(n))
	public int eraseOverlapIntervals(Interval[] intervals) {
		if (intervals == null || intervals.length == 0) {
			return 0;
		}
		int len = intervals.length;
		Arrays.sort(intervals, new IntervalComparator());
		int count = 0;
		Interval last = intervals[0];
		for (int i = 1; i < len; i++) {
			Interval curr = intervals[i];
			//If two intervals overlap, remove the one with larger end
			if (curr.start < last.end) {
				if (curr.end < last.end) {
					last = curr;
				}
				count++;
			} else {
				last = curr;
			}
		}
		return count;
	}
}
