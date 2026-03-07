package org.wshuai.leetcode.stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 01/23/2016.
 * #0225 https://leetcode.com/problems/implement-stack-using-queues/
 */
public class ImplementStackUsingQueues {

	// time O(n^2), space O(n)
	private static class MyStack {

		private final Deque<Integer> queue;

		public MyStack() {
			queue = new ArrayDeque<>();
		}

		public void push(int x) {
			int size = queue.size();
			// 将新元素加到队尾
			queue.offer(x);
			// 依次将旧元素从队首加到队尾
			while (size-- > 0) {
				queue.offer(queue.poll());
			}
		}

		public int pop() {
			// 弹出队首元素
			return queue.poll();
		}

		public int top() {
			// 查看队首元素
			return queue.peek();
		}

		public boolean empty() {
			return queue.isEmpty();
		}
	}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
}
