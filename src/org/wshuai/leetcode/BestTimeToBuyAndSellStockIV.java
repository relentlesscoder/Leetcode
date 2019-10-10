package org.wshuai.leetcode;

/**
 * Created by Wei on 4/3/2017.
 * #188 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 */
public class BestTimeToBuyAndSellStockIV {
	//See http://www.cnblogs.com/grandyang/p/4295761.html
	public int maxProfit(int k, int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int len = prices.length;
		if (k >= len) {
			return solveMaxProfit(prices);
		}
		int[] global = new int[k + 1];
		int[] local = new int[k + 1];
		for (int i = 0; i < len - 1; i++) {
			int diff = prices[i + 1] - prices[i];
			for (int j = k; j >= 1; j--) {
				local[j] = Math.max(global[j - 1] + Math.max(diff, 0), local[j] + diff);
				global[j] = Math.max(global[j], local[j]);
			}
		}
		return global[k];
	}

	private int solveMaxProfit(int[] prices) {
		int res = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] > prices[i - 1]) {
				res += prices[i] - prices[i - 1];
			}
		}
		return res;
	}
}
