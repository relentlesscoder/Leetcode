package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 10/26/2016.
 * #0155 https://leetcode.com/problems/min-stack/
 */
public class MinStack {

	private Stack<Integer> data;

	private Stack<Integer> mins;

	/** initialize your data structure here. */
	public MinStack() {
		data = new Stack<>();
		mins = new Stack<>();
	}

	public void push(int x) {
		data.push(x);
		mins.push(Math.min(mins.isEmpty() ?
			Integer.MAX_VALUE : mins.peek(), x));
	}

	public void pop() {
		data.pop();
		mins.pop();
	}

	public int top() {
		return data.peek();
	}

	public int getMin() {
		return mins.peek();
	}
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
