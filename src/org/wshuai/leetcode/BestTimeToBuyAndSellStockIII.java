package org.wshuai.leetcode;

/**
 * Created by Wei on 4/3/2017.
 * #123 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 */
public class BestTimeToBuyAndSellStockIII {
  public int maxProfit(int[] prices) {
    if(prices == null || prices.length == 0){
      return 0;
    }

    int len = prices.length;
    int[] left = new int[len];
    int[] right = new int[len];

    left[0] = 0;
    int min = prices[0];
    for(int i = 1; i < len; i++){
      min = Math.min(min, prices[i]);
      left[i] = Math.max(left[i-1], prices[i]-min);
    }

    right[len-1] = 0;
    int max = prices[len-1];
    for(int i = len-2; i >= 0; i--){
      max = Math.max(max, prices[i]);
      right[i] = Math.max(right[i+1], max-prices[i]);
    }

    int profit = 0;
    for(int i = 0; i < len; i++){
      profit = Math.max(profit, left[i]+right[i]);
    }

    return profit;
  }
}
