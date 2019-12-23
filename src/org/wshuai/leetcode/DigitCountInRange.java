package org.wshuai.leetcode;

/**
 * Created by Wei on 12/23/2019.
 * #1067 https://leetcode.com/problems/digit-count-in-range/
 */
public class DigitCountInRange {
	// https://leetcode.com/problems/digit-count-in-range/discuss/303462/Simple-iterative-Java-solution-extending-number-of-digits-one
	// https://leetcode.com/articles/number-of-digit-one/
	public int digitsCount(int d, int low, int high) {
		return countDigit(high, d) - countDigit(low-1, d);
	}

	/*
	 0      100     200
	 10    110     210
	 11    111     211
	 ...    ...       ...
	 19    119    219

	 if number is 212, the number of 1s at ten's place is:
	 1. (n / divider) * i -> total number of 1s in group less to
	 200 (the first left two columns) -> (212 / 100) * 10
	 2. Math.min(Math.max(n % divider - i + 1, 0), i) -> the
	 total number of 1s between 200 to 212.
	 12 - 10 + 1 = 3 is 210, 211, 212.
	 why we need to use Math.max(..., 0) ?
	 think about example 205 -> the remainder is less than 10 thus
	 the count is 0.
	 why we need to use Math.min(Math.max(..., 0), i) ?
	 think about example 244 -> the max number is 10 within
	 the current group.
	 */
	public int countDigit(int n, int d) {
		if(n < 0 || n < d) {
			return 0;
		}

		int count = 0;
		for(long i = 1; i <= n; i*= 10) {
			long divider = i * 10;
			count += (n / divider) * i;

			if (d > 0) {
				// same as problem #233
				count += Math.min(Math.max(n % divider - d * i + 1, 0), i); // comment1: tailing number need to be large than d *  i to qualify.
			} else {
				if(n / divider > 0) {// n == 0, no need to check
					// i == 1, 0 should be excluded
					if(i > 1) {  // exclude the left most column - starts with leading 0
						count -= i;
						count += Math.min(n % divider + 1, i);
					}
				}
			}
		}

		return count;
	}
}
