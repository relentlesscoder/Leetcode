package org.wshuai.leetcode;

/**
 * Created by Wei on 11/02/2016.
 * #0441 https://leetcode.com/problems/arranging-coins/
 */
public class ArrangingCoins {
	// time O(log(n))
	public int arrangeCoins(int n) {
		int res = 0, i = 1;
		while(n >= i){
			res++;
			n -= i++;
		}
		return res;
	}
}
