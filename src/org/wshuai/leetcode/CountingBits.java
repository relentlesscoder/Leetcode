package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #0338 https://leetcode.com/problems/counting-bits/
 */
public class CountingBits {

	// time O(n), space O(n)
	public int[] countBits(int n) {
		int[] res = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			res[i] = res[i >> 1] + (i & 1);
		}
		return res;
	}

	// time O(n), space O(n)
	public int[] countBitsDP(int n) {
		int[] res = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			res[i] = res[i & (i - 1)] + 1;
		}
		return res;
	}

	// time O(n), space O(n)
	public int[] countBitsHammingDistance(int n) {
		int[] res = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			res[i] = count(i);
		}
		return res;
	}

	private int count(int n) {
		int mask1 = 0x55555555,
				mask2 = 0x33333333,
				mask3 = 0x0f0f0f0f,
				mask4 = 0x00ff00ff,
				mask5 = 0x0000ffff;
		int res = ((n >> 1) & mask1) + (n & mask1);
		res = ((res >> 2) & mask2) + (res & mask2);
		res = ((res >> 4) & mask3) + (res & mask3);
		res = ((res >> 8) & mask4) + (res & mask4);
		return ((res >> 16) & mask5) + (res & mask5);
	}
}
