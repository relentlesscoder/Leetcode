package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 9/27/2016.
 */
public class PerfectSquares {
  public int numSquares(int n) {
    if(n <= 0){
      return -1;
    }
    int[] dp = new int[n + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    for(int i = 0; i*i <= n; i++){
      dp[i*i] = 1;
    }
    for(int a = 0; a <= n; a++){
      for(int b = 0; a + b*b <= n; b++){
        int x = a + b*b;
        dp[x] = Math.min(dp[x], dp[a]+1);
      }
    }
    return dp[n];
  }
}
