package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #122 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class BestTimeToBuyAndSellStockII {
	//O(n)
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int max = 0;
		int len = prices.length;
		for (int i = 1; i < len; i++) {
			int diff = prices[i] - prices[i - 1];
			if (diff > 0) {
				max += diff;
			}
		}
		return max;
	}
}
