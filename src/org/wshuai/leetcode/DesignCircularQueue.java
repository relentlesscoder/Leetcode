package org.wshuai.leetcode;

/**
 * Created by Wei on 09/27/2019.
 * #0622 https://leetcode.com/problems/design-circular-queue/
 */
public class DesignCircularQueue {

    // time O(n), space O(k)
    private static class MyCircularQueue {

        private int k;
        private int head;
        private int tail;
        private int size;
        private final int[] nums;

        public MyCircularQueue(int k) {
            this.k = k;
            head = 0;
            tail = -1;
            size = 0;
            nums = new int[k];
        }

        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }
            tail = (tail + 1) % k;
            nums[tail] = value;
            size++;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }
            head = (head + 1) % k;
            size--;
            return true;
        }

        public int Front() {
            if (isEmpty()) {
                return -1;
            }
            return nums[head];
        }

        public int Rear() {
            if (isEmpty()) {
                return -1;
            }
            return nums[tail];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == k;
        }
    }

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
}
