package org.wshuai.leetcode.stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 05/02/2020.
 * #1429 https://leetcode.com/problems/first-unique-number/
 */
public class FirstUniqueNumber {

	// time O(n), space O(m)
    private static class FirstUnique {

        private final Map<Integer, Integer> freq;
        private final Deque<Integer> queue;

        public FirstUnique(int[] nums) {
            freq = new HashMap<>();
            queue = new ArrayDeque<>();
            for (int num : nums) {
				// 同样的数字只加入队列一次
                if (freq.merge(num, 1, Integer::sum) == 1) {
                    queue.offer(num);
                }
            }
        }

        public int showFirstUnique() {
			// 弹出队首的所有重复的数字
            while (!queue.isEmpty() && freq.get(queue.peek()) > 1) {
                queue.poll();
            }
            return queue.isEmpty() ? -1 : queue.peek();
        }

        public void add(int value) {
			// 只有当前数字不存在才加入
            if (freq.merge(value, 1, Integer::sum) == 1) {
                queue.offer(value);
            }
        }
    }

/**
 * Your FirstUnique object will be instantiated and called as such:
 * FirstUnique obj = new FirstUnique(nums);
 * int param_1 = obj.showFirstUnique();
 * obj.add(value);
 */
}
