package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 07/29/2017.
 * #0624 https://leetcode.com/problems/maximum-distance-in-arrays/
 */
public class MaximumDistanceInArrays {
	// time O(n)
	public int maxDistance(List<List<Integer>> arrays) {
		List<Integer> cur = arrays.get(0);
		int res = Integer.MIN_VALUE, max = cur.get(cur.size() - 1), min = cur.get(0);
		for(int i = 1; i < arrays.size(); i++){
			cur = arrays.get(i);
			int curMax = cur.get(cur.size() - 1), curMin = cur.get(0);
			res = Math.max(res, Math.abs(curMin - max));
			res = Math.max(res, Math.abs(curMax - min));
			max = Math.max(max, curMax);
			min = Math.min(min, curMin);
		}
		return res;
	}
}
