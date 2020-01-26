package org.wshuai.leetcode;

/**
 * Created by Wei on 07/02/2017.
 * #0233 https://leetcode.com/problems/number-of-digit-one/
 */
public class NumberOfDigitOne {
	// time O(log(n))
	// Stupid math problem, see https://leetcode.com/articles/number-of-digit-one/
	public int countDigitOne(int n) {
		int count = 0;
		for (long i = 1; i <= n; i *= 10) {
			long divider = i * 10;
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
			 total number of 1s at 10th position between 200 to 212.
			 12 - 10 + 1 = 3 is 210, 211, 212.
			 why we need to use Math.max(..., 0) ?
			 think about example 205 -> the remainder is less than 10 thus
			 the count is 0.
			 why we need to use Math.min(Math.max(..., 0), i) ?
			 think about example 244 -> the max number is 10 within
			 the current group.
			 */
			count += (n / divider) * i + Math.min(Math.max(n % divider - i + 1, 0), i);
		}
		return count;
	}
}
