package org.wshuai.leetcode;

/**
 * Created by Wei on 09/21/2016.
 *
 */
public class NthDigit {
	// time 
	// https://leetcode.com/problems/nth-digit/discuss/88363/Java-solution
	public int findNthDigit(int n) {
		int len = 1, start = 1;
		long base = 9;
		while(n > len * base){
			n -= len * base;
			base *= 10;
			start *= 10;
			len += 1;
		}
		start += (n - 1) / len;
		String s = Integer.toString(start);
		return Character.getNumericValue(s.charAt((n - 1) % len));
	}
}
