package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #191 https://leetcode.com/problems/number-of-1-bits/
 */
public class NumberOf1Bits {

	// 1ms
	public int hammingWeight(int n) {
		//Create mask 01010101010101010101010101010101
		int mask1 = 0x55555555;
		//Create mask 00110011001100110011001100110011
		int mask2 = 0x33333333;
		//Create mask 00001111000011110000111100001111
		int mask3 = 0x0F0F0F0F;
		//Create mask 00000000111111110000000011111111
		int mask4 = 0x00FF00FF;
		//Create mask 00000000000000001111111111111111
		int mask5 = 0x0000FFFF;
		int result = ((n >> 1) & mask1) + (n & mask1);
		result = ((result >> 2) & mask2) + (result & mask2);
		result = ((result >> 4) & mask3) + (result & mask3);
		result = ((result >> 8) & mask4) + (result & mask4);
		result = ((result >> 16) & mask5) + (result & mask5);
		return result;
	}

	// 2ms
	public int hammingWeightShitBits(int n) {
		int result = 0;
		for (int i = 0; i < 32; i++) {
			result += n & 1;
			n = n >> 1;
		}
		return result;
	}
}
