package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 01/09/2020.
 * #0056 https://leetcode.com/problems/merge-intervals/
 */
public class MergeIntervals {

	// time O(n*log(n))
	public int[][] merge(int[][] intervals) {
		List<int[]> res = new ArrayList<>();
		Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
		for(int[] in : intervals){
			if(res.size() == 0 || res.get(res.size() - 1)[1] < in[0]){
				res.add(in);
			}else{
				res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], in[1]);
			}
		}
		return res.toArray(new int[res.size()][2]);
	}
}
