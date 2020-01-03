package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 7/17/17.
 * #480 https://leetcode.com/problems/sliding-window-median/
 */
public class SlidingWindowMedian {
	private PriorityQueue<Long> maxHeap;
	private PriorityQueue<Long> minHeap;

	public double[] medianSlidingWindow(int[] nums, int k) {
		if(nums == null || nums.length == 0 || k == 0 || k > nums.length){
			return new double[0];
		}
		double[] res = new double[nums.length - k + 1];
		int j = 0;
		boolean even = k % 2 == 0;
		maxHeap = new PriorityQueue<>((Long a, Long b) -> b.compareTo(a));
		minHeap = new PriorityQueue<>();
		for(int i = 0; i < nums.length; i++){
			if(maxHeap.isEmpty() || nums[i] <= maxHeap.peek()){
				maxHeap.offer((long)nums[i]);
			}else{
				minHeap.offer((long)nums[i]);
			}
			balance();
			int count = maxHeap.size() + minHeap.size();
			if(count == k){
				double median = even ? (maxHeap.peek() + minHeap.peek()) / 2.0 : (double)maxHeap.peek();
				res[j++] = median;
				long toDelete = nums[i - k + 1];
				if(toDelete <= maxHeap.peek()){
					maxHeap.remove(toDelete);
				}else{
					minHeap.remove(toDelete);
				}
				balance();
			}
		}
		return res;
	}

	private void balance(){
		while(maxHeap.size() > minHeap.size() + 1){
			minHeap.offer(maxHeap.poll());
		}
		while(minHeap.size() > maxHeap.size()){
			maxHeap.offer(minHeap.poll());
		}
	}
}
