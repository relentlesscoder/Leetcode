package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #122 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class BestTimeToBuyAndSellStockII {
  public int maxProfit(int[] prices) {
    if(prices == null || prices.length == 0){
      return 0;
    }
    int len = prices.length;
    if(len == 1){
      return 0;
    }

    int pft = 0;
    int buy = -1;

    int i = 0;
    while(i < len){
      if(buy == -1){
        if(i == 0 && prices[0] < prices[1]){
          buy = prices[i];
        }else if(i != 0 && i != len - 1 && prices[i - 1] >= prices[i] && prices[i + 1] > prices[i]){
          buy = prices[i];
        }
      }else{
        if(i == len - 1 && prices[i] > prices[i - 1]){
          pft += (prices[i] - buy);
          buy = -1;
        }else if(i != 0 && i != len - 1  && prices[i - 1] < prices[i] && prices[i + 1] <= prices[i]){
          pft += (prices[i] - buy);
          buy = -1;
        }
      }
      i++;
    }
    return pft;
  }
}
