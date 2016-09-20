package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 9/18/2016.
 */
public class MovingAverageFromDataStream {
  private List<Integer> lst;

  private double sum;

  private int count;

  private int size;

  private int head;

  /** Initialize your data structure here. */
  public MovingAverageFromDataStream(int size) {
    if(size <= 0){
      throw new IllegalArgumentException("Invalid param.");
    }

    this.lst = new ArrayList<Integer>();
    this.sum = 0.0d;
    this.count = 0;
    this.size = size;
    this.head = 0;
  }

  public double next(int val) {
    if(count < size){
      lst.add(val);
      count++;
      sum += val;
      return sum/count;
    }else{
      lst.add(val);
      int hVal = lst.get(head);
      head++;
      sum -= hVal;
      sum += val;
      return sum/size;
    }
  }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
