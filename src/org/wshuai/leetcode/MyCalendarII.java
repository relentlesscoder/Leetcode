package org.wshuai.leetcode;

import java.util.TreeMap;

/**
 * Created by Wei on 10/15/2019.
 * #731 https://leetcode.com/problems/my-calendar-ii/
 */
public class MyCalendarII {
	TreeMap<Integer, Integer> map;

	// same idea as #1094
	public MyCalendarII() {
		map = new TreeMap<>();
	}

	public boolean book(int start, int end) {
		map.put(start, map.getOrDefault(start, 0) + 1);
		map.put(end, map.getOrDefault(end, 0) - 1);

		int active = 0;
		for(int d: map.values()){
			active += d;
			if(active >= 3){
				map.put(start, map.getOrDefault(start, 0) - 1);
				map.put(end, map.getOrDefault(end, 0) + 1);
				if(map.get(start) == 0){
					map.remove(start);
				}
				return false;
			}
		}

		return true;
	}
}
