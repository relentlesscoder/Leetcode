package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 9/19/19.
 * #1167 https://leetcode.com/problems/minimum-cost-to-connect-sticks/
 */
public class MinimumCostToConnectSticks {
	// be greedy to make sure each time every two sticks that cost minimum are connected
	public int connectSticks(int[] sticks) {
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for(int v: sticks){
			queue.offer(v);
		}
		int cost = 0;
		while(queue.size() > 1){
			int x = queue.poll();
			int y = queue.poll();
			int c = x + y;
			cost += c;
			queue.offer(c);
		}
		return cost;
	}
}
