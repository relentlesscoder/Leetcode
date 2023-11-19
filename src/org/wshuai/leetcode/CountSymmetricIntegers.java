package org.wshuai.leetcode;

/**
 * Created by Wei on 11/07/2023.
 * #2843 https://leetcode.com/problems/count-symmetric-integers/
 */
public class CountSymmetricIntegers {

	// time O(n), space O(1)
	public int countSymmetricIntegers(int low, int high) {
		int res = 0;
		for (int num = Math.max(low, 10); num <= Math.min(high, 9_999); num++) {
			if ((num < 100 && num / 10 == num % 10) ||
					(num >= 1_000 && (((num % 10) + ((num / 10) % 10)) == (num / 1_000 + ((num / 100) % 10))))) {
				res++;
			}
		}
		return res;
	}
}
