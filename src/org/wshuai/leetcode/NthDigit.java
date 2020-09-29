package org.wshuai.leetcode;

/**
 * Created by Wei on 09/21/2016.
 * #0400 https://leetcode.com/problems/nth-digit/
 */
public class NthDigit {

	// time O(log(n))
	// https://leetcode.com/problems/nth-digit/discuss/88363/Java-solution
	public int findNthDigit(int n) {
		int start = 1, len = 1;
		long count = 9;
		while(n > len * count){
			n -= len * count;
			len += 1;
			count *= 10;
			start *= 10;
		}
		start += (n - 1) / len;
		String str = String.valueOf(start);
		return str.charAt((n - 1) % len) - '0';
	}

	/**
	 1 ~ 9 include 9 one digit number;
	 10 ~ 99 include 90 two digits number;
	 100 ~ 999 include 900 three digits number;
	 1000 ~ 9999 include 9000 four digits number;
	 */
}
