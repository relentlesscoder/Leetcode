package org.wshuai.leetcode;

/**
 * Created by Wei on 11/19/16.
 * #309 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 */
public class BestTimeToBuyAndSellStockWithCooldown {
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int len = prices.length;
		int[] empty = new int[len];
		int[] hold = new int[len];

		empty[0] = 0;
		hold[0] = -prices[0];
		for (int i = 1; i < len; i++) {
			empty[i] = Math.max(empty[i - 1], hold[i - 1] + prices[i]);
			hold[i] = Math.max(hold[i - 1], i > 2 ? empty[i - 2] - prices[i] : -prices[i]);
		}
		return empty[len - 1];
	}
}
