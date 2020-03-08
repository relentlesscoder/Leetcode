package org.wshuai.leetcode;

import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * Created by Wei on 07/17/2017.
 * #0480 https://leetcode.com/problems/sliding-window-median/
 */
public class SlidingWindowMedian {
	// time O(n*log(k)), space O(k)
	// run time : 26ms
	public double[] medianSlidingWindow(int[] nums, int k) {
		// need to save index to the queue to handle duplicates
		TreeSet<Integer> minQueue = new TreeSet<>((a, b) -> nums[a] != nums[b] ?
			Integer.compare(nums[a], nums[b]) : a - b);
		TreeSet<Integer> maxQueue = new TreeSet<>((a, b) -> nums[a] != nums[b] ?
			Integer.compare(nums[b], nums[a]) : b - a);
		int n = nums.length, j = 0;
		double[] res = new double[n - k + 1];
		for (int i = 0; i < n; i++) {
			if (i >= k) {
				int delete = i - k;
				if (minQueue.contains(delete)) {
					minQueue.remove(delete);
				} else {
					maxQueue.remove(delete);
				}
			}
			if (minQueue.size() == 0 || nums[i] >= nums[minQueue.first()]) {
				minQueue.add(i);
			} else {
				maxQueue.add(i);
			}
			rebalance(minQueue, maxQueue);
			if (i >= k - 1) {
				res[j++] = k % 2 == 0 ? ((double) nums[minQueue.first()]
					+ (double) nums[maxQueue.first()]) / 2.0 : (double) nums[minQueue.first()];
			}
		}
		return res;
	}

	private void rebalance(TreeSet<Integer> minQueue, TreeSet<Integer> maxQueue) {
		while (minQueue.size() > maxQueue.size() + 1) {
			int delete = minQueue.first();
			minQueue.remove(delete);
			maxQueue.add(delete);
		}
		while (maxQueue.size() > minQueue.size()) {
			int delete = maxQueue.first();
			maxQueue.remove(delete);
			minQueue.add(delete);
		}
	}

	// time O(n*k), space O(k)
	// run time : 74ms
	public double[] medianSlidingWindowPriorityQueue(int[] nums, int k) {
		int n = nums.length, j = 0;
		double[] res = new double[n - k + 1];
		boolean even = k % 2 == 0;
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b.compareTo(a));
		for (int i = 0; i < n; i++) {
			if (i >= k) {
				int delete = nums[i - k];
				if (delete >= minHeap.peek()) {
					minHeap.remove(delete);
				} else {
					maxHeap.remove(delete);
				}
				// need to re-balance two queues here
				// for input like nums = [9, 7, 0, 6, 5] and k = 2
				rebalance(minHeap, maxHeap);
			}
			if (minHeap.isEmpty() || nums[i] >= minHeap.peek()) {
				minHeap.offer(nums[i]);
			} else {
				maxHeap.offer(nums[i]);
			}
			rebalance(minHeap, maxHeap);
			if (i >= k - 1) {
				res[j++] = even ? ((double) maxHeap.peek()
					+ (double) minHeap.peek()) / 2.0 : (double) minHeap.peek();
			}
		}
		return res;
	}

	private void rebalance(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
		while (minHeap.size() > maxHeap.size() + 1) {
			maxHeap.offer(minHeap.poll());
		}
		while (maxHeap.size() > minHeap.size()) {
			minHeap.offer(maxHeap.poll());
		}
	}
}
