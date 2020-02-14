package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 10/31/2016.
 * #0362 https://leetcode.com/problems/design-hit-counter/
 */
public class DesignHitCounter {
	private int count;
	private LinkedList<int[]> queue;

	/** Initialize your data structure here. */
	public DesignHitCounter() {
		count = 0;
		queue = new LinkedList<int[]>();
	}

	/** Record a hit.
	 @param timestamp - The current timestamp (in seconds granularity). */
	public void hit(int timestamp) {
		count++;
		if(!queue.isEmpty() && queue.peekLast()[0] == timestamp){
			queue.peekLast()[1]++;
		}else{
			queue.offerLast(new int[]{timestamp, 1});
		}
	}

	/** Return the number of hits in the past 5 minutes.
	 @param timestamp - The current timestamp (in seconds granularity). */
	public int getHits(int timestamp) {
		while(!queue.isEmpty() && timestamp - queue.peekFirst()[0] >= 300){
			count -= queue.pollFirst()[1];
		}
		return count;
	}
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
