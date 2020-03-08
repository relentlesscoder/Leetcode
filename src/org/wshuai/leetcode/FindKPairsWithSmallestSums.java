package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/04/2016.
 * #0373 https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
 */
public class FindKPairsWithSmallestSums {
	// time O(k*log(k))
	public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		List<List<Integer>> res = new ArrayList<>();
		if(nums1.length == 0 || nums2.length == 0 || k == 0){
			return res;
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] + a[1] - b[0] - b[1]);
		for(int i = 0; i < nums1.length && i < k; i++){
			pq.offer(new int[]{nums1[i], nums2[0], 0});
		}
		/*
		1,2    1,4   1,6
		7,2    7,4   7,6
		11,2  11,4  11,6
		 */
		while(k-- > 0 && !pq.isEmpty()){
			int[] cur = pq.poll();
			res.add(Arrays.asList(cur[0], cur[1]));
			if(cur[2] == nums2.length - 1){
				continue;
			}
			pq.offer(new int[]{cur[0], nums2[cur[2] + 1], cur[2] + 1});
		}
		return res;
	}
}
