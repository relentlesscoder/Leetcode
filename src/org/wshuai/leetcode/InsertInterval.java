package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/03/2016.
 * #0057 https://leetcode.com/problems/insert-interval/
 */
public class InsertInterval {

	// time O(n), space O(n)
	public int[][] insert(int[][] intervals, int[] newInterval) {
		List<int[]> res = new ArrayList<>();
		int n = intervals.length, i = 0;
		// non-overlapping intervals on the left
		while(i < n && intervals[i][1] < newInterval[0]){
			res.add(intervals[i++]);
		}
		// merge overlapping intervals
		while(i < n && intervals[i][0] <= newInterval[1]){
			newInterval = new int[]{Math.min(intervals[i][0], newInterval[0]),
					Math.max(intervals[i++][1], newInterval[1])};
		}
		res.add(newInterval);
		// non-overlapping intervals on the right
		while(i < n){
			res.add(intervals[i++]);
		}
		return res.toArray(new int[res.size()][2]);
	}
}
