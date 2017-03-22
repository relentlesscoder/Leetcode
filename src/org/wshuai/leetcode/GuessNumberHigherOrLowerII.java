package org.wshuai.leetcode;

/**
 * Created by Wei on 3/21/17.
 * #375 https://leetcode.com/problems/guess-number-higher-or-lower-ii/
 */
public class GuessNumberHigherOrLowerII {
  public int getMoneyAmount(int n) {
    int[][] aux = new int[n+1][n+1];
    return getMoneyAmountUtil(aux, 1, n);
  }

  private int getMoneyAmountUtil(int[][] aux, int s, int e){
    if(s >= e){
      return 0;
    }
    if(aux[s][e] != 0){
      return aux[s][e];
    }
    aux[s][e] = Integer.MAX_VALUE;
    for(int i = s; i <= e; i++){
      aux[s][e] = Math.min(aux[s][e], i + Math.max(
        getMoneyAmountUtil(aux, s, i-1), getMoneyAmountUtil(aux, i+1, e)));
    }
    return aux[s][e];
  }
}
