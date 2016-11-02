package org.wshuai.leetcode;

/**
 * Created by Wei on 11/2/2016.
 * #441 https://leetcode.com/problems/arranging-coins/
 */
public class ArrangingCoins {
  public int arrangeCoins(int n) {
    if(n <= 0){
      return 0;
    }

    int count = 0;
    int i = 1;
    while(n >= i){
      n -= i;
      i++;
      count++;
    }

    return count;
  }
}
