package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 09/25/2023.
 * #1962 https://leetcode.com/problems/remove-stones-to-minimize-the-total/
 */
public class RemoveStonesToMinimizeTheTotal {

	// time O((n + k) * log(n)), space O(n)
	public int minStoneSum(int[] piles, int k) {
		int res = 0;
		PriorityQueue<Integer> maxQueue = new PriorityQueue<>((a, b) -> b - a);
		for (int p : piles) {
			maxQueue.offer(p);
		}
		while (k-- > 0 && maxQueue.peek() > 1) {
			int max = maxQueue.poll();
			maxQueue.offer(max - max / 2);
		}
		while (!maxQueue.isEmpty()) {
			res += maxQueue.poll();
		}
		return res;
	}
}
