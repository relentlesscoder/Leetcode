package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 12/6/2019.
 * #1199 https://leetcode.com/problems/minimum-time-to-build-blocks/
 */
public class MinimumTimeToBuildBlocks {
	public int minBuildTime(int[] blocks, int split) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int b : blocks){
			pq.offer(b);
		}
		while(pq.size() > 1){
			pq.poll();
			pq.offer(pq.poll() + split);
		}
		return pq.peek();
	}
}
