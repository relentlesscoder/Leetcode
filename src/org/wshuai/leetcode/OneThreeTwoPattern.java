package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 10/20/2019.
 * #0456 https://leetcode.com/problems/132-pattern/
 */
public class OneThreeTwoPattern {

    // time O(n), space O(n)
	public boolean find132pattern(int[] nums) {
		int n = nums.length;
		int[] leftMin = new int[n + 1];
		leftMin[0] = Integer.MAX_VALUE;
		// Find prefix min value for each index i
		for (int i = 1; i <= n; i++) {
			leftMin[i] = Math.min(leftMin[i - 1], nums[i - 1]);
		}
		Deque<Integer> stack = new ArrayDeque<>();
		for (int j = n - 1; j >= 0; j--) {
			// nums[j] > nums[i] where nums[i] is the minimum value on
			// nums[j]'s left
			if (nums[j] <= leftMin[j]) {
				continue;
			}
			// Pop out all values that are <= nums[i]
			while (!stack.isEmpty() && nums[stack.peek()] <= leftMin[j]) {
				stack.pop();
			}
			// The top of the stack now is the minimum nums[k] > nums[i]
			// on nums[j]'s right, check if it satisfies nums[k] < nums[j]
			if (!stack.isEmpty() && nums[stack.peek()] < nums[j]) {
				return true;
			}
			stack.push(j);
		}
		return false;
	}
}
