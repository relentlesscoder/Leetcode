package org.wshuai.leetcode;

import java.util.TreeMap;

/**
 * Created by Wei on 9/19/19.
 * #729 https://leetcode.com/problems/my-calendar-i/
 */
public class MyCalendarI {
	private TreeMap<Integer, Integer> calendar;

	// In Java, TreeMap keeps elements sorted and supports fast insertion
	public MyCalendarI() {
		calendar = new TreeMap<>();
	}

	public boolean book(int start, int end) {
		Integer prev = calendar.floorKey(start);
		Integer next = calendar.ceilingKey(start);
		if ((prev == null || calendar.get(prev) <= start)
				&& (next == null || end <= next)) {
			calendar.put(start, end);
			return true;
		}
		return false;
	}
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
