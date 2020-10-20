package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 08/21/2019.
 * #0703 https://leetcode.com/problems/kth-largest-element-in-a-stream/
 */
public class KthLargestElementInAStream {

	private PriorityQueue<Integer> pq;

	private int k;

	// time O(n*log(k)), space O(k)
	public KthLargestElementInAStream(int k, int[] nums) {
		this.k = k;
		pq = new PriorityQueue<>();
		for(int num : nums){
			add(num);
		}
	}

	// time O(log(k))
	public int add(int val) {
		pq.offer(val);
		if(pq.size() > k){
			pq.poll();
		}
		return pq.size() == k ? pq.peek() : Integer.MIN_VALUE;
	}
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
