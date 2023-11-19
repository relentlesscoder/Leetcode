package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 11/08/2023.
 * #2863 https://leetcode.com/problems/maximum-length-of-semi-decreasing-subarrays/
 */
public class MaximumLengthOfSemiDecreasingSubarrays {

	// time O(n), space O(n)
	public int maxSubarrayLength(int[] nums) {
		// if subarray [i, j] is one of the longest subarray, then there will be no i' that nums[i'] >= nums[i] and no j' that nums[j'] <= nums[j].
		Deque<Integer> stack = new ArrayDeque<>();
		int n = nums.length, res = 0, max = Integer.MIN_VALUE;
		for (int i = n - 1; i >= 0; i--) { // create a monotonic increasing stack to save possible right boundary
			if (stack.isEmpty() || nums[i] < nums[stack.peek()]) {
				stack.push(i);
			}
		}
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && stack.peek() <= i) { // pop out invalid end index
				stack.pop();
			}
			if (nums[i] > max) { // if the current is the max from the left
				max = nums[i];
				while (!stack.isEmpty() && nums[stack.peek()] < max) { // try extend right boundary as far as possible
					res = Math.max(res, stack.peek() - i + 1);
					stack.pop();
				}
			}
		}
		return res;
	}
}
