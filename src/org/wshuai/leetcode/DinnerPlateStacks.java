package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Wei on 12/30/2019.
 * #1172 https://leetcode.com/problems/dinner-plate-stacks/
 */
public class DinnerPlateStacks {

	// time O(n * log(n)), space O(n)
    private static class DinnerPlates {

        private final int capacity;
        private final List<Deque<Integer>> stacks; // all stacks
        private final PriorityQueue<Integer> idx; // use min queue to store all non-full stack ids

        public DinnerPlates(int capacity) {
            this.capacity = capacity;
            this.stacks = new ArrayList<>();
            this.idx = new PriorityQueue<>();
        }

        public void push(int val) {
			// Clear the queue is all indexes are invalid
            if (!idx.isEmpty() && idx.peek() >= stacks.size()) {
                idx.clear();
            }
            if (idx.isEmpty()) { // All stacks are full or no stacks at all
                stacks.add(new ArrayDeque<>());
                stacks.get(stacks.size() - 1).push(val);
                if (capacity > 1) { // Add the new indexes to the queue if the stack is not full
                    idx.offer(stacks.size() - 1);
                }
            } else {
                stacks.get(idx.peek()).push(val);
                if (stacks.get(idx.peek()).size() == capacity) {
                    idx.poll(); // Remove the index from min queue if the stack is full
                }
            }
        }

        public int pop() {
            return popAtStack(stacks.size() - 1);
        }

        public int popAtStack(int index) {
            if (index < 0 || index >= stacks.size() || stacks.get(index).isEmpty()) {
                return -1;
            }
            Deque<Integer> stack = stacks.get(index);
            if (stack.size() == capacity) {
                idx.offer(index); // Find the leftmost non-full stack
            }
            int res = stack.pop();
            while (!stacks.isEmpty() && stacks.get(stacks.size() - 1).isEmpty()) {
                stacks.remove(stacks.size() - 1); // Remove all empty stacks on right
            }
            return res;
        }
    }
}

/**
 * Your DinnerPlates object will be instantiated and called as such:
 * DinnerPlates obj = new DinnerPlates(capacity);
 * obj.push(val);
 * int param_2 = obj.pop();
 * int param_3 = obj.popAtStack(index);
 */
