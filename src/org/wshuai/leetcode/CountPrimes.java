package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 9/1/16.
 */
public class CountPrimes {
  public static int countPrimes(int n) {
    if(n <= 2){
      return 0;
    }
    if(n == 3){
      return 1;
    }

    Set<Integer> set = new HashSet<Integer>();
    int count = n - 2;
    for(int i = 2; i*i <= n; i++){
      int j = 0;
      int num = i*i;
      while(num < n){
        if(!set.contains(num)){
          set.add(num);
          count--;
        }
        j++;
        num = i*i + j*i;
      }
    }
    return count;
  }
}
