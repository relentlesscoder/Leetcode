package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 07/17/2017.
 * #0295 https://leetcode.com/problems/find-median-from-data-stream/
 */
public class FindMedianFromDataStream {

	private PriorityQueue<Integer> maxQueue, minQueue;

	/** initialize your data structure here. */
	public FindMedianFromDataStream() {
		maxQueue = new PriorityQueue<>((a, b) -> b - a);
		minQueue = new PriorityQueue<>();
	}

	// time O(log(d)), space O(d)
	public void addNum(int num) {
		if(maxQueue.isEmpty() || maxQueue.peek() >= num){
			maxQueue.offer(num);
		}else{
			minQueue.offer(num);
		}
		while(maxQueue.size() > minQueue.size() + 1){
			minQueue.offer(maxQueue.poll());
		}
		while(minQueue.size() > maxQueue.size()){
			maxQueue.offer(minQueue.poll());
		}
	}

	// time O(1)
	public double findMedian() {
		if(maxQueue.size() == minQueue.size()){
			return (maxQueue.peek() + minQueue.peek()) / 2.0;
		}else{
			return (double)maxQueue.peek();
		}
	}
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
