package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 9/10/2019.
 * #1063 https://leetcode.com/problems/number-of-valid-subarrays/
 */
public class NumberOfValidSubarrays {

	// O(n)
	public int validSubarrays(int[] nums) {
		Deque<Integer> stack = new ArrayDeque<>();
		int n = nums.length;
		int res = 0;
		for (int i = n - 1; i >= 0; i--) {
			// using stack to find the index of the next smaller value
			while (!stack.isEmpty() && nums[i] <= nums[stack.peek()]) {
				stack.pop();
			}
			res += (stack.isEmpty() ? n : stack.peek() - i);
			stack.push(i);
		}
		return res;
	}

	// O(n^2)
	public int validSubarraysIntuitive(int[] nums) {
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			int j = i + 1;
			// find the index of the next smaller value
			while (j < nums.length && nums[j] >= nums[i]) {
				j++;
			}
			count += (j - i);
		}
		return count;
	}
}
