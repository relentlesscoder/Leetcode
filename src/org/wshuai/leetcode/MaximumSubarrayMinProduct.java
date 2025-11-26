package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/18/2023.
 * #1856 https://leetcode.com/problems/maximum-subarray-min-product/
 */
public class MaximumSubarrayMinProduct {
	private static final int MOD = (int)1e9 + 7;

	// time O(n), space O(n)
	public int maxSumMinProduct(int[] nums) {
		// Same idea as #0907
		long res = 0;
		int n = nums.length;
		long[] prefixSum = new long[n + 1];
		for (int i = 1; i <= n; i++) {
			prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
		}
		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(-1);
		for (int r = 0; r <= n; r++) {
			int val = r == n ? Integer.MIN_VALUE : nums[r];
			while (stack.size() > 1 && nums[stack.peek()] >= val) {
				int c = stack.pop();
				int l = stack.peek();
				res = Math.max(res, 1L * nums[c] * (prefixSum[r] - prefixSum[l + 1]));
			}
			stack.push(r);
		}
		return (int) (res % MOD);
	}
}
