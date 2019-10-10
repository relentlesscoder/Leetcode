package org.wshuai.leetcode;

import java.util.TreeMap;

/**
 * Created by Wei on 8/26/19.
 * #352 https://leetcode.com/problems/data-stream-as-disjoint-intervals/
 */
public class DataStreamAsDisjointIntervals {
	TreeMap<Integer, int[]> map;

	/**
	 * Initialize your data structure here.
	 */
	public DataStreamAsDisjointIntervals() {
		map = new TreeMap<>();
	}

	// Log(N)
	public void addNum(int val) {
		if (map.containsKey(val)) {
			return;
		}
		Integer l = map.lowerKey(val);
		Integer h = map.higherKey(val);
		if (l != null && h != null && map.get(l)[1] + 1 == val && h == val + 1) {
			map.get(l)[1] = map.get(h)[1];
			map.remove(h);
		} else if (l != null && map.get(l)[1] + 1 >= val) {
			map.get(l)[1] = Math.max(map.get(l)[1], val);
		} else if (h != null && h == val + 1) {
			int[] in = new int[2];
			in[0] = val;
			in[1] = map.get(h)[1];
			map.put(val, in);
			map.remove(h);
		} else {
			int[] in = new int[2];
			in[0] = val;
			in[1] = val;
			map.put(val, in);
		}
	}

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
