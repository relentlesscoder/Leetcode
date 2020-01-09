package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/03/2016.
 * #0057 https://leetcode.com/problems/insert-interval/
 */
public class InsertInterval {
	// time O(n)
	public int[][] insert(int[][] intervals, int[] newInterval) {
		List<int[]> res = new ArrayList<>();
		int n = intervals.length;
		int i = 0;
		// non-overlapping intervals on the left
		while(i < n && intervals[i][1] < newInterval[0]){
			res.add(intervals[i]);
			i++;
		}
		// merge overlapping intervals
		while(i < n && intervals[i][0] <= newInterval[1]){
			int[] cur = intervals[i];
			newInterval = new int[]{Math.min(newInterval[0], cur[0]),
					Math.max(newInterval[1], cur[1])};
			i++;
		}
		res.add(newInterval);
		// non-overlapping intervals on the right
		while(i < n){
			res.add(intervals[i]);
			i++;
		}
		return res.toArray(new int[res.size()][]);
	}
}
