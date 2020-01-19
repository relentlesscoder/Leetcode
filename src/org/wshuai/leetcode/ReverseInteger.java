package org.wshuai.leetcode;

/**
 * Created by Wei on 08/12/2016.
 * #0007 https://leetcode.com/problems/reverse-integer/
 */
public class ReverseInteger {
	// time O(n)
	public int reverse(int x) {
		long rev = 0;
		while(x != 0){
			rev = rev * 10 + x % 10;
			x /= 10;
			if(rev < Integer.MIN_VALUE || rev > Integer.MAX_VALUE){
				return 0;
			}
		}
		return (int)rev;
	}
}
