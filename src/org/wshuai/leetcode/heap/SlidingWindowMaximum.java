package org.wshuai.leetcode.heap;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 10/28/2016.
 * #0239 https://leetcode.com/problems/sliding-window-maximum/
 */
public class SlidingWindowMaximum {

    // time O(n), space O(n)
	public int[] maxSlidingWindow(int[] nums, int k) {
		// 维护一个单调递减的双端队列
		int n = nums.length;
		int[] res = new int[n - k + 1];
		Deque<Integer> queue = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			// 如果队尾的元素小于等于当前元素，则它们不可能是当前或者后续滑动窗口的最大值。
			while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]) {
				queue.pollLast();
			}
			// 加入当前索引
			queue.offer(i);
			int left = i - k + 1;
			if (left < 0) {
				continue;
			}
			// 更新当前索引的最大值为队首元素
			res[left] = nums[queue.peek()];
			// 为后续窗口将当前队首移除
			if (queue.peek() == left) {
				queue.poll();
			}
		}
		return res;
	}
}
