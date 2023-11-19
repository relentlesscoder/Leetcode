package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/13/2016.
 * #0435 https://leetcode.com/problems/non-overlapping-intervals/
 */
public class NonOverlappingIntervals {

	// time O(n*log(n))
	public int eraseOverlapIntervals(int[][] intervals) {
		if(intervals == null || intervals.length == 0){
			return 0;
		}
		Arrays.sort(intervals, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
		int res = 0, n = intervals.length;
		int[] prev = intervals[0];
		for(int i = 1; i < n; i++){
			int[] cur = intervals[i];
			if(cur[0] < prev[1]){
				res++;
				if(cur[1] < prev[1]){ // keep the one finished earlier
					prev = cur;
				}
			}else{
				prev = cur;
			}
		}
		return res;
	}
}
