package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/31/2020.
 * #1561 https://leetcode.com/problems/maximum-number-of-coins-you-can-get/
 */
public class MaximumNumberOfCoinsYouCanGet {

	// time O(n*log(n))
	public int maxCoins(int[] piles) {
		Arrays.sort(piles);
		int res = 0, n = piles.length, m = n/3;
		for(int i = n - 2; i >= m; i -= 2){
			res += piles[i];
		}
		return res;
	}
}
