package org.wshuai.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Wei on 8/8/19.
 * #933 https://leetcode.com/problems/number-of-recent-calls/
 */
public class NumberOfRecentCalls {
	Queue<Integer> queue;

	public NumberOfRecentCalls() {
		queue = new LinkedList<Integer>();
	}

	public int ping(int t) {
		queue.add(t);
		while (queue.peek() < t - 3000) {
			queue.poll();
		}
		return queue.size();
	}
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */