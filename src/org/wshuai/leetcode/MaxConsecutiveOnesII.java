package org.wshuai.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Wei on 2/15/17.
 * #487 https://leetcode.com/problems/max-consecutive-ones-ii/
 */
public class MaxConsecutiveOnesII {
  //O(n), maintain a sliding window which contains at most k zeros
  public int findMaxConsecutiveOnes(int[] nums) {
    if(nums == null || nums.length == 0){
      return 0;
    }
    int k = 1;
    int max = 0;
    int zeros = 0;
    for(int i = 0, j = 0; j < nums.length; j++){
      if(nums[j] == 0){
        zeros++;
      }
      while(zeros > k){
        if(nums[i++] == 0){
          zeros--;
        }
      }
      max = Math.max(max, j-i+1);
    }
    return max;
  }

  //O(n), use a queue to store zero indexes to handle infinite stream
  public int findMaxConsecutiveOnesInfinite(int[] nums) {
    int k = 1;
    int max = 0;
    Queue<Integer> queue = new LinkedList<Integer>();
    for(int i = 0, j = 0; i < nums.length; i++){
      if(nums[i] == 0){
        queue.offer(i);
      }
      while(queue.size() > k){
        j = queue.poll()+1;
      }
      max = Math.max(max, i-j+1);
    }
    return max;
  }
}
