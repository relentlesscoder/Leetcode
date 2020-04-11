package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 12/16/2019.
 * #0632 https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
 */
public class SmallestRangeCoveringElementsFromKLists {
	// time O(n*log(k)), space O(k)
	public int[] smallestRange(List<List<Integer>> nums) {
		int start = -1, end = -1, range = Integer.MAX_VALUE, max = Integer.MIN_VALUE, k = nums.size();
		// maintain priority queue of size k and use a variable to track current maximum
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		for(int i = 0; i < k; i++){
			int val = nums.get(i).get(0);
			pq.offer(new int[]{i, 0, val});
			max = Math.max(max, val);
		}
		while(pq.size() == k){
			int[] cur = pq.poll();
			// each time, we poll the current minimum from the queue and calculate the current range
			if(max - cur[2] < range){
				range = max - cur[2];
				start = cur[2];
				end = max;
			}
			// advance the list contains the polled element by 1 and push back to the priority queue
			// this will always guarantee that the priority queue contains 1 element from each list and
			// the current max - head of the queue is the smallest range possible
			if(cur[1] + 1 < nums.get(cur[0]).size()){
				int[] next = new int[]{cur[0], ++cur[1], nums.get(cur[0]).get(cur[1])};
				pq.offer(next);
				max = Math.max(max, next[2]);
			}
		}
		return new int[]{start, end};
	}
}
