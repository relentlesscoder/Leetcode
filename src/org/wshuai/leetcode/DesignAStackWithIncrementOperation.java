package org.wshuai.leetcode;

/**
 * Created by Wei on 03/16/2020.
 * #1381 https://leetcode.com/problems/design-a-stack-with-increment-operation/
 */
public class DesignAStackWithIncrementOperation {

	// time O(n), space O(n)
	private static class CustomStackArrayWithLazyPropagation {

		private int curr;
		private int[][] stack;

		public CustomStackArrayWithLazyPropagation(int maxSize) {
			curr = -1;
			stack = new int[maxSize][2];
		}

		// time O(1)
		public void push(int x) {
			if (curr == stack.length - 1) {
				return;
			}
			stack[++curr] = new int[] {x, 0};
		}

		// time O(1)
		public int pop() {
			if (curr == -1) {
				return -1;
			}
			if (curr > 0) {
				// lazy propagation to add all applied increment values
				stack[curr - 1][1] += stack[curr][1];
			}
			return stack[curr][0] + stack[curr--][1];
		}

		// time O(1)
		public void increment(int k, int val) {
			int idx = Math.min(k - 1, curr);
			if (idx == -1) {
				return;
			}
			stack[idx][1] += val;
		}
	}

	private static class CustomStack {

		private int curr;
		private int[] stack;

		public CustomStack(int maxSize) {
			curr = -1;
			stack = new int[maxSize];
		}

		// time O(1)
		public void push(int x) {
			if (curr == stack.length - 1) {
				return;
			}
			stack[++curr] = x;
		}

		// time O(1)
		public int pop() {
			if (curr == -1) {
				return -1;
			}
			return stack[curr--];
		}

		// time O(k)
		public void increment(int k, int val) {
			for (int i = 0; i <= Math.min(k - 1, curr); i++) {
				stack[i] += val;
			}
		}
	}
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */
