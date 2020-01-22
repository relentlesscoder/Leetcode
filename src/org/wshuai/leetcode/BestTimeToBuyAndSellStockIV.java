package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 04/03/2017.
 * #0188 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 */
public class BestTimeToBuyAndSellStockIV {
	// time O(n*k), space O(k)
	public int maxProfit(int k, int[] prices) {
		int n = prices.length;
		if(k >= n / 2){
			int cash = 0, hold = Integer.MIN_VALUE;
			for(int p : prices){
				int old = cash;
				cash = Math.max(cash, hold + p);
				hold = Math.max(hold, old - p);
			}
			return cash;
		}
		int[][] dp = new int[2][k + 1];
		Arrays.fill(dp[1], Integer.MIN_VALUE);
		for(int p : prices){
			for(int i = 1; i <= k; i++){
				dp[0][i] = Math.max(dp[0][i], dp[1][i] + p);
				dp[1][i] = Math.max(dp[1][i], dp[0][i - 1] - p);
			}
		}
		return dp[0][k];
	}
}
