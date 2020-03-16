package org.wshuai.leetcode;

/**
 * Created by Wei on 03/16/2020.
 * #1381 https://leetcode.com/problems/design-a-stack-with-increment-operation/
 */
public class DesignAStackWithIncrementOperation {
	private int[] data;
	private int index, capacity;

	public DesignAStackWithIncrementOperation(int maxSize) {
		data = new int[maxSize];
		index = 0;
		capacity = maxSize;
	}

	public void push(int x) {
		if(index < capacity){
			data[index++] = x;
		}
	}

	public int pop() {
		if(index == 0){
			return -1;
		}
		return data[--index];
	}

	public void increment(int k, int val) {
		for(int i = 0; i < Math.min(capacity, k); i++){
			data[i] += val;
		}
	}
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */
