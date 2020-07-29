package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 07/28/2020.
 * #1499 https://leetcode.com/problems/max-value-of-equation/
 */
public class MaxValueOfEquation {

	// time O(n*log(n)), space O(n)
	public int findMaxValueOfEquation(int[][] points, int k) {
		// the top element of priority queue has the max (y - x)
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
		int res = Integer.MIN_VALUE;
		for(int[] point : points){
			// if the top is more than k away from current element, then
			// it is also more than k away from all following element
			while(!pq.isEmpty() && point[0] - pq.peek()[1] > k){
				pq.poll();
			}
			if(!pq.isEmpty()){
				res = Math.max(res, pq.peek()[0] + point[0] + point[1]);
			}
			pq.offer(new int[]{point[1] - point[0], point[0]});
		}
		return res;
	}
}
