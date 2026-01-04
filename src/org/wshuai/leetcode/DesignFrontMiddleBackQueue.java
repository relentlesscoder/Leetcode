package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 12/24/2020.
 * #1670 https://leetcode.com/problems/design-front-middle-back-queue/
 */
public class DesignFrontMiddleBackQueue {

    // time O(n), space O(n)
    private static class FrontMiddleBackQueue {

        private final Deque<Integer> frontQueue;
        private final Deque<Integer> backQueue;

        // 使用两个队列分别实现存前一半和后一半数字。任何时候最多只允许前一半比后一半
        // 多 1 个数字。
        public FrontMiddleBackQueue() {
            frontQueue = new ArrayDeque<>();
            backQueue = new ArrayDeque<>();
        }

        // 将数字加入队首然后重新平衡
        public void pushFront(int val) {
            frontQueue.offerFirst(val);
            rebalance();
        }

        // 将数字加入中间，题目要求如果有两个中间位置选择较前的位置。需要处理两种情况:
        //   1. 前队列和后队列长度一样，将新数字直接加入到前队列的队尾。
        //   2. 前队列比后队列多一个数，则将前队列的队尾加入到后队列的队首再将新数字加
        //      入到前队列的队尾。
        public void pushMiddle(int val) {
            if (frontQueue.size() == backQueue.size() + 1) {
                backQueue.offerFirst(frontQueue.pollLast());
            }
            frontQueue.offer(val);
            rebalance();
        }

        // 将数字加入队尾然后重新平衡
        public void pushBack(int val) {
            backQueue.offer(val);
            rebalance();
        }

        // 将队首数字弹出然后重新平衡
        public int popFront() {
            if (frontQueue.isEmpty()) {
                return -1;
            }
            int res = frontQueue.poll();
            rebalance();
            return res;
        }

        // 将前队列队尾数字弹出然后重新平衡，注意前队列的队尾永远是"整个队列"的中间元素。
        public int popMiddle() {
            if (frontQueue.isEmpty()) {
                return -1;
            }
            int res = frontQueue.pollLast();
            rebalance();
            return res;
        }

        // 将队尾数字弹出然后重新平衡
        public int popBack() {
            if (frontQueue.isEmpty() && backQueue.isEmpty()) {
                return -1;
            }
            // 优先从后队列弹出数字
            int res = !backQueue.isEmpty() ?
                    backQueue.pollLast() : frontQueue.pollLast();
            rebalance();
            return res;
        }

        private void rebalance() {
            // 不允许后面元素比前面多
            while (backQueue.size() > frontQueue.size()) {
                frontQueue.offer(backQueue.pollFirst());
            }
            // 不允许前面元素比后面多两个
            while (frontQueue.size() > backQueue.size() + 1) {
                backQueue.offerFirst(frontQueue.pollLast());
            }
        }
    }

/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * FrontMiddleBackQueue obj = new FrontMiddleBackQueue();
 * obj.pushFront(val);
 * obj.pushMiddle(val);
 * obj.pushBack(val);
 * int param_4 = obj.popFront();
 * int param_5 = obj.popMiddle();
 * int param_6 = obj.popBack();
 */
}
