package org.wshuai.leetcode;

/**
 * Created by Wei on 11/7/2016.
 * #397 https://leetcode.com/problems/integer-replacement/
 */
public class IntegerReplacement {
  //4ms
  public int integerReplacement(int n) {
    long t = n;
    int count = 0;
    while(t > 1){
      if(t%2 == 0){
        t >>= 1;
      }else{
        if((t+1)%4 == 0 && t != 3){
          t += 1;
        }else{
          t -= 1;
        }
      }
      count++;
    }
    return count;
  }

  //11ms
  public int integerReplacementRecursive(int n) {
    if(n == 1){
      return 0;
    }
    if(n%2 == 0){
      return 1 + integerReplacementRecursive(n/2);
    }else{
      long x = n;
      int p = (int)((x+1)/2);
      int m = (int)((x-1)/2);
      return 2 + Math.min(integerReplacementRecursive(p), integerReplacementRecursive(m));
    }
  }
}
