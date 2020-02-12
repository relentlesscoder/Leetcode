package org.wshuai.leetcode;

import java.util.TreeMap;

/**
 * Created by Wei on 08/26/2019.
 * #0352 https://leetcode.com/problems/data-stream-as-disjoint-intervals/
 */
public class DataStreamAsDisjointIntervals {
	private TreeMap<Integer, int[]> map;

	/** Initialize your data structure here. */
	public DataStreamAsDisjointIntervals() {
		map = new TreeMap<>();
	}

	// time O(log(n))
	public void addNum(int val) {
		// val exists in the map
		if(map.containsKey(val)){
			return;
		}
		Integer lower = map.lowerKey(val), higher = map.higherKey(val);
		// val exists in the range of [lower, map.get(lower)]
		if(lower != null && val <= map.get(lower)[1]){
			return;
		}
		// map.get(lower) + 1 = val = higher - 1
		// merge the two intervals
		if(lower != null && higher != null && map.get(lower)[1] + 1 == val && val + 1 == higher){
			map.get(lower)[1] = map.get(higher)[1];
			map.remove(higher);
		// map.get(lower) + 1 = val
		// merge val to the left interval
		}else if(lower != null && map.get(lower)[1] + 1 == val){
			map.get(lower)[1] = val;
		// val = higher - 1
		// merge val to the right interval
		}else if(higher != null && val + 1 == higher){
			map.put(val, new int[]{val, map.get(higher)[1]});
			map.remove(higher);
		// add val as an new interval
		}else{
			map.put(val, new int[]{val, val});
		}
	}

	// time O(n)
	public int[][] getIntervals() {
		return map.values().toArray(new int[map.size()][2]);
	}
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */
