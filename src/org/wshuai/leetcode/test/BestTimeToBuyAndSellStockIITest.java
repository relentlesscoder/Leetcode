package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.BestTimeToBuyAndSellStockII;

/**
 * Created by Wei on 11/13/16.
 */
public class BestTimeToBuyAndSellStockIITest {
	@Test
	public void testcase() {
		BestTimeToBuyAndSellStockII bt = new BestTimeToBuyAndSellStockII();
		int max = bt.maxProfit(new int[]{5, 5, 4, 9, 3, 8, 5, 5, 1, 6, 8, 3, 4});
	}
}
