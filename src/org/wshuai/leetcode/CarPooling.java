package org.wshuai.leetcode;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Wei on 9/30/2019.
 * #1094 https://leetcode.com/problems/car-pooling/
 */
public class CarPooling {

	// time: O(n) of trips; space: O(m) of locations
	public boolean carPooling(int[][] trips, int capacity) {
		if(trips.length == 0){
			return true;
		}
		int[] count = new int[1_001];
		for(int[] trip: trips){
			for(int i = trip[1]; i < trip[2]; i++){
				count[i] += trip[0];
				if(count[i] > capacity){
					return false;
				}
			}
		}
		return true;
	}

	// time: O(n*log(n)) of trips; space: O(n) of trips
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
