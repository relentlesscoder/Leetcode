package org.wshuai.leetcode;

/**
 * Created by Wei on 10/29/2019.
 * #0641 https://leetcode.com/problems/design-circular-deque/
 */
public class DesignCircularDeque {

    private static class MyCircularDeque {

        private final int capacity;
        private final int[] arr;
        private int front;
        private int rear;

        // front指向第一个有效数据的位置，而rear指向最后一个有效数据的后面一个位置即下一个
        // 插入数据的位置。
        public MyCircularDeque(int k) {
            capacity = k + 1;
            arr = new int[capacity];
            front = 0;
            rear = 0;
        }

        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }
            // 先更新索引后赋值
            front = ((front - 1) % capacity + capacity) % capacity;
            arr[front] = value;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }
            // 先赋值后更新索引
            arr[rear] = value;
            rear = (rear + 1) % capacity;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }
            // front 被设计在数组的开头，所以是 +1
            front = (front + 1) % capacity;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }
            // rear 被设计在数组的末尾，所以是 -1
            rear = ((rear - 1) % capacity + capacity) % capacity;
            return true;
        }

        public int getFront() {
            if (isEmpty()) {
                return -1;
            }
            return arr[front];
        }

        public int getRear() {
            if (isEmpty()) {
                return -1;
            }
            // 当 rear 为 0 时防止数组越界
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
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
}
