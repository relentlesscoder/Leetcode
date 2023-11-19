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
		Map<Integer, Integer> map = new TreeMap<>();
		for (int[] t : trips) {
			// record the delta of passengers at each time point
			map.put(t[1], map.getOrDefault(t[1], 0) + t[0]);
			map.put(t[2], map.getOrDefault(t[2], 0) - t[0]);
		}
		int cur = 0;
		for(int key : map.keySet()){
			// from the perspective of capacity,
			// picking up passengers decreases capacity
			// dropping passengers increases capacity
			cur += map.get(key);
			if(capacity < cur){
				return false;
			}
		}
		return true;
	}
}
