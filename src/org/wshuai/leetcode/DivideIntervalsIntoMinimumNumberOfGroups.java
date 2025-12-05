package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Created by Wei on 09/23/2023.
 * #2406 https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/
 */
public class DivideIntervalsIntoMinimumNumberOfGroups {

	// time O(n), space O(m)
	public int minGroups(int[][] intervals) {
		int res = 0, groups = 0;
		int[] diff = new int[1000002];
		for (int[] interval : intervals) {
			diff[interval[0]]++;
			diff[interval[1] + 1]--;
		}
		for (int i = 0; i < 1000001; i++) {
			groups += diff[i];
			res = Math.max(res, groups);
		}
		return res;
	}

	// time O(n * log(n)), space O(n)
	public int minGroupsPriorityQueue(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
		PriorityQueue<Integer> minQueue = new PriorityQueue<>();
		for (int[] interval : intervals) {
			if (!minQueue.isEmpty() && minQueue.peek() < interval[0]) {
				minQueue.poll(); // at least we can find one interval that does not intersect with the current
			}
			minQueue.offer(interval[1]);
		}
		return minQueue.size();
	}

	// time O(n * log(n)), space O(n)
	public int minGroupsTreeMap(int[][] intervals) {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for (int[] in : intervals) {
			map.put(in[0], map.getOrDefault(in[0], 0) + 1);
			map.put(in[1] + 1, map.getOrDefault(in[1] + 1, 0) - 1);
		}
		int res = 0, cur = 0;
		for (int d : map.values()) {
			cur += d;
			res = Math.max(res, cur);
		}
		return res;
	}
}
