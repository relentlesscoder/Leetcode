package org.wshuai.leetcode;

/**
 * Created by Wei on 10/25/2019.
 * #0309 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 */
public class BestTimeToBuyAndSellStockWithCooldown {
	public int maxProfit(int[] prices) {
		int cash = 0, hold = Integer.MIN_VALUE, prev = 0;
		for(int p : prices){
			int last = cash;
			cash = Math.max(cash, hold + p);
			hold = Math.max(hold, prev - p);
			prev = last;
		}
		return cash;
	}
}
