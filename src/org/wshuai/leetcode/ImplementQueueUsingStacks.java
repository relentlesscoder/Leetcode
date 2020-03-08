package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 01/25/2020.
 * #0232 https://leetcode.com/problems/implement-queue-using-stacks/
 */
public class ImplementQueueUsingStacks {
	private Stack<Integer> cur, next;

	/** Initialize your data structure here. */
	public ImplementQueueUsingStacks() {
		cur = new Stack<>();
		next = new Stack<>();
	}

	/** Push element x to the back of queue. */
	public void push(int x) {
		while(!cur.isEmpty()){
			next.push(cur.pop());
		}
		next.push(x);
		while(!next.isEmpty()){
			cur.push(next.pop());
		}
	}

	/** Removes the element from in front of queue and returns that element. */
	public int pop() {
		return cur.pop();
	}

	/** Get the front element. */
	public int peek() {
		return cur.peek();
	}

	/** Returns whether the queue is empty. */
	public boolean empty() {
		return cur.isEmpty();
	}
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
