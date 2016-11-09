package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/7/16.
 * #322 https://leetcode.com/problems/coin-change/
 */
public class CoinChange {
  //O(len(coins)*amount)
  public int coinChange(int[] coins, int amount) {
    if(coins == null || coins.length == 0){
      return -1;
    }
    int[] aux = new int[amount+1];
    Arrays.fill(aux, Integer.MAX_VALUE);
    aux[0] = 0;
    int len = coins.length;
    for(int i = 0; i <= amount; i++){
      for(int j = 0; j < len; j++){
        long sum = i+(long)coins[j];
        if(sum <= amount && aux[i] != Integer.MAX_VALUE){
          int idx = (int)sum;
          aux[idx] = Math.min(aux[idx], 1+aux[i]);
        }
      }
    }
    return aux[amount] == Integer.MAX_VALUE ? -1 : aux[amount];
  }
}
