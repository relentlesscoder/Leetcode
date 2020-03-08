package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 11/7/2016.
 * #356 https://leetcode.com/problems/line-reflection/
 */
public class LineReflection {
	// time O(n), space O(n)
	public boolean isReflected(int[][] points) {
		if (points == null || points.length == 0) {
			return true;
		}
		int n = points.length, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
		for (int i = 0; i < n; i++) {
			int val = points[i][0];
			min = val < min ? val : min;
			max = val > max ? val : max;
			map.putIfAbsent(val, new HashSet<>());
			map.get(val).add(points[i][1]);
		}
		// sum of min and max can be odd, e.g. 0 and 5
		double mid = 1.0 * (max + min) / 2;
		for (int i = 0; i < n; i++) {
			int x = points[i][0];
			// x1 - mid = mid - x2 if x1 and x2 is symmetric to mid
			int key = (int) (2 * mid - x);
			// x is symmetric and y is the same
			if (map.containsKey(key)
					&& map.get(key).contains(points[i][1])) {
				continue;
			}
			return false;
		}
		return true;
	}
}
