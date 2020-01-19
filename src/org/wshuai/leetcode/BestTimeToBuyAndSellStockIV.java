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
		// the same as infinite transactions
		if(k >= n / 2){
			int c = 0, h = Integer.MIN_VALUE;
			for(int p : prices){
				int old = c;
				c = Math.max(c, h + p);
				h = Math.max(h, old - p);
			}
			return c;
		}

		// the same idea as two transactions
		int[] cash = new int[k + 1], hold = new int[k + 1];
		Arrays.fill(hold, Integer.MIN_VALUE);
		for(int p : prices){
			for(int j = 1; j <= k; j++){
				cash[j] = Math.max(cash[j], hold[j] + p);
				hold[j] = Math.max(hold[j], cash[j - 1] - p);
			}
		}
		return cash[k];
	}
}
