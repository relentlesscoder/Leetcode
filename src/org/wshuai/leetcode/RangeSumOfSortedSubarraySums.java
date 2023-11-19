package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 07/12/2020.
 * #1508 https://leetcode.com/problems/range-sum-of-sorted-subarray-sums/
 */
public class RangeSumOfSortedSubarraySums {

	// time O(left*log(n)), space O(n)
	public int rangeSum(int[] nums, int n, int left, int right) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		for(int i = 0; i < n; i++){
			pq.offer(new int[]{nums[i], i + 1});
		}
		int[] temp = new int[right];
		int res = 0, k = 0;
		while(k < right){
			int[] cur = pq.poll();
			temp[k++] = cur[0];
			if(cur[1] < n){
				pq.offer(new int[]{cur[0] + nums[cur[1]], cur[1] + 1});
			}
		}
		for(int i = right - 1; i >= left - 1; i--){
			res += temp[i];
		}
		return res;
	}
}
