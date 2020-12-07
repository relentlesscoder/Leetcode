package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 11/10/2020.
 * #1642 https://leetcode.com/problems/furthest-building-you-can-reach/
 */
public class FurthestBuildingYouCanReach {

	// time O(n*log(n)), space O(l)
	public int furthestBuilding(int[] heights, int bricks, int ladders) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i = 0; i < heights.length - 1; i++){
			int diff = heights[i + 1] - heights[i];
			if(diff > 0){
				pq.offer(diff);
			}
			if(pq.size() > ladders){
				bricks -= pq.poll();
			}
			if(bricks < 0){
				return i;
			}
		}
		return heights.length - 1;
	}
}
