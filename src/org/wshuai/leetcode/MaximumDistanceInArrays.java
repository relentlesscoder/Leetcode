package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 7/29/2017.
 * #624 https://leetcode.com/problems/maximum-distance-in-arrays/
 */
public class MaximumDistanceInArrays {
	public int maxDistance(List<List<Integer>> arrays) {
		int result = 0;
		List<Integer> lst = arrays.get(0);
		int max = lst.get(lst.size() - 1);
		int min = lst.get(0);
		for (int i = 1; i < arrays.size(); i++) {
			lst = arrays.get(i);
			result = Math.max(result, Math.abs(lst.get(0) - max));
			result = Math.max(result, Math.abs(lst.get(lst.size() - 1) - min));
			max = Math.max(max, lst.get(lst.size() - 1));
			min = Math.min(min, lst.get(0));
		}
		return result;
	}
}
