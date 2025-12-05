package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/02/2016.
 * #0347 https://leetcode.com/problems/top-k-frequent-elements/
 */
public class TopKFrequentElements {

	// time O(n*log(k)), space O(n + k)
	public int[] topKFrequent(int[] nums, int k) {
		if (k == nums.length) {
			return nums;
		}
		Map<Integer, Integer> freq = new HashMap<>();
		for (int num : nums) {
			freq.put(num, freq.getOrDefault(num, 0) + 1);
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		for (int key : freq.keySet()) {
			pq.offer(new int[]{key, freq.get(key)});
			if (pq.size() > k) {
				pq.poll();
			}
		}
		int[] res = new int[k];
		for (int i = 0; i < k; i++) {
			res[i] = pq.poll()[0];
		}
		return res;
	}
}
