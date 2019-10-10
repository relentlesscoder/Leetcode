package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/2016.
 * #201 https://leetcode.com/problems/bitwise-and-of-numbers-range/
 */
public class BitwiseANDOfNumbersRange {
	public int rangeBitwiseAnd(int m, int n) {
		int i = 0;
		while (m != n) {
			m >>= 1;
			n >>= 1;
			i++;
		}
		return (m <<= i);
	}
}
