package org.wshuai.leetcode;

/**
 * Created by Wei on 10/25/2019.
 * #0714 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {
	// time O(n)
	// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems
	public int maxProfit(int[] prices, int fee) {
		int cash = 0, hold = Integer.MIN_VALUE;
		for(int p : prices){
			int prev = cash;
			cash = Math.max(cash, hold + p);
			hold = Math.max(hold, prev - p - fee);
		}
		return cash;
	}
}
