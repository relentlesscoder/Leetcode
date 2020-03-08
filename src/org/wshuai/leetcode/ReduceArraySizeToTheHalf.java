package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by Wei on 02/03/2020.
 * #1342 https://leetcode.com/problems/reduce-array-size-to-the-half/
 */
public class ReduceArraySizeToTheHalf {
	// time O(k*log(k)), space O(n)
	public int minSetSize(int[] arr) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int a : arr){
			map.put(a, map.getOrDefault(a, 0) + 1);
		}
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
		int n = arr.length / 2, size = 0, res = 0;
		for(int v : map.values()){
			pq.offer(v);
		}
		while(size < n){
			size += pq.poll();
			res++;
		}
		return res;
	}
}
