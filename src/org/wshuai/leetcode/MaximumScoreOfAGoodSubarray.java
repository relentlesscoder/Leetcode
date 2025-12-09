package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 11/12/2023.
 * #1793 https://leetcode.com/problems/maximum-score-of-a-good-subarray/
 */
public class MaximumScoreOfAGoodSubarray {

	// time O(n), space O(n)
	public int maximumScoreMonotonicStack(int[] nums, int k) {
		// #0084的变形题，唯一的区别是最大矩形必须包含索引k。
		int res = 0, n = nums.length;
		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(-1); // Left sentinel -1
		for (int r = 0; r <= n; r++) {
			int h = r == n ? -1 : nums[r]; // Right sentinel n
			while (stack.size() > 1 && nums[stack.peek()] >= h) {
				int c = stack.pop();
				int l = stack.peek();
				// 只更新矩形最大值如果当前矩形包含索引k
				if (l < k && r > k) {
					res = Math.max(res, nums[c] * (r - l - 1));
				}
			}
			stack.push(r);
		}
		return res;
	}

    // time O(n), space O(1)
	public int maximumScoreGreedy(int[] nums, int k) {
		// 从索引k开始，向左右两边扩张子数组。每次我们都找到两边数字的较大值来扩张，这样
		// 才有可能通过增加宽度将当前矩形的面积扩大。
		int res = nums[k], n = nums.length, left = k, right = k, min = nums[k];
		while (left > 0 || right < n - 1) {
			if (left == 0) {
				right++; // 左边的数字已用完
			} else if (right == n - 1) {
				left--; // 右边的数字已用完
			} else if (nums[left - 1] > nums[right + 1]) {
				left--; // 选较大值
			} else {
				right++;
			}
			// Update current min for the new subarray
			min = Math.min(min, Math.min(nums[left], nums[right]));
			// Update the max score
			res = Math.max(res, min * (right - left + 1));
		}
		return res;
	}
}
