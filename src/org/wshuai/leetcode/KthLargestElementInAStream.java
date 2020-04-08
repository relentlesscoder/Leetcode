package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 08/21/2019.
 * #0703 https://leetcode.com/problems/kth-largest-element-in-a-stream/
 */
public class KthLargestElementInAStream {
	private PriorityQueue<Integer> queue;
	private int k;

	// time O(n*log(k)), space O(k)
	public KthLargestElementInAStream(int k, int[] nums) {
		queue = new PriorityQueue<>();
		this.k = k;
		for(int num : nums){
			add(num);
		}
	}

	public int add(int val) {
		queue.offer(val);
		if(queue.size() > k){
			queue.poll();
		}
		return queue.peek();
	}
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
