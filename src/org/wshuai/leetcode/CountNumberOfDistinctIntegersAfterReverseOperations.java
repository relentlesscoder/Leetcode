package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 11/06/2023.
 * #2442 https://leetcode.com/problems/count-number-of-distinct-integers-after-reverse-operations/
 */
public class CountNumberOfDistinctIntegersAfterReverseOperations {

	// time O(n), space O(n)
	public int countDistinctIntegers(int[] nums) {
		int res = 0;
		Set<Integer> set = new HashSet<>();
		for (int num : nums) {
			set.add(num);
			set.add(reverse(num));
		}
		return set.size();
	}

	private int reverse(int num) {
		int res = 0;
		while (num > 0) {
			int d = num % 10;
			res = res * 10 + d;
			num /= 10;
		}
		return res;
	}
}
