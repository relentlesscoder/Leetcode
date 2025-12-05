package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 07/29/2017.
 * #0624 https://leetcode.com/problems/maximum-distance-in-arrays/
 */
public class MaximumDistanceInArrays {

	// time O(n), space O(1)
	public int maxDistance(List<List<Integer>> arrays) {
		int res = Integer.MIN_VALUE;
		int max = arrays.get(0).get(arrays.get(0).size() - 1);
		int min = arrays.get(0).get(0);
		for (int i = 1; i < arrays.size(); i++) {
			List<Integer> nums = arrays.get(i);
			int curMin = nums.get(0);
			int curMax = nums.get(nums.size() - 1);
			res = Math.max(res, max - curMin);
			res = Math.max(res, curMax - min);
			max = Math.max(max, curMax);
			min = Math.min(min, curMin);
		}
		return res;
	}
}
