package org.wshuai.leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by Wei on 7/17/17.
 * #295 https://leetcode.com/problems/find-median-from-data-stream/
 */
public class FindMedianFromDataStream {
  PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
  PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());

  /** initialize your data structure here. */
  public FindMedianFromDataStream() {

  }

  public void addNum(int num) {
    if(num < findMedian()){
      maxHeap.offer(num);
    }else{
      minHeap.offer(num);
    }
    if(maxHeap.size() > minHeap.size()){
      minHeap.offer(maxHeap.poll());
    }
    if(minHeap.size() - maxHeap.size() > 1){
      maxHeap.offer(minHeap.poll());
    }
  }

  public double findMedian() {
    if(minHeap.size() == 0 && maxHeap.size() == 0){
      return 0;
    }
    if(minHeap.size() == maxHeap.size()){
      return ((double)minHeap.peek() + (double)maxHeap.peek())/2.0;
    }else{
      return (double)minHeap.peek();
    }
  }
}
