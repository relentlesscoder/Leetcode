package org.wshuai.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Wei on 9/27/2016.
 */
public class DesignPhoneDirectory {
  int max;
  Queue<Integer> queue;
  boolean[] used;

  /** Initialize your data structure here
   @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
  public DesignPhoneDirectory(int maxNumbers) {
    if(maxNumbers <= 0){
      throw new IllegalArgumentException("Invalid input.");
    }
    queue = new LinkedList<Integer>();
    for(int i = 0; i < maxNumbers; i++){
      queue.offer(i);
    }
    used = new boolean[maxNumbers];
    max = maxNumbers - 1;
  }

  /** Provide a number which is not assigned to anyone.
   @return - Return an available number. Return -1 if none is available. */
  public int get() {
    if(queue.isEmpty()){
      return -1;
    }
    int val = queue.poll();
    used[val] = true;
    return val;
  }

  /** Check if a number is available or not. */
  public boolean check(int number) {
    return  number <= max && !used[number];
  }

  /** Recycle or release a number. */
  public void release(int number) {
    if(number <= max && used[number]){
      queue.offer(number);
      used[number] = false;
    }
  }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
