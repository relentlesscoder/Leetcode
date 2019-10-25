package org.wshuai.leetcode;

/**
 * Created by Wei on 10/25/2019.
 * #122 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class BestTimeToBuyAndSellStockII {
	public int maxProfit(int[] prices) {
		int cash = 0;
		int hold = Integer.MIN_VALUE;
		for(int p: prices){
			int prev = cash;
			cash = Math.max(cash, hold + p);
			hold = Math.max(hold, prev - p);
		}
		return cash;
	}
}
