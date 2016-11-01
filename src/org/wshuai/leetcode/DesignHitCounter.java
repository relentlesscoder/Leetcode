package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 10/31/16.
 */
public class DesignHitCounter {
  private int sum;
  private LinkedList<int[]> hits;

  /** Initialize your data structure here. */
  public DesignHitCounter() {
    sum = 0;
    hits = new LinkedList<int[]>();
  }

  /** Record a hit.
   @param timestamp - The current timestamp (in seconds granularity). */
  public void hit(int timestamp) {
    sum++;
    int[] last = hits.peekLast();
    if(last != null && last[0] == timestamp){
      last[1]++;
    }else{
      int[] curr = new int[2];
      curr[0] = timestamp;
      curr[1] = 1;
      hits.offer(curr);
    }
  }

  /** Return the number of hits in the past 5 minutes.
   @param timestamp - The current timestamp (in seconds granularity). */
  public int getHits(int timestamp) {
    int[] head = hits.peek();
    while(head != null && timestamp - head[0] >= 300){
      hits.poll();
      sum -= head[1];
      head = hits.peek();
    }
    return sum;
  }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
