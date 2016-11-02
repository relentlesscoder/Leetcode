package org.wshuai.leetcode;

/**
 * Created by Wei on 11/2/2016.
 * #342 https://leetcode.com/problems/power-of-four/
 */
public class PowerOfFour {
  //see http://www.cnblogs.com/grandyang/p/5403783.html
  public boolean isPowerOfFour(int num) {
    return num > 0 && (num&(num-1)) == 0 && (num & 0x55555555) == num;
  }
}
