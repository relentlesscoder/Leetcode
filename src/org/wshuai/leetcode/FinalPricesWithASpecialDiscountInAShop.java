package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 06/14/2020.
 * #1475 https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/
 */
public class FinalPricesWithASpecialDiscountInAShop {

	// time O(n), space O(n)
	public int[] finalPrices(int[] prices) {
		int n = prices.length;
		int[] res = new int[n];
		Stack<Integer> stack = new Stack<>();
		for(int i = n - 1; i >= 0; i--){
			while(!stack.isEmpty() && stack.peek() > prices[i]){
				stack.pop();
			}
			res[i] = prices[i] - (stack.isEmpty() ? 0 : stack.peek());
			stack.push(prices[i]);
		}
		return res;
	}
}
