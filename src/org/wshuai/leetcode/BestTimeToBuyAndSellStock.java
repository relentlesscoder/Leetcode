package org.wshuai.leetcode;

/**
 * Created by Wei on 10/25/2019.
 * #0121 https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */
public class BestTimeToBuyAndSellStock {

	// time O(n), space O(1)
	public int maxProfit(int[] prices) {
		int res = 0, min = prices[0];
		for (int i = 1; i < prices.length; i++) {
			res = Math.max(res, prices[i] - min);
			min = Math.min(min, prices[i]);
		}
		return res;
	}

	// time O(n), space O(1)
	public int maxProfitDP(int[] prices) {
		int cash = 0, hold = Integer.MIN_VALUE;
		for(int p : prices){
			cash = Math.max(cash, hold + p);
			// since only one transaction is allowed,
			// cash is always 0 before the buy.
			hold = Math.max(hold, -p);
		}
		return cash;
	}
}
