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
		// Same as #0084 with only difference that the valid rectangles
		// formed by indexes in [i,j] need to satisfy the constraint
		// that: i <= k and j >= k
		int res = 0, n = nums.length;
		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(-1); // Left sentinel -1
		for (int r = 0; r <= n; r++) {
			int h = r == n ? -1 : nums[r]; // Right sentinel n
			while (stack.size() > 1 && nums[stack.peek()] >= h) {
				int c = stack.pop();
				int l = stack.peek();
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
		// We start at index k and try to extend the subarray from either
		// left index i or right index j. Each time we pick the greater number
		// between i and j since picking the greater number is always better than
		// the smaller since we have higher chance to get a better score.
		int res = nums[k], n = nums.length, left = k, right = k, min = nums[k];
		while (left > 0 || right < n - 1) {
			if (left == 0) {
				right++; // If we used up numbers from left we extend the right
			} else if (right == n - 1) {
				left--; // If we used up numbers from right we extend the left
			} else if (nums[left - 1] > nums[right + 1]) {
				left--; // Pick the greater
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
