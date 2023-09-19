package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 09/18/2023.
 * #1856 https://leetcode.com/problems/maximum-subarray-min-product/
 */
public class MaximumSubarrayMinProduct {

	private static final long MOD = 1_000_000_007;

	// time O(n), space O(n)
	public int maxSumMinProduct(int[] nums) {
		long res = 0L;
		int n = nums.length;
		int[] leftBound = new int[n], rightBound = new int[n];
		Stack<Integer> stack = new Stack<>(); // same idea as #2281 https://leetcode.com/problems/sum-of-total-strength-of-wizards/
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
				stack.pop();
			}
			leftBound[i] = stack.isEmpty() ? 0 : stack.peek() + 1;
			stack.add(i);
		}
		stack.clear();
		for (int i = n - 1; i >= 0; i--) {
			while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
				stack.pop();
			}
			rightBound[i] = stack.isEmpty() ? n - 1 : stack.peek() - 1;
			stack.add(i);
		}
		long[] prefixSum = new long[n + 1];
		for (int i = 1; i <= n; i++) {
			prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
		}
		for (int i = 0; i < n; i++) {
			res = Math.max(res, (prefixSum[rightBound[i] + 1] - prefixSum[leftBound[i]]) * nums[i]);
		}
		return (int) (res % MOD);
	}

	// time O(n), space O(n)
	public int maxSumMinProductOnePass(int[] nums) {
		long res = Integer.MIN_VALUE;
		int n = nums.length;
		long[] prefixSum = new long[n + 1];
		for (int i = 1; i <= n; i++) {
			prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
		}
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i <= n; i++) {
			// for each num j that is popped out from the stack, exclusively
			// the left boundary is the top of the stack and the right boundary is i
			// because the monotonic stack is non-decreasing
			while (!stack.isEmpty() && (i == n || nums[stack.peek()] > nums[i])) {
				int j = stack.pop();
				res = Math.max(res, nums[j] * (prefixSum[i] - prefixSum[stack.isEmpty() ? 0 : stack.peek() + 1]));
			}
			stack.push(i);
		}
		return (int)(res % MOD);
	}
}
