package org.wshuai.leetcode;

/**
 * Created by Wei on 9/21/2016.
 */
public class NthDigit {
	public int findNthDigit(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("Invalid input.");
		}
		long len = 1;
		long time = 1;
		long s = 0;
		while (n - 9 * len * time > 0) {
			n -= 9 * len * time;
			s += 9 * time;
			len++;
			time *= 10;
		}
		if (n == 0) {
			return 9;
		} else {
			long x = n / len;
			long y = n % len;
			long num = s + x;
			if (y == 0) {
				return (int) (num > 9 ? num % 10 : num);
			} else {
				num++;
				long pos = len - y;
				while (pos > 0) {
					num = num / 10;
					pos--;
				}
				return (int) (num % 10);
			}
		}
	}
}
