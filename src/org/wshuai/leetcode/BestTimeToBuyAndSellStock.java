package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #121 https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */
public class BestTimeToBuyAndSellStock {
	public int maxProfit(int[] prices) {
		if (prices == null) {
			return 0;
		}
		int len = prices.length;
		if (len == 0 || len == 1) {
			return 0;
		}

		int max = prices[0];
		int min = prices[0];
		int mp = 0;
		for (int i = 0; i < len; i++) {
			if (prices[i] > max) {
				max = prices[i];
				int diff = max - min;
				mp = diff > mp ? diff : mp;
			}
			if (prices[i] < min) {
				min = prices[i];
				max = prices[i];
			}
		}
		return mp;
	}

	//Divide and conquer
	public int maxProfitDC(int[] prices) {
		if (prices == null || prices.length == 0 || prices.length == 1) {
			return 0;
		}
		int prx = 0;
		int len = prices.length;
		if (len == 2) {
			prx = prices[1] - prices[0];
		}
		int[] prs = new int[len - 1];
		for (int i = 0; i < len - 1; i++) {
			prs[i] = prices[i + 1] - prices[i];
		}
		prx = maxPr(prs, 0, len - 2);
		return prx > 0 ? prx : 0;
	}

	public int maxPr(int[] prs, int p, int q) {
		if (p < q) {
			int r = (p + q) / 2;
			int lp = maxPr(prs, p, r);
			int rp = maxPr(prs, r + 1, q);
			int cp = maxcPr(prs, p, r, q);
			int max = lp > rp ? lp : rp;
			max = max > cp ? max : cp;
			return max;
		} else {
			return prs[p];
		}
	}

	public int maxcPr(int[] prs, int p, int r, int q) {
		int lmax = Integer.MIN_VALUE;
		int x = 0;
		for (int i = r; i >= p; i--) {
			x += prs[i];
			lmax = x > lmax ? x : lmax;
		}
		int rmax = Integer.MIN_VALUE;
		x = 0;
		for (int i = r + 1; i <= q; i++) {
			x += prs[i];
			rmax = x > rmax ? x : rmax;
		}
		return lmax + rmax;
	}
}
