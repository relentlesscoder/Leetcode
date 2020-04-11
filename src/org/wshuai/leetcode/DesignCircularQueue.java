package org.wshuai.leetcode;

/**
 * Created by Wei on 09/27/2019.
 * #0622 https://leetcode.com/problems/design-circular-queue/
 */
public class DesignCircularQueue {

	private final int[] data;
	private int front = 0, rear = -1, len = 0;

	/** Initialize your data structure here. Set the size of the queue to be k. */
	public DesignCircularQueue(int k) {
		data = new int[k];
	}

	/** Insert an element into the circular queue. Return true if the operation is successful. */
	public boolean enQueue(int value) {
		if(!isFull()){
			rear = (rear + 1) % data.length;
			data[rear] = value;
			len++;
			return true;
		}
		return false;
	}

	/** Delete an element from the circular queue. Return true if the operation is successful. */
	public boolean deQueue() {
		if(!isEmpty()){
			front = (front + 1) % data.length;
			len--;
			return true;
		}
		return false;
	}

	/** Get the front item from the queue. */
	public int Front() {
		return isEmpty() ? -1 : data[front];
	}

	/** Get the last item from the queue. */
	public int Rear() {
		return isEmpty() ? -1 : data[rear];
	}

	/** Checks whether the circular queue is empty or not. */
	public boolean isEmpty() {
		return len == 0;
	}

	/** Checks whether the circular queue is full or not. */
	public boolean isFull() {
		return len == data.length;
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
