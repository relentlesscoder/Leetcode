package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 01/09/2020.
 * #0056 https://leetcode.com/problems/merge-intervals/
 */
public class MergeIntervals {
	// time O(n)
	public int[][] merge(int[][] intervals) {
		int n = intervals.length;
		if(n <= 1){
			return intervals;
		}
		Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
		List<int[]> res = new ArrayList<>();
		for(int i = 0; i < n; i++){
			int[] cur = intervals[i];
			if(res.size() == 0 || cur[0] > res.get(res.size() - 1)[1]){
				res.add(cur);
			}else{
				int[] prev = res.get(res.size() - 1);
				prev[1] = Math.max(prev[1], cur[1]);
			}
		}
		return res.toArray(new int[res.size()][]);
	}
}
