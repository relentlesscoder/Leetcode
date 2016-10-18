package org.wshuai.leetcode;

/**
 * Created by Wei on 10/1/2016.
 * #367 https://leetcode.com/problems/valid-perfect-square/
 */
public class ValidPerfectSquare {
  public boolean isPerfectSquare(int num) {
    if(num < 0){
      return false;
    }
    long val = num;
    long left = 0;
    long right = num;
    while(left <= right){
      long mid = left + (right - left)/2;
      long prod = mid*mid;
      if(val == prod){
        return true;
      }else if(val < prod){
        right = mid - 1;
      }else{
        left = mid + 1;
      }
    }
    return false;
  }
}
