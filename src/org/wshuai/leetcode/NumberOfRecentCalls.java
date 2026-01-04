package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 08/08/2019.
 * #0933 https://leetcode.com/problems/number-of-recent-calls/
 */
public class NumberOfRecentCalls {

	// time O(n), space O(n)
    class RecentCounter {

        private final Deque<Integer> queue;

        public RecentCounter() {
            queue = new ArrayDeque<>();
        }

        public int ping(int t) {
            // 将 t 入队
            queue.offer(t);
            // 依次弹出队首不在范围中 (< t - 3000) 的数
            while (!queue.isEmpty() && queue.peek() < t - 3000) {
                queue.poll();
            }
            return queue.size();
        }
    }

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
}
