package org.wshuai.leetcode;

/**
 * Created by Wei on 9/19/2016.
 */
public class PowerOfTwo {
  public boolean isPowerOfTwo(int n) {
    return n>0 && (n & n - 1)==0;
  }
}
