package org.wshuai.leetcode;

/**
 * Created by Wei on 10/25/2019.
 * #0122 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class BestTimeToBuyAndSellStockII {
	// time O(n)
	public int maxProfit(int[] prices) {
		int cash = 0, hold = Integer.MIN_VALUE;
		for(int p : prices){
			int prev = cash;
			cash = Math.max(cash, hold + p);
			hold = Math.max(hold, prev - p);
		}
		return cash;
	}
}
