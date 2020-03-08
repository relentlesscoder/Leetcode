package org.wshuai.leetcode;

/**
 * Created by Wei on 01/03/2017.
 * #0461 https://leetcode.com/problems/hamming-distance/
 */
public class HammingDistance {
	public int hammingDistance(int x, int y) {
		int xor = x ^ y;
		int mask1 = 0x55555555,
			mask2 = 0x33333333,
			mask3 = 0x0f0f0f0f,
			mask4 = 0x00ff00ff,
			mask5 = 0x0000ffff;
		int res = ((xor >> 1) & mask1) + (xor & mask1);
		res = ((res >> 2) & mask2) + (res & mask2);
		res = ((res >> 4) & mask3) + (res & mask3);
		res = ((res >> 8) & mask4) + (res & mask4);
		res = ((res >> 16) & mask5) + (res & mask5);
		return res;
	}
}
