package org.wshuai.leetcode;

/**
 * Created by Wei on 10/8/19.
 * #693 https://leetcode.com/problems/binary-number-with-alternating-bits/
 */
public class BinaryNumberWithAlternatingBits {
	public boolean hasAlternatingBits(int n) {
		int curr = n % 2;
		n /= 2;
		while(n > 0){
			if(curr == n % 2){
				return false;
			}
			curr = n % 2;
			n /= 2;
		}
		return true;
	}
}
