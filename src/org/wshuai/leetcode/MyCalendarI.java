package org.wshuai.leetcode;

import java.util.TreeMap;

/**
 * Created by Wei on 09/19/2019.
 * #0729 https://leetcode.com/problems/my-calendar-i/
 */
public class MyCalendarI {
	private TreeMap<Integer, Integer> map;

	public MyCalendarI() {
		map = new TreeMap<>();
	}

	// time O(log(n)), space O(n)
	public boolean book(int start, int end) {
		Integer low = map.floorKey(start), high = map.higherKey(start);
		if((low != null && map.get(low) > start)
			|| (high != null && high.intValue() < end)){
			return false;
		}
		map.put(start, end);
		return true;
	}
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
