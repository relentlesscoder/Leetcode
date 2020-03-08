package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/2016.
 * #0201 https://leetcode.com/problems/bitwise-and-of-numbers-range/
 */
public class BitwiseANDOfNumbersRange {
	// time O(32)
	// https://leetcode.com/problems/bitwise-and-of-numbers-range/discuss/56729/Bit-operation-solution(JAVA)
	public int rangeBitwiseAnd(int m, int n) {
		int i = 0;
		// find common prefix of m and n
		while(m != n){
			m >>= 1;
			n >>= 1;
			i++;
		}
		return m << i;
	}
}
