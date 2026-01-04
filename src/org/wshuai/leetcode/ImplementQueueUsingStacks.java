package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 01/25/2020.
 * #0232 https://leetcode.com/problems/implement-queue-using-stacks/
 */
public class ImplementQueueUsingStacks {
	class MyQueue {

		private final Deque<Integer> stack1; // 主栈
		private final Deque<Integer> stack2; // 副栈

		public MyQueue() {
			stack1 = new ArrayDeque<>();
			stack2 = new ArrayDeque<>();
		}

		public void push(int x) {
			// 将主栈的元素依次押入副栈中，则原栈底元素在栈顶而原栈顶元素在栈底 (顺序翻转)。
			while (!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
			// 把新元素加到副栈的栈顶。
			stack2.push(x);
			// 将副栈的元素依次押回主栈中，则新元素来到栈底。
			while (!stack2.isEmpty()) {
				stack1.push(stack2.pop());
			}
		}

		public int pop() {
			return stack1.pop();
		}

		public int peek() {
			return stack1.peek();
		}

		public boolean empty() {
			return stack1.isEmpty();
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
}
