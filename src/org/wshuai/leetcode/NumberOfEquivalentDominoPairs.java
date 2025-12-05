package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 08/19/2019.
 * #1128 https://leetcode.com/problems/number-of-equivalent-domino-pairs/
 */
public class NumberOfEquivalentDominoPairs {

	// time O(n), space O(100)
	public int numEquivDominoPairs(int[][] dominoes) {
		int res = 0;
		int[] map = new int[100];
		for (int[] d : dominoes) {
			int idx = d[0] * 10 + d[1];
			res += map[idx];
			if (d[0] != d[1]) {
				res += map[d[1] * 10 + d[0]];
			}
			map[idx]++;
		}
		return res;
	}
}
