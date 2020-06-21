package org.wshuai.leetcode;

/**
 * Created by Wei on 10/08/2019.
 * #0762 https://leetcode.com/problems/prime-number-of-set-bits-in-binary-representation/
 */
public class PrimeNumberOfSetBitsInBinaryRepresentation {
	// time O(n*d)
	public int countPrimeSetBits(int L, int R) {
		int res = 0;
		boolean[] primes = new boolean[33];
		primes[2] = primes[3] = primes[5] = primes[7] = primes[11] = primes[13] =
			primes[17] = primes[19] = primes[23] = primes[29] = primes[31] = true;
		for (int i = L; i <= R; i++) {
			if (primes[countSetBits(i)]) {
				res++;
			}
		}
		return res;
	}

	private int countSetBits(int num) {
		int mask1 = 0x55555555, // 0101 0101 0101 0101 0101 0101 0101 0101
			mask2 = 0x33333333, // 0011 0011 0011 0011 0011 0011 0011 0011
			mask3 = 0x0F0F0F0F, // 0000 1111 0000 1111 0000 1111 0000 1111
			mask4 = 0x00FF00FF, // 0000 0000 1111 1111 0000 0000 1111 1111
			mask5 = 0x0000FFFF; //  0000 0000 0000 0000 1111 1111 1111 1111
		int res = ((num >> 1) & mask1) + (num & mask1);
		res = ((res >> 2) & mask2) + (res & mask2);
		res = ((res >> 4) & mask3) + (res & mask3);
		res = ((res >> 8) & mask4) + (res & mask4);
		res = ((res >> 16) & mask5) + (res & mask5);
		return res;
	}
}
