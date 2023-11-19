package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 09/23/2023.
 * #2406 https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/
 */
public class DivideIntervalsIntoMinimumNumberOfGroups {

	// time O(n * log(n)), space O(n)
	public int minGroups(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
		PriorityQueue<Integer> minQueue = new PriorityQueue<>();
		for (int[] interval : intervals) {
			if (!minQueue.isEmpty() && minQueue.peek() < interval[0]) {
				minQueue.poll();
			}
			minQueue.offer(interval[1]);
		}
		return minQueue.size();
	}
}
