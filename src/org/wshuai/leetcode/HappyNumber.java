package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 10/26/2016.
 * #202 https://leetcode.com/problems/happy-number/
 */
public class HappyNumber {
	public boolean isHappy(int n) {
		Set<Integer> s = new HashSet<Integer>();

		while (!s.contains(n)) {
			s.add(n);

			n = getSum(n);
			if (n == 1) {
				return true;
			}
		}

		return false;
	}

	private int getSum(int n) {
		int sum = 0;

		while (n > 0) {
			int x = n % 10;
			sum += x * x;
			n = n / 10;
		}

		return sum;
	}
}
