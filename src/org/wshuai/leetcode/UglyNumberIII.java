package org.wshuai.leetcode;

/**
 * Created by Wei on 11/13/2019.
 * #1201 https://leetcode.com/problems/ugly-number-iii/
 */
public class UglyNumberIII {
	// stupid math question
	// https://leetcode.com/problems/ugly-number-iii/discuss/387539/cpp-Binary-Search-with-picture-and-Binary-Search-Template
	public int nthUglyNumber(int n, int a, int b, int c) {
		int low = 1, high = Integer.MAX_VALUE, mid;

		while (low < high) {
			mid = low + (high - low) / 2;

			if (count((a), b, c, mid) < n){
				low = mid + 1;
			}else{
				high = mid;
			}
		}

		return high;
	}

	private long gcd(long a, long b) {
		if (a == 0){
			return b;
		}

		return gcd(b % a, a);
	}

	private long lcm(long a, long b) {
		return (a * b) / gcd(a, b);
	}

	private int count(long a, long b, long c, long num) {
		return (int) ((num / a) + (num / b) + (num / c)
			- (num / lcm(a, b))
			- (num / lcm(b, c))
			- (num / lcm(a, c))
			+ (num / lcm(a, lcm(b, c))));
	}
}
