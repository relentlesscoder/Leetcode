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
			// ?
			} else {
				if(n / divider > 0) {
					if(i > 1) {  // comment2: when d == 0, we need avoid to take numbers like 0xxxx into account.
						count -= i;
						count += Math.min(n % divider + 1, i);
					}
				}
			}
		}

		return count;
	}
}
