package org.wshuai.leetcode.other;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 01/05/2026.
 * #LCR184 https://leetcode.cn/problems/dui-lie-de-zui-da-zhi-lcof/
 */
public class LCR184 {

    // time O(n), space O(n)
    private static class Checkout {

        private int id;
        private final Deque<int[]> itemQueue;
        private final Deque<int[]> maxQueue;

        // 双队列
        public Checkout() {
            id = 0; // 用来标记商品
            // 所有商品队列
            itemQueue = new ArrayDeque<>();
            // 单调递减的最贵商品队列
            maxQueue = new ArrayDeque<>();
        }

        public int get_max() {
            if (itemQueue.isEmpty()) {
                return -1;
            }
            // 队首即为最大值
            return maxQueue.peek()[1];
        }

        public void add(int value) {
            int[] item = new int[]{id++, value};
            itemQueue.offer(item);
            // 去掉队尾价格小于等于 value 的商品因为它们不可能为当前或者以后的商品的最大值
            while (!maxQueue.isEmpty() && maxQueue.peekLast()[1] <= value) {
                maxQueue.pollLast();
            }
            maxQueue.offer(item);
        }

        public int remove() {
            if (itemQueue.isEmpty()) {
                return -1;
            }
            int[] head = itemQueue.poll();
            // 从单调队列中去掉当前商品
            if (!maxQueue.isEmpty() && maxQueue.peek()[0] == head[0]) {
                maxQueue.poll();
            }
            return head[1];
        }
    }

/**
 * Your Checkout object will be instantiated and called as such:
 * Checkout obj = new Checkout();
 * int param_1 = obj.get_max();
 * obj.add(value);
 * int param_3 = obj.remove();
 */
}
