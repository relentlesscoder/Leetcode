package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 06/14/2020.
 * #1475 https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/
 */
public class FinalPricesWithASpecialDiscountInAShop {

	// time O(n), space O(n)
	public int[] finalPricesLTR(int[] prices) {
		int n = prices.length;
		int[] res = new int[n];
		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(-1); // Sentinel
		for (int i = 0; i < n; i++) {
			int p = prices[i];
			res[i] = p;
			while (stack.size() > 1 && prices[stack.peek()] >= p) {
				res[stack.pop()] -= p;
			}
			stack.push(i);
		}
		return res;
	}

	// time O(n), space O(n)
	public int[] finalPricesRTL(int[] prices) {
		int n = prices.length;
		int[] res = new int[n];
		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(n);
		for (int i = n - 1; i >= 0; i--) {
			int p = prices[i];
			res[i] = p;
			while (stack.size() > 1 && prices[stack.peek()] > p) {
				stack.pop();
			}
			res[i] -= stack.peek() == n ? 0 : prices[stack.peek()];
			stack.push(i);
		}
		return res;
	}
}
