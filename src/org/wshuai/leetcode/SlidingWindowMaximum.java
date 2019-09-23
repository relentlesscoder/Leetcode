package org.wshuai.leetcode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Created by Wei on 10/28/2016.
 * #239 https://leetcode.com/problems/sliding-window-maximum/
 */
public class SlidingWindowMaximum {

	//O(n) Double-ended queue, https://segmentfault.com/a/1190000003903509
	public int[] maxSlidingWindow(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return new int[0];
		}

		int len = nums.length;
		int[] res = new int[len - k + 1];
		LinkedList<Integer> deque = new LinkedList<Integer>();
		for (int i = 0; i < len; i++) {
			//Check if the head of the queue is the element going out of the window, remove it.
			if (!deque.isEmpty() && deque.peekFirst() == i - k) {
				deque.poll();
			}
			//Remove (from the tail) all the elements that are less than the next element
			//so that the queue is always in DESC order
			while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
				deque.removeLast();
			}
			deque.offer(i);
			if (i + 1 >= k) {
				res[i + 1 - k] = nums[deque.peekFirst()];
			}
		}
		return res;
	}

	//O(n*logk) Priority queue
	public int[] maxSlidingWindowPriorityQueue(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return new int[0];
		}

		int len = nums.length;
		int[] res = new int[len - k + 1];
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
		for (int i = 0; i < len; i++) {
			if (i + 1 >= k) {
				if (i + 1 != k) {
					int j = i - k;
					pq.remove(nums[j]);
				}
				pq.offer(nums[i]);
				res[i + 1 - k] = pq.peek();
			} else {
				pq.offer(nums[i]);
			}
		}
		return res;
	}
}
