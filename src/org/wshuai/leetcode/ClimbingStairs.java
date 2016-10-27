package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #70 https://leetcode.com/problems/climbing-stairs/
 */
public class ClimbingStairs {
  //Dynamic programming
  public int climbStairs(int n) {
    if(n == 1){
      return 1;
    }
    if(n == 2){
      return 2;
    }
    int n1 = 1;
    int n2 = 2;
    int r = 0;
    for(int i = 2; i < n; i++){
      r = n2 + n1;
      n1 = n2;
      n2 = r;
    }
    return r;
  }
}
