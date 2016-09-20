package org.wshuai.leetcode;

/**
 * Created by Wei on 9/19/2016.
 */
public class PowerOfThree {
  public boolean isPowerOfThree(int n) {
    int max = (int)Math.pow(3, (int)(Math.log(Integer.MAX_VALUE) / Math.log(3)));
    return ( n > 0 &&  max % n == 0);
  }

  public boolean isPowerOfThreeLiteral(int n) {
    return ( n > 0 &&  1162261467 % n==0);
  }
}
