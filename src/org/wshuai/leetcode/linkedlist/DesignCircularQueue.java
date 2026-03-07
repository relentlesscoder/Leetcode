package org.wshuai.leetcode.linkedlist;

/**
 * Created by Wei on 09/27/2019.
 * #0622 https://leetcode.com/problems/design-circular-queue/
 */
public class DesignCircularQueue {

    // time O(n), space O(k)
    private static class MyCircularQueue {

        private int front;
        private int rear;
        private final int capacity;
        private final int[] arr;

        // front指向第一个有效数据的位置，而rear指向最后一个有效数据的后面一个位置即下一个
        // 插入数据的位置。
        public MyCircularQueue(int k) {
            capacity = k + 1;
            arr = new int[capacity];
            front = 0;
            rear = 0;
        }

        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }
            // 先赋值再更新索引
            arr[rear] = value;
            rear = (rear + 1) % capacity;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }
            front = (front + 1) % capacity;
            return true;
        }

        public int Front() {
            if (isEmpty()) {
                return -1;
            }
            return arr[front];
        }

        public int Rear() {
            if (isEmpty()) {
                return -1;
            }
            return arr[((rear - 1) % capacity + capacity) % capacity];
        }

        public boolean isEmpty() {
            return front == rear;
        }

        public boolean isFull() {
            return (rear + 1) % capacity == front;
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
