package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 10/8/2019.
 * #762 https://leetcode.com/problems/prime-number-of-set-bits-in-binary-representation/
 */
public class PrimeNumberOfSetBitsInBinaryRepresentation {
	private Set<Integer> primes;

	public PrimeNumberOfSetBitsInBinaryRepresentation(){
		primes = new HashSet<>();
		primes.addAll(new ArrayList<Integer>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29)));
	}

	public int countPrimeSetBits(int L, int R) {
		int res = 0;
		for(int i = L; i <= R; i++){
			int count = countOne(i);
			if(primes.contains(count)){
				res++;
			}
		}
		return res;
	}

	// see #191
	private int countOne(int n){
		int mask1 = 0x55555555;  // 0101 0101 0101 0101 0101 0101 0101 0101
		int mask2 = 0x33333333;  // 0011 0011 0011 0011 0011 0011 0011 0011
		int mask3 = 0x0F0F0F0F;  // 0000 1111 0000 1111 0000 1111 0000 1111
		int mask4 = 0x00FF00FF;  // 0000 0000 1111 1111 0000 0000 1111 1111
		int mask5 = 0x0000FFFF; //  0000 0000 0000 0000 1111 1111 1111 1111
		int result = ((n >> 1) & mask1) + (n & mask1);
		result = ((result >> 2) & mask2) + (result & mask2);
		result = ((result >> 4) & mask3) + (result & mask3);
		result = ((result >> 8) & mask4) + (result & mask4);
		result = ((result >> 16) & mask5) + (result & mask5);
		return result;
	}
}
