package org.wshuai.leetcode;

/**
 * Created by Wei on 09/20/2023.
 * #1963 https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/
 */
public class MinimumNumberOfSwapsToMakeTheStringBalanced {

	// time O(n), space O(1)
	public int minSwaps(String s) {
		int res = 0, balance = 0;
		for (int i = 0, j = s.length() - 1; i < j; i++) {
			balance += s.charAt(i) == '[' ? 1 : -1;
			if (balance == -1) { // swap first unbalanced "]" with "[" closest to the right end
				while (s.charAt(j) == ']') {
					j--;
				}
				j--;
				res++;
				balance = 1;
			}
		}
		return res;
	}
}
