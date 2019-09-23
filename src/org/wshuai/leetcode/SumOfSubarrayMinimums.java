package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 9/14/19.
 * #907 https://leetcode.com/problems/sum-of-subarray-minimums/
 */
public class SumOfSubarrayMinimums {
	public int sumSubarrayMins(int[] A) {
		int mod = 1_000_000_007;

		Stack<ValueCountPair> stack = new Stack();
		int ans = 0;
		// dot is the sum of minimum of all contiguous subarray that ends with index j
		// [1,7,5,2,4,9]
		// dot = 1*1,   sum = 1
		// dot = 1*1 + 1*7, sum = 1+8
		// dot = 1*1 + 1*7 - 1*7 + 5*2, sum = 1+8+11
		// ....
		int dot = 0;
		for (int j = 0; j < A.length; j++) {
			int count = 1;
			while (!stack.isEmpty() && stack.peek().val >= A[j]) {
				ValueCountPair node = stack.pop();
				count += node.count;
				dot -= node.val * node.count;
			}
			stack.push(new ValueCountPair(A[j], count));
			dot += A[j] * count;
			ans += dot;
			ans %= mod;
		}

		return ans;
	}
}

class ValueCountPair {
	int val;

	int count;

	public ValueCountPair(int v, int c) {
		val = v;
		count = c;
	}
}
