package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 09/19/2019.
 * #1167 https://leetcode.com/problems/minimum-cost-to-connect-sticks/
 */
public class MinimumCostToConnectSticks {

	// time O(n*log(n)), space O(n)
	public int connectSticks(int[] sticks) {
		int res = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int s : sticks){
			pq.offer(s);
		}
		// since early added sticks will count repeatedly
		// in the following steps, we need to pick the
		// shortest two sticks to connect each time
		while(pq.size() > 1){
			int cost = pq.poll() + pq.poll();
			res += cost;
			pq.offer(cost);
		}
		return res;
	}
}
