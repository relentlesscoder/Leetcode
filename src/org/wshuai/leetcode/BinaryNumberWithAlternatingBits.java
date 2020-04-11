package org.wshuai.leetcode;

/**
 * Created by Wei on 10/08/2019.
 * #0693 https://leetcode.com/problems/binary-number-with-alternating-bits/
 */
public class BinaryNumberWithAlternatingBits {
	// time O(d)
	public boolean hasAlternatingBits(int n) {
		int prev = (n & 1), cur;
		n >>= 1;
		while(n > 0){
			cur = (n & 1);
			if(cur == prev){
				return false;
			}
			prev = cur;
			n >>= 1;
		}
		return true;
	}
}
