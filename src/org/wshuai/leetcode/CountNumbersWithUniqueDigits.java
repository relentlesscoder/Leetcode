package org.wshuai.leetcode;

/**
 * Created by Wei on 9/24/2016.
 */
public class CountNumbersWithUniqueDigits {
  public int countNumbersWithUniqueDigits(int n) {
    if(n < 0){
      return 0;
    }
    if(n == 0){
      return 1;
    }
    int[] counts = new int[n];
    counts[0] = 10;
    for(int i = 1; i < n; i++){
      int num = 9;
      int c = 9;
      int x = 10 - i;
      while(c >= x){
        num *= c;
        c--;
      }
      counts[i] = counts[i - 1] + num;
    }
    return counts[n - 1];
  }
}
