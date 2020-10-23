package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #0191 https://leetcode.com/problems/number-of-1-bits/
 */
public class NumberOf1Bits {

	public int hammingWeight(int n) {
		// mask 01010101010101010101010101010101
		int mask1 = 0x55555555;
		// mask 00110011001100110011001100110011
		int mask2 = 0x33333333;
		// mask 00001111000011110000111100001111
		int mask3 = 0x0F0F0F0F;
		// mask 00000000111111110000000011111111
		int mask4 = 0x00FF00FF;
		// mask 00000000000000001111111111111111
		int mask5 = 0x0000FFFF;
		int res = ((n >> 1) & mask1) + (n & mask1);
		res = ((res >> 2) & mask2) + (res & mask2);
		res = ((res >> 4) & mask3) + (res & mask3);
		res = ((res >> 8) & mask4) + (res & mask4);
		res = ((res >> 16) & mask5) + (res & mask5);
		return res;
	}

	public int hammingWeightShitBits(int n) {
		int res = 0;
		for (int i = 0; i < 32; i++) {
			res += (n & 1);
			n = n >> 1;
		}
		return res;
	}
}
