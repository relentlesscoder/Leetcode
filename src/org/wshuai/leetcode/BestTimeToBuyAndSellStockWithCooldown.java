package org.wshuai.leetcode;

/**
 * Created by Wei on 10/25/19.
 * #309 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 */
public class BestTimeToBuyAndSellStockWithCooldown {
	public int maxProfit(int[] prices) {
		int prev = 0, cash = 0;
		int hold = Integer.MIN_VALUE;

		for(int p: prices){
			int c = cash;
			cash = Math.max(cash, hold + p);
			hold = Math.max(hold, prev - p);
			prev = c;
		}

		return cash;
	}
}
