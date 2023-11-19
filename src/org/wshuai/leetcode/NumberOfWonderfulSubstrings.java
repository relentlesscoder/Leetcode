package org.wshuai.leetcode;

/**
 * Created by Wei on 09/22/2023.
 * #1915 https://leetcode.com/problems/number-of-wonderful-substrings/
 */
public class NumberOfWonderfulSubstrings {

	// time O(n), space O(1)
	public long wonderfulSubstrings(String word) {
		long[] count = new long[1 << 10];
		count[0] = 1;
		int mask = 0;
		long res = 0;
		for (char c : word.toCharArray()) {
			mask ^= 1 << (c - 'a');
			res += count[mask];
			for (int i = 0; i < 10; i++) {
				int nextOdd = mask ^ (1 << i);
				res += count[nextOdd];
			}
			count[mask]++;
		}
		return res;
	}
}
