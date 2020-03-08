package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 07/17/2017.
 * #0295 https://leetcode.com/problems/find-median-from-data-stream/
 */
public class FindMedianFromDataStream {
	private PriorityQueue<Integer> minQueue;
	private PriorityQueue<Integer> maxQueue;

	/** initialize your data structure here. */
	public FindMedianFromDataStream() {
		minQueue = new PriorityQueue<>();
		maxQueue = new PriorityQueue<>((a, b) -> b - a);
	}

	public void addNum(int num) {
		if(minQueue.size() == 0 || num >= minQueue.peek()){
			minQueue.offer(num);
		}else{
			maxQueue.offer(num);
		}
		while(minQueue.size() > maxQueue.size() + 1){
			maxQueue.offer(minQueue.poll());
		}
		while(minQueue.size() < maxQueue.size()){
			minQueue.offer(maxQueue.poll());
		}
	}

	public double findMedian() {
		int n = minQueue.size() + maxQueue.size();
		if(n == 0){
			return -1;
		}
		if(n % 2 == 0){
			return (minQueue.peek() + maxQueue.peek()) / 2.0;
		}
		return (double)minQueue.peek();
	}
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
