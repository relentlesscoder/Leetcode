package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/15/2019.
 * #1288 https://leetcode.com/problems/remove-covered-intervals/
 */
public class RemoveCoveredIntervals {
	public int removeCoveredIntervals(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
		int res = 0;
		int cur_right = 0;
		for(int[] it : intervals){
			if(it[1] > cur_right){
				res++;
			}
			cur_right = Math.max(cur_right, it[1]);
		}
		return res;
	}
}
