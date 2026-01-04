package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/18/2016.
 * #0346 https://leetcode.com/problems/moving-average-from-data-stream/
 */
public class MovingAverage {

	// time O(n), space O(m)
	class MovingAverageCircularArray {

		private int idx;
		private double sum;
		private final int[] arr;

		// 用循环数组来实现滑动窗口
		public MovingAverageCircularArray(int size) {
			this.idx = 0;
			this.sum = 0.0;
			this.arr = new int[size];
		}

		public double next(int val) {
			int n = arr.length, i = idx % n;
			sum -= arr[i];
			arr[i] = val;
			sum += arr[i];
			return sum / Math.min(++idx, n);
		}
	}

    // time O(n), space O(m)
    private static class MovingAverageQueue {

        private final int size;
        private double sum;
        private final Deque<Integer> queue;

		// 用队列来实现滑动窗口
        public MovingAverageQueue(int size) {
            this.size = size;
            this.sum = 0.0;
            this.queue = new ArrayDeque<>();
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

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
}
