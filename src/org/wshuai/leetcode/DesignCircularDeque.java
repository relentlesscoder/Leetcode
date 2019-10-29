package org.wshuai.leetcode;

/**
 * Created by Wei on 10/29/2019.
 * #641 https://leetcode.com/problems/design-circular-deque/
 */
public class DesignCircularDeque {
	private int[] data;
	private int front;
	private int last;
	private int k;

	/** Initialize your data structure here. Set the size of the deque to be k. */
	public DesignCircularDeque(int k) {
		data = new int[k];
		front = -1;
		last = -1;
		this.k = k;
	}

	/** Adds an item at the front of Deque. Return true if the operation is successful. */
	public boolean insertFront(int value) {
		if(front == -1){
			data[0] = value;
			front = 0;
			last = 0;
			return true;
		}
		int index = front == 0 ? k - 1 : front - 1;
		if(index != last){
			front = index;
			data[front] = value;
			return true;
		}
		return false;
	}

	/** Adds an item at the rear of Deque. Return true if the operation is successful. */
	public boolean insertLast(int value) {
		if(last == -1){
			data[0] = value;
			front = 0;
			last = 0;
			return true;
		}
		int index = last == k - 1 ? 0 : last + 1;
		if(index != front){
			last = index;
			data[last] = value;
			return true;
		}
		return false;
	}

	/** Deletes an item from the front of Deque. Return true if the operation is successful. */
	public boolean deleteFront() {
		if(front == -1){
			return false;
		}
		if(front == last){
			front = -1;
			last = -1;
			return true;
		}
		front = front == k - 1 ? 0 : front + 1;
		return true;
	}

	/** Deletes an item from the rear of Deque. Return true if the operation is successful. */
	public boolean deleteLast() {
		if(last == -1){
			return false;
		}
		if(front == last){
			front = -1;
			last = -1;
			return true;
		}
		last = last == 0 ? k - 1 : last - 1;
		return true;
	}

	/** Get the front item from the deque. */
	public int getFront() {
		return front == -1 ? -1 : data[front];
	}

	/** Get the last item from the deque. */
	public int getRear() {
		return last == -1 ? -1 : data[last];
	}

	/** Checks whether the circular deque is empty or not. */
	public boolean isEmpty() {
		return front == -1;
	}

	/** Checks whether the circular deque is full or not. */
	public boolean isFull() {
		return (last == k - 1 ? 0 : last + 1) == front;
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
