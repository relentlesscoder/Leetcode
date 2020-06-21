package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by Wei on 10/16/2019.
 * #0846 https://leetcode.com/problems/hand-of-straights/
 */
public class HandOfStraights {

	// time O(n*log(n)), space O(n)
	public boolean isNStraightHand(int[] hand, int W) {
		int n = hand.length;
		if(n % W != 0){
			return false;
		}
		int count = n / W;
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		Map<Integer, Integer> map = new HashMap<>();
		for(int h : hand){
			map.put(h, map.getOrDefault(h, 0) + 1);
		}
		map.forEach((k, v) -> pq.offer(new int[]{k, v}));
		while(count-- > 0){
			int i = 0;
			int[][] temp = new int[W][2];
			while(!pq.isEmpty() && i < W){
				int[] cur = pq.poll();
				cur[1]--;
				// check if consecutive
				if(i != 0 && temp[i - 1][0] + 1 != cur[0]){
					return false;
				}
				temp[i++] = cur;
			}
			// check if W numbers can be found
			if(i != W){
				return false;
			}
			for(int[] t : temp){
				if(t[1] == 0){
					continue;
				}
				pq.offer(t);
			}
		}
		return true;
	}
}
