package org.wshuai.leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by Wei on 7/17/17.
 * #480 https://leetcode.com/problems/sliding-window-median/
 */
public class SlidingWindowMedian {
  PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
  PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());

  public double[] medianSlidingWindow(int[] nums, int k) {
    if(nums == null || nums.length == 0){
      return new double[0];
    }
    int len = nums.length-k+1;
    double[] res = new double[len];
    for(int i = 0; i <= nums.length; i++){
      if(i >= k){
        res[i-k] = getMedian();
        removeNum(nums[i-k]);
      }
      if(i < nums.length){
        addNum(nums[i]);
      }
    }
    return res;
  }

  private double getMedian(){
    if(minHeap.size() == 0 && maxHeap.size() == 0){
      return 0;
    }
    if(minHeap.size() == maxHeap.size()){
      return ((double)minHeap.peek() + (double)maxHeap.peek())/2.0;
    }else{
      return (double)minHeap.peek();
    }
  }

  private void removeNum(int num){
    if(num < getMedian()){
      maxHeap.remove(num);
    }else{
      minHeap.remove(num);
    }
    if(minHeap.size() - maxHeap.size() > 1){
      maxHeap.offer(minHeap.poll());
    }
    if(maxHeap.size() > minHeap.size()){
      minHeap.offer(maxHeap.poll());
    }
  }

  private void addNum(int num){
    if(num < getMedian()){
      maxHeap.offer(num);
    }else{
      minHeap.offer(num);
    }
    if(minHeap.size() - maxHeap.size() > 1){
      maxHeap.offer(minHeap.poll());
    }
    if(maxHeap.size() > minHeap.size()){
      minHeap.offer(maxHeap.poll());
    }
  }
}
