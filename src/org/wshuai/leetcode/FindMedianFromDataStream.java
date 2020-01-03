package org.wshuai.leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by Wei on 7/17/17.
 * #295 https://leetcode.com/problems/find-median-from-data-stream/
 */
public class FindMedianFromDataStream {
	private PriorityQueue<Integer> maxHeap;
	private PriorityQueue<Integer> minHeap;

	/** initialize your data structure here. */
	public FindMedianFromDataStream() {
		maxHeap = new PriorityQueue<>((a, b) -> b - a);
		minHeap = new PriorityQueue<>();
	}

	public void addNum(int num) {
		if(maxHeap.size() == 0 || num <= maxHeap.peek()){
			maxHeap.offer(num);
		}else{
			minHeap.offer(num);
		}
		while(maxHeap.size() > minHeap.size() + 1){
			minHeap.offer(maxHeap.poll());
		}
		while(minHeap.size() > maxHeap.size()){
			maxHeap.offer(minHeap.poll());
		}
	}

	public double findMedian() {
		if(maxHeap.size() == 0 && minHeap.size() == 0){
			return -1;
		}
		if(maxHeap.size() == minHeap.size()){
			return (maxHeap.peek() + minHeap.peek())/2.0;
		}
		return (double)maxHeap.peek();
	}
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
