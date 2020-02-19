package org.wshuai.leetcode;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Created by Wei on 09/27/2016.
 * #0379 https://leetcode.com/problems/design-phone-directory/
 */
public class DesignPhoneDirectory {
	private int max;
	private LinkedList<Integer> queue;
	private boolean[] used;

	/** Initialize your data structure here
	 @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
	public DesignPhoneDirectory(int maxNumbers) {
		if(maxNumbers <= 0){
			throw new IllegalArgumentException("Invalid input.");
		}
		queue = new LinkedList<Integer>();
		for(int i = 0; i < maxNumbers; i++){
			queue.offerLast(i);
		}
		used = new boolean[maxNumbers];
		max = maxNumbers - 1;
	}

	/** Provide a number which is not assigned to anyone.
	 @return - Return an available number. Return -1 if none is available. */
	public int get() {
		if(queue.isEmpty()){
			throw new NoSuchElementException("Empty list.");
		}
		int val = queue.pollFirst();
		used[val] = true;
		return val;
	}

	/** Check if a number is available or not. */
	public boolean check(int number) {
		return  number <= max && !used[number];
	}

	/** Recycle or release a number. */
	public void release(int number) {
		if(number <= max && used[number]){
			queue.offerLast(number);
			used[number] = false;
		}
	}
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
