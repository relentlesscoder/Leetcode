package org.wshuai.leetcode;

import java.util.TreeMap;

/**
 * Created by Wei on 11/27/2019.
 * #0732 https://leetcode.com/problems/my-calendar-iii/
 */
public class MyCalendarIII {
	private TreeMap<Integer, Integer> map;

	public MyCalendarIII() {
		map = new TreeMap<>();
	}

	// time O(log(n))
	public int book(int start, int end) {
		map.put(start, map.getOrDefault(start, 0) + 1);
		map.put(end, map.getOrDefault(end, 0) - 1);

		int res = 0, sum = 0;
		for(int v : map.values()){
			sum += v;
			res = Math.max(res, sum);
		}

		return res;
	}
}

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(start,end);
 */
