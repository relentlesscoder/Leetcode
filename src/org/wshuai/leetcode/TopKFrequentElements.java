package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/02/2016.
 * #0347 https://leetcode.com/problems/top-k-frequent-elements/
 */
public class TopKFrequentElements {

	// time O(n*log(k)), space O(k)
	public int[] topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> count = new HashMap<>();
		for(int num : nums){
			count.put(num, count.getOrDefault(num, 0) + 1);
		}
		PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		for(int key : count.keySet()){
			queue.offer(new int[]{key, count.get(key)});
			if(queue.size() > k){
				queue.poll();
			}
		}
		int[] res = new int[k];
		int i = 0;
		while(!queue.isEmpty()){
			res[i++] = queue.poll()[0];
		}
		return res;
	}
}
