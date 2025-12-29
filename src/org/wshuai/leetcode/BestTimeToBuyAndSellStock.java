package org.wshuai.leetcode;

/**
 * Created by Wei on 10/25/2019.
 * #0121 https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */
public class BestTimeToBuyAndSellStock {

    // time O(n), space O(1)
    public int maxProfit(int[] prices) {
		// 维护一个最小的价格 min 并预设为第一天的价格，从第二天(不可在同一天交易)开始
		// 遍历数组。选择当天卖出的最大利润为 prices[i] - min。所有交易日里利润的最大
		// 值即为答案。
        int res = 0, min = prices[0];
        for (int p : prices) {
			// 计算在当前交易日卖出股票的最大利润
            res = Math.max(res, p - min);
			// 因为不允许同一天交易，所以在计算当天的最大利润之后更新最小价格。
            min = Math.min(min, p);
        }
        return res;
    }
}
