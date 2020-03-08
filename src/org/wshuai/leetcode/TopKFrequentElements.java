package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/02/2016.
 * #0347 https://leetcode.com/problems/top-k-frequent-elements/
 */
public class TopKFrequentElements {
	// time O(n*log(k)), space O(k)
	public List<Integer> topKFrequent(int[] nums, int k) {
		List<Integer> res = new ArrayList<>();
		if(nums == null || nums.length == 0 || k <= 0){
			return res;
		}
		Map<Integer, Integer> map = new HashMap<>();
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		for(int num : nums){
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		for(Map.Entry<Integer, Integer> entry : map.entrySet()){
			pq.offer(new int[]{entry.getKey(), entry.getValue()});
			if(pq.size() > k){
				pq.poll();
			}
		}
		while(!pq.isEmpty()){
			res.add(pq.poll()[0]);
		}
		Collections.reverse(res);
		return res;
	}
}
