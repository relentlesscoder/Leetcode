package org.wshuai.leetcode;

/**
 * Created by Wei on 09/27/2019.
 * #0622 https://leetcode.com/problems/design-circular-queue/
 */
public class DesignCircularQueue {

	private int[] data;
	private int head = 0, tail = -1, size = 0, k;

	/** Initialize your data structure here. Set the size of the queue to be k. */
	public DesignCircularQueue(int k) {
		this.data = new int[k];
		this.k = k;
	}

	/** Insert an element into the circular queue. Return true if the operation is successful. */
	public boolean enQueue(int value) {
		if(isFull()){
			return false;
		}
		tail = (tail + 1) % k;
		data[tail] = value;
		size++;
		return true;
	}

	/** Delete an element from the circular queue. Return true if the operation is successful. */
	public boolean deQueue() {
		if(isEmpty()){
			return false;
		}
		head = (head + 1) % k;
		size--;
		return true;
	}

	/** Get the front item from the queue. */
	public int Front() {
		if(isEmpty()){
			return -1;
		}
		return data[head];
	}

	/** Get the last item from the queue. */
	public int Rear() {
		if(isEmpty()){
			return -1;
		}
		return data[tail];
	}

	/** Checks whether the circular queue is empty or not. */
	public boolean isEmpty() {
		return size == 0;
	}

	/** Checks whether the circular queue is full or not. */
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
