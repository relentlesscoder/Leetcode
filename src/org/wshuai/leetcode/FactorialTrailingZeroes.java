package org.wshuai.leetcode;

/**
 * Created by Wei on 10/08/2016.
 * #0172 https://leetcode.com/problems/factorial-trailing-zeroes/
 */
public class FactorialTrailingZeroes {
	// time O(log(n))
	// https://leetcode.com/problems/factorial-trailing-zeroes/discuss/196311/topic
	// number of factor 5 always >= that of 2
	public int trailingZeroes(int n) {
		int res = 0;
		while(n > 0){
			res += n / 5;
			n /= 5;
		}
		return res;
	}
}
