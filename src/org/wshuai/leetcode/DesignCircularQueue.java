package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 9/27/19.
 * #622 https://leetcode.com/problems/design-circular-queue/
 */
public class DesignCircularQueue {
	private int[] data;
	private int head;
	private int tail;
	private int N;

	/** Initialize your data structure here. Set the size of the queue to be k. */
	public DesignCircularQueue(int k) {
		data = new int[k];
		Arrays.fill(data, -1);
		head = -1;
		tail = -1;
		N = k;
	}

	/** Insert an element into the circular queue. Return true if the operation is successful. */
	public boolean enQueue(int value) {
		if(tail == -1){
			head = 0;
			tail = 0;
			data[0] = value;
			return true;
		}
		int t = (tail + 1) % N;
		if(data[t] != -1){
			return false;
		}
		data[t] = value;
		tail++;
		return true;
	}

	/** Delete an element from the circular queue. Return true if the operation is successful. */
	public boolean deQueue() {
		if(head == -1){
			return false;
		}
		data[head % N] = -1;
		if(head % N == tail % N){
			head = -1;
			tail = -1;
		}else{
			head++;
		}
		return true;
	}

	/** Get the front item from the queue. */
	public int Front() {
		if(head == -1){
			return -1;
		}
		return data[head % N];
	}

	/** Get the last item from the queue. */
	public int Rear() {
		if(tail == -1){
			return -1;
		}
		return data[tail % N];
	}

	/** Checks whether the circular queue is empty or not. */
	public boolean isEmpty() {
		return head == -1;
	}

	/** Checks whether the circular queue is full or not. */
	public boolean isFull() {
		int t = (tail + 1) % N;
		return data[t] != -1;
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
