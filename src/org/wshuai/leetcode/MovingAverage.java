package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/18/2016.
 * #0346 https://leetcode.com/problems/moving-average-from-data-stream/
 */
public class MovingAverage {

	// time O(m), space O(n)
	private class MovingAverageQueue {

		private int size;

		private double sum;

		private Deque<Integer> queue;

		public MovingAverageQueue(int size) {
			this.size = size;
			sum = 0.0;
			queue = new ArrayDeque<>();
		}

		public double next(int val) {
			sum += val;
			queue.offer(val);
			if (queue.size() > size) {
				sum -= queue.poll();
			}
			return sum / queue.size();
		}
	}

	// time O(m), space O(n)
	private class MovingAverageCircularArray {

		private int size, count, head;

		private double sum;

		private int[] queue;

		public MovingAverageCircularArray(int size) {
			this.size = size;
			count = 0;
			head = 0;
			sum = 0.0;
			queue = new int[size];
		}

		public double next(int val) {
			count++;
			int tail = (head + 1) % size;
			sum = sum - queue[tail] + val;
			head = (head + 1) % size;
			queue[head] = val;
			return sum / Math.min(size, count);
		}
	}
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
