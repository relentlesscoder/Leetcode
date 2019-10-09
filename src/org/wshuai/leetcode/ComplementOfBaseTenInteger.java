package org.wshuai.leetcode;

/**
 * Created by Wei on 10/9/2019.
 * #1009 https://leetcode.com/problems/complement-of-base-10-integer/
 */
public class ComplementOfBaseTenInteger {
	public int bitwiseComplement(int N) {
		int c = 1;
		// to get the mask. For 10011, the mask will be 11111.
		while(c < N){
			c = (c << 1) + 1;
		}
		return N ^ c;
	}
}
