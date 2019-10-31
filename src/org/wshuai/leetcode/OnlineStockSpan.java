package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 10/30/2019.
 * #901 https://leetcode.com/problems/online-stock-span/
 */
public class OnlineStockSpan {
	Stack<int[]> stack;

	public OnlineStockSpan() {
		stack = new Stack<>();
	}

	public int next(int price) {
		int lessOrEqual = 1;
		while(!stack.isEmpty() && stack.peek()[0] <= price){
			lessOrEqual += stack.pop()[1];
		}
		stack.push(new int[]{price, lessOrEqual});
		return lessOrEqual;
	}
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
