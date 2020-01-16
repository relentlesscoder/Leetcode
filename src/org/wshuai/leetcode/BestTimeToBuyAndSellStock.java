package org.wshuai.leetcode;

/**
 * Created by Wei on 10/25/2019.
 * #0121 https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */
public class BestTimeToBuyAndSellStock {
	// time O(n)
	public int maxProfit(int[] prices) {
		int cash = 0, hold = Integer.MIN_VALUE;
		for(int p : prices){
			cash = Math.max(cash, hold + p);
			hold = Math.max(hold, -p);
		}
		return cash;
	}
}
