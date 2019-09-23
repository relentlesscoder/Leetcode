package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 8/21/19.
 * #703 https://leetcode.com/problems/kth-largest-element-in-a-stream/
 */
public class KthLargestElementInAStream {
	private PriorityQueue<Integer> queue;
	private int num;

	public KthLargestElementInAStream(int k, int[] nums) {
		this.num = k;
		queue = new PriorityQueue<>();
		for (int n : nums) {
			queue.offer(n);
		}
		while (queue.size() > k) {
			queue.poll();
		}
	}

	public int add(int val) {
		if (queue.size() == num && val > queue.peek()) {
			queue.offer(val);
			queue.poll();
		} else if (queue.size() < num) {
			queue.offer(val);
		}
		return queue.peek();
	}
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
