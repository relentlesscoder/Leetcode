package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 02/18/2020.
 * #1353 https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/
 */
public class MaximumNumberOfEventsThatCanBeAttended {

	// time O(d + n * log(n)), space O(n)
	public int maxEvents(int[][] events) {
		int res = 0, n = events.length, j = 0;
		Arrays.sort(events, (a, b) -> a[0] - b[0]);
		PriorityQueue<Integer> minQueue = new PriorityQueue<>();
		for (int d = 1; d <= 100_000; d++) {
			while (!minQueue.isEmpty() && minQueue.peek() < d) { // remove all the events that are no longer available
				minQueue.poll();
			}
			while (j < n && events[j][0] == d) { // add all events that become available today
				minQueue.offer(events[j++][1]);
			}
			if (!minQueue.isEmpty()) { // attend one meeting with earliest ending date today
				minQueue.poll();
				res++;
			}
		}
		return res;
	}

}
