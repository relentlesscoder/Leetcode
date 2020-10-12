package org.wshuai.leetcode;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Wei on 09/30/2019.
 * #1094 https://leetcode.com/problems/car-pooling/
 */
public class CarPooling {

	// time O(n*log(n)), space O(n)
	public boolean carPoolingHashMap(int[][] trips, int capacity) {
		Map<Integer, Integer> m = new TreeMap<>();
		for (int[] t : trips) {
			// record the delta of passengers at each time point
			m.put(t[1], m.getOrDefault(t[1], 0) + t[0]);
			m.put(t[2], m.getOrDefault(t[2], 0) - t[0]);
		}
		for (int v : m.values()) {
			// from the perspective of capacity,
			// picking up passengers decreases capacity
			// dropping passengers increases capacity
			capacity -= v;
			if (capacity < 0) {
				return false;
			}
		}
		return true;
	}
}
