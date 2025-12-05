package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 10/26/2016.
 * #0155 https://leetcode.com/problems/min-stack/
 */
public class MinStack {

    // time O(n), space O(n)
    private static class MinStackImpl {

        private Deque<int[]> stack;

        public MinStackImpl() {
            stack = new ArrayDeque<>();
        }

        public void push(int val) {
            if (stack.isEmpty()) {
                stack.push(new int[]{val, val});
            } else {
                stack.push(new int[]{val, Math.min(stack.peek()[1], val)});
            }
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek()[0];
        }

        public int getMin() {
            return stack.peek()[1];
        }
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
