package org.wshuai.leetcode;

import java.util.TreeMap;

/**
 * Created by Wei on 11/27/19.
 * #732 https://leetcode.com/problems/my-calendar-iii/
 */
public class MyCalendarIII {
	private TreeMap<Integer, Integer> map;

	public MyCalendarIII() {
		map = new TreeMap<>();
	}

	public int book(int start, int end) {
		map.put(start, map.getOrDefault(start, 0) + 1);
		map.put(end, map.getOrDefault(end, 0) - 1);

		int res = 0;
		int event = 0;
		for(int v : map.values()){
			event += v;
			res = Math.max(res, event);
		}
		return res;
	}
}

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(start,end);
 */
