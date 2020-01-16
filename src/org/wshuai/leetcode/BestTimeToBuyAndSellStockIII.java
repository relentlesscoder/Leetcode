package org.wshuai.leetcode;

/**
 * Created by Wei on 10/25/2019.
 * #0123 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 */
public class BestTimeToBuyAndSellStockIII {
	// time O(n)
	public int maxProfit(int[] prices) {
		int cash1 = 0, cash2 = 0,
				hold1 = Integer.MIN_VALUE, hold2 = Integer.MIN_VALUE;
		for(int p : prices){
			cash2 = Math.max(cash2, hold2 + p);
			hold2 = Math.max(hold2, cash1 - p);
			cash1 = Math.max(cash1, hold1 + p);
			hold1 = Math.max(hold1, -p);
		}
		return cash2;
	}
}
