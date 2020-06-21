package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 09/04/2019.
 * #0716 https://leetcode.com/problems/max-stack/
 */
public class MaxStack {
	private Stack<Integer> nums, maxs;

	/** initialize your data structure here. */
	public MaxStack() {
		nums = new Stack<>();
		maxs = new Stack<>();
	}

	public void push(int x) {
		nums.push(x);
		maxs.push(maxs.isEmpty() ? x : Math.max(maxs.peek(), x));
	}

	public int pop() {
		maxs.pop();
		return nums.pop();
	}

	public int top() {
		return nums.peek();
	}

	public int peekMax() {
		return maxs.peek();
	}

	public int popMax() {
		Stack<Integer> temp = new Stack<>();
		int max = maxs.peek();
		while(top() != max){
			temp.push(pop());
		}
		pop();
		while(!temp.isEmpty()){
			push(temp.pop());
		}
		return max;
	}
}
