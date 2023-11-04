package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/03/2023.
 * #2158 https://leetcode.com/problems/amount-of-new-area-painted-each-day/
 */
public class AmountOfNewAreaPaintedEachDay {

	// time O(m + n), space O(m + n)
	public int[] amountPainted(int[][] paint) {
		int[] res = new int[paint.length], lines = new int[50_001];
		for (int i = 0; i < paint.length; i++) {
			int start = paint[i][0], end = paint[i][1];
			while (start < end) {
				int jump = Math.max(start + 1, lines[start]); // determine the next step
				res[i] += lines[start] == 0 ? 1 : 0; // count the spot only if it is not painted yet
				lines[start] = Math.max(lines[start], end); // mark the spot using end time
				start = jump;
			}
		}
		return res;
	}

	// time O(n * log(n)), space O(n)
	public int[] amountPaintedTreeMap(int[][] paint) {
		int[] res = new int[paint.length];
		TreeMap<Integer, Integer> painted = new TreeMap<>();
		int i = 0;
		for (int[] p : paint) {
			int start = p[0], end = p[1];
			Integer startKey = painted.floorKey(p[0]), endKey = painted.floorKey(p[1]); // find the boundary of the overlap
			if (startKey != null && painted.get(startKey) >= p[0]) {
				start = startKey;
			}
			if (endKey != null && painted.get(endKey) >= p[1]) {
				end = painted.get(endKey);
			}
			int length = end - start;
			SortedMap<Integer, Integer> subMap = painted.subMap(start, end); // get all area that already painted
			List<Integer> keysToRemove = new ArrayList<>();
			for (Map.Entry<Integer, Integer> entry : subMap.entrySet()) {
				length -= entry.getValue() - entry.getKey(); // do not paint the same area twice
				keysToRemove.add(entry.getKey());
			}
			for (int key : keysToRemove) {
				painted.remove(key);
			}
			painted.put(start, end);
			res[i++] = length;
		}
		return res;
	}
}
