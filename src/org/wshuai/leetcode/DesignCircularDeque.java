package org.wshuai.leetcode;

/**
 * Created by Wei on 10/29/2019.
 * #0641 https://leetcode.com/problems/design-circular-deque/
 */
public class DesignCircularDeque {

	private int[] data;
	private int head, tail, k, len;

	/** Initialize your data structure here. Set the size of the deque to be k. */
	public DesignCircularDeque(int k) {
		data = new int[k];
		head = 0;
		tail = -1;
		this.k = k;
	}

	/** Adds an item at the front of Deque. Return true if the operation is successful. */
	public boolean insertFront(int value) {
		if(isFull()){
			return false;
		}
		head = (head - 1 + k) % k;
		data[head] = value;
		len++;
		if(len == 1){
			tail = head;
		}
		return true;
	}

	/** Adds an item at the rear of Deque. Return true if the operation is successful. */
	public boolean insertLast(int value) {
		if(isFull()){
			return false;
		}
		tail = (tail + 1) % k;
		data[tail] = value;
		len++;
		return true;
	}

	/** Deletes an item from the front of Deque. Return true if the operation is successful. */
	public boolean deleteFront() {
		if(isEmpty()){
			return false;
		}
		head = (head + 1) % k;
		len--;
		return true;
	}

	/** Deletes an item from the rear of Deque. Return true if the operation is successful. */
	public boolean deleteLast() {
		if(isEmpty()){
			return false;
		}
		tail = (tail - 1 + k) % k;
		len--;
		return true;
	}

	/** Get the front item from the deque. */
	public int getFront() {
		return isEmpty() ? -1 : data[head];
	}

	/** Get the last item from the deque. */
	public int getRear() {
		return isEmpty() ? -1 : data[tail];
	}

	/** Checks whether the circular deque is empty or not. */
	public boolean isEmpty() {
		return len == 0;
	}

	/** Checks whether the circular deque is full or not. */
	public boolean isFull() {
		return len == k;
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
