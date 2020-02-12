package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 09/18/2016.
 * #0346 https://leetcode.com/problems/moving-average-from-data-stream/
 */
public class MovingAverageFromDataStream {

	private LinkedList<Integer> queue;
	private int size;
	private double sum;

	/** Initialize your data structure here. */
	public MovingAverageFromDataStream(int size) {
		queue = new LinkedList<>();
		this.size = size;
		sum = 0;
	}

	// time O(1), space O(k)
	public double next(int val) {
		sum += val;
		if(queue.size() == size){
			sum -= queue.pollFirst();
		}
		queue.offerLast(val);
		return sum / queue.size();
	}
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
