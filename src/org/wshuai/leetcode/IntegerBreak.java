package org.wshuai.leetcode;

/**
 * Created by Wei on 11/2/2016.
 * #343 https://leetcode.com/problems/integer-break/
 * http://www.cnblogs.com/grandyang/p/5411919.html
 */
public class IntegerBreak {
	// 2 -> 1+1
	// 3 -> 2+1
	// 4 -> 2+2
	// 5 -> 3+2
	// 6 -> 3+3
	// 7 -> 3+4
	// 8 -> 3+3+2
	// 9 -> 3+3+3
	//10 -> 3+3+4
	public int integerBreak(int n) {
		if (n <= 1) {
			return 0;
		}
		if (n == 2 || n == 3) {
			return n - 1;
		}
		int r = 1;
		while (n > 4) {
			n -= 3;
			r *= 3;
		}
		return r * n;
	}
}
