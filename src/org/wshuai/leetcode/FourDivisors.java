package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 03/23/2020.
 * #1390 https://leetcode.com/problems/four-divisors/
 */
public class FourDivisors {
	// time O(n*log(d))
	public int sumFourDivisors(int[] nums) {
		int sum = 0;
		Map<Integer, Integer> valid = new HashMap<>();
		Set<Integer> invalid = new HashSet<>();
		for (int num : nums) {
			if (invalid.contains(num)) {
				continue;
			}
			if (valid.containsKey(num)) {
				sum += valid.get(num);
				continue;
			}
			int cur = 1 + num, count = 2;
			for (int i = 2; i * i <= num; i++) {
				if (num % i == 0) {
					int d = num / i;
					count++;
					cur += i;
					if (i != d) {
						count++;
						cur += d;
					}
					if (count > 4) {
						break;
					}
				}
			}
			if (count == 4) {
				sum += cur;
				valid.put(num, cur);
			} else {
				invalid.add(num);
			}
		}
		return sum;
	}
}
