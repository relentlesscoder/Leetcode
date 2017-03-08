package org.wshuai.leetcode;

/**
 * Created by Wei on 3/5/17.
 * #495 https://leetcode.com/problems/teemo-attacking/
 */
public class TeemoAttacking {
  public int findPoisonedDuration(int[] timeSeries, int duration) {
    int sum = 0;
    int len = timeSeries.length;
    int start = -1;
    int end = -1;
    for(int i = 0; i < len; i++){
      int time = timeSeries[i];
      if(time >= end){
        sum += (end-start);
        start = time;
      }
      end = time+duration;
    }
    return sum+(end-start);
  }
}
