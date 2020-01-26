package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 10/28/2016.
 * #0239 https://leetcode.com/problems/sliding-window-maximum/
 */
public class SlidingWindowMaximum {
	// time O(n), space O(n)
	public int[] maxSlidingWindow(int[] nums, int k) {
		if(nums == null || nums.length == 0){
			return new int[0];
		}
		LinkedList<Integer> queue = new LinkedList<>();
		int n = nums.length;
		int[] res = new int[n - k + 1];
		for(int i = 0, j = 0; i < n; i++){
			// remove if the current max (head of the queue) is out of scope
			if(!queue.isEmpty() && i - queue.peekFirst() >= k){
				queue.pollFirst();
			}
			// the element at tail of the queen which is smaller than the
			// current element has no chance to become the maximum of current
			// and any subsequent sliding window so pop them all out.
			while(!queue.isEmpty() && nums[queue.peekLast()] < nums[i]){
				queue.pollLast();
			}
			queue.offerLast(i);
			// add the current max (head of the queue)
			if(i >= k - 1){
				res[j++] = nums[queue.peekFirst()];
			}
		}
		return res;
	}
}
