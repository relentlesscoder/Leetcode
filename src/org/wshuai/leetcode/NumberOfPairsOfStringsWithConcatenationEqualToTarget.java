package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/28/2023.
 * #2023 https://leetcode.com/problems/number-of-pairs-of-strings-with-concatenation-equal-to-target/
 */
public class NumberOfPairsOfStringsWithConcatenationEqualToTarget {

	// time O(n * t), space O(n)
	public int numOfPairs(String[] nums, String target) {
		int res = 0;
		Map<String, Integer> count = new HashMap<>();
		for (String s : nums) {
			count.put(s, count.getOrDefault(s, 0) + 1);
		}
		for (int i = 1; i < target.length(); i++) {
			String s1 = target.substring(0, i), s2 = target.substring(i);
			if (s1.equals(s2)) {
				int c1 = count.getOrDefault(s1, 0);
				res += c1 * (c1 - 1);
				// 2 -> 1, 3 -> 2 + 1, 4 -> 3 + 2 + 1, 5 -> 4 + 3 + 2 + 1 ...
				// thus total pairs p = (1 + n - 1) * (n - 1) / 2 = n * (n - 1) /2
				// we need to multiply it by 2 since the each pair can be reversed, total number of pairs is n * (n - 1)
			} else {
				res += count.getOrDefault(s1, 0) * count.getOrDefault(s2, 0);
			}
		}
		return res;
	}
}
