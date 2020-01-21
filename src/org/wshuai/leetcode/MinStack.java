package org.wshuai.leetcode;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by Wei on 10/26/2016.
 * #0155 https://leetcode.com/problems/min-stack/
 */
public class MinStack {
	// single stack solution -
	// https://leetcode.com/problems/min-stack/discuss/49014/Java-accepted-solution-using-one-stack
	private Stack<Integer> stack;
	private Stack<Integer> mins;

	/** initialize your data structure here. */
	public MinStack() {
		stack = new Stack<>();
		mins = new Stack<>();
	}

	public void push(int x) {
		stack.push(x);
		mins.push(mins.isEmpty() ? x : Math.min(x, mins.peek()));
	}

	public void pop() {
		if(stack.isEmpty()){
			return;
		}
		stack.pop();
		mins.pop();
	}

	public int top() throws EmptyStackException {
		return stack.peek();
	}

	public int getMin() throws EmptyStackException {
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
