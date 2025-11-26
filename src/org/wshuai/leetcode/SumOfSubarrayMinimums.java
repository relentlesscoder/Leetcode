package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by Wei on 09/14/2019.
 * #0907 https://leetcode.com/problems/sum-of-subarray-minimums/
 */
public class SumOfSubarrayMinimums {

	private static final int MOD = (int)1e9 + 7;

	// time O(n), space O(n)
	public int sumSubarrayMins(int[] arr) {
		// Same idea as #0084
		long res = 0;
		int n = arr.length;
		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(-1);
		for (int r = 0; r <= n; r++) {
			int val = r == n ? 0 : arr[r];
			while (stack.size() > 1 && arr[stack.peek()] >= val) {
				int c = stack.pop();
				int l = stack.peek();
				int lc = c - l, rc = r - c;
				res = (res + 1L * arr[c] * lc % MOD * rc % MOD) % MOD;
			}
			stack.push(r);
		}
		return (int) res;
	}

	// time O(n), space O(n)
	public int sumSubarrayMinsMonotonicStackOnePass(int[] arr) {
		long res = 0;
		int n = arr.length;
		int[] left = new int[n], right = new int[n];
		Arrays.fill(right, n);
		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(-1);
		for (int i = 0; i < n; i++) {
			while (stack.size() > 1 && arr[stack.peek()] >= arr[i]) {
				right[stack.pop()] = i;
			}
			left[i] = stack.peek();
			stack.push(i);
		}
		for (int i = 0; i < n; i++) {
			int l = i - left[i], r = right[i] - i;
			res = (res + 1L * arr[i] * l % MOD * r % MOD) % MOD;
		}
		return (int) res;
	}

	// time O(n), space O(n)
	public int sumSubarrayMinsMonotonicStackTwoPass(int[] arr) {
		// For each index i, find the first index j on left that satisfies
		// arr[j] < arr[i] and find the first index k on right that satisfies
		// arr[k] <= arr[i] (= for subarray deduplicate) and the subarray that
		// contains index i as minimum number is in exclusive range (j,k).
		// Each index i will contribute (i - j) * (k - i) * nums[i] to the final
		// result.
		long res = 0;
		int n = arr.length;
		int[] left = new int[n], right = new int[n];
		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(-1);
		for (int i = 0; i < n; i++) {
			while (stack.size() > 1 && arr[stack.peek()] >= arr[i]) {
				stack.pop();
			}
			left[i] = stack.peek();
			stack.push(i);
		}
		stack.clear();
		stack.push(n);
		for (int i = n - 1; i >= 0; i--) {
			while (stack.size() > 1 && arr[stack.peek()] > arr[i]) {
				stack.pop();
			}
			right[i] = stack.peek();
			stack.push(i);
		}
		for (int i = 0; i < n; i++) {
			int l = i - left[i], r = right[i] - i;
			res = (res + 1L * arr[i] * l % MOD * r % MOD) % MOD;
		}
		return (int) res;
	}
}
