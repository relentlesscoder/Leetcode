package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #338 https://leetcode.com/problems/counting-bits/
 */
public class CountingBits {
	public int[] countBits(int num) {
		if (num < 0) {
			return new int[0];
		}

		int[] res = new int[num + 1];
		NumberOf1Bits nb = new NumberOf1Bits();
		for (int i = 0; i <= num; i++) {
			res[i] = nb.hammingWeight(i);
		}
		return res;
	}
}
