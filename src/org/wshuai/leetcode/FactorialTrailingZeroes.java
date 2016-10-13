package org.wshuai.leetcode;

/**
 * Created by Wei on 10/8/16.
 */
public class FactorialTrailingZeroes {

  //Key: number of factor 5 always >= that of 2

  //1ms
  public int trailingZeroes(int n) {
    if(n <= 0){
      return 0;
    }
    int count = 0;
    for(long i = 5; n/i >= 1; i*=5){
      count += n/i;
    }
    return count;
  }

  //2ms
  public int trailingZeroesBetter(int n) {
    if(n <= 0){
      return 0;
    }
    int count = 0;
    for(long i = 5; n/i >= 1; i*=5){
      count += n/i;
    }
    return count;
  }

  //Failed big input
  public int trailingZeroesNaive(int n) {
    if(n <= 0){
      return 0;
    }
    long x = 1;
    for(int i = 1; i <= n; i++){
      x *= i;
    }
    int result = 0;
    while(x > 0){
      if(x%10 == 0){
        result++;
      }else{
        break;
      }
      x/=10;
    }
    return result;
  }
}
