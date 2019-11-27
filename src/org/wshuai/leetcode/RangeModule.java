package org.wshuai.leetcode;

import java.util.TreeMap;

/**
 * Created by Wei on 11/27/19.
 * #715 https://leetcode.com/problems/range-module/
 */
public class RangeModule {
	private TreeMap<Integer, Integer> intervals;

	public RangeModule() {
		intervals = new TreeMap<>();
	}

	public void addRange(int left, int right) {
		//greatest key less than or equal to left
		Integer start = intervals.floorKey(left);
		//greatest key less than or equal to right
		Integer end = intervals.floorKey(right);
		//if the end time of last interval
		//greater than or equals to left
		//extend the range to the left
		if(start != null && intervals.get(start) >= left){
			left = start;
		}
		//if the end time of last interval
		//greater than or equals to right
		//extend the range to the right
		if(end != null && intervals.get(end) > right){
			right = intervals.get(end);
		}
		//remove all existing ranges within the current range
		intervals.subMap(left, true, right, true).clear();
		//insert the new range
		intervals.put(left, right);
	}

	public boolean queryRange(int left, int right) {
		Integer start = intervals.floorKey(left);
		if(start == null){
			return false;
		}
		//check if the range covers the input
		return intervals.get(start) >= right;
	}

	public void removeRange(int left, int right) {
		Integer start = intervals.floorKey(left);
		Integer end = intervals.floorKey(right);
		//cut the first right range overlapped
		if(end != null && intervals.get(end) > right){
			intervals.put(right, intervals.get(end));
		}
		//cut the last left range overlapped
		if(start != null && intervals.get(start) > left){
			intervals.put(start, left);
		}
		//remove all the ranges within
		intervals.subMap(left, true, right, false).clear();
	}
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */
