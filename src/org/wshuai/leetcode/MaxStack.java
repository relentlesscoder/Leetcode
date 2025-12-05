package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by Wei on 09/04/2019.
 * #0716 https://leetcode.com/problems/max-stack/
 */
public class MaxStack {

	// time O(n * log(n)), space O(n)
    private static class MaxStackImpl {

        private Deque<int[]> stack;
        private PriorityQueue<int[]> queue;
        private Set<Integer> removed;
        private int id;

        public MaxStackImpl() {
            id = 0;
            stack = new ArrayDeque<>();
            queue = new PriorityQueue<>((a, b) -> a[0] == b[0] ? b[1] - a[1] : b[0] - a[0]);
            removed = new HashSet<>();
        }

		// O(log(n))
        public void push(int x) {
            stack.push(new int[]{x, id});
            queue.offer(new int[]{x, id});
            id++;
        }

		// O(1)
        public int pop() {
            while (removed.contains(stack.peek()[1])) { // Lazy propagation
                stack.pop();
            }
            int[] top = stack.pop();
            removed.add(top[1]);
            return top[0];
        }

		// O(1)
        public int top() {
            while (removed.contains(stack.peek()[1])) { // Lazy propagation
                stack.pop();
            }
            return stack.peek()[0];
        }

		// O(log(n))
        public int peekMax() {
            while (removed.contains(queue.peek()[1])) { // Lazy propagation
                queue.poll();
            }
            return queue.peek()[0];
        }

		// O(log(n))
        public int popMax() {
            while (removed.contains(queue.peek()[1])) { // Lazy propagation
                queue.poll();
            }
            int[] top = queue.poll();
            removed.add(top[1]);
            return top[0];
        }
    }
}
