package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 8/19/19.
 * #1128 https://leetcode.com/problems/number-of-equivalent-domino-pairs/
 */
public class NumberOfEquivalentDominoPairs {
	public int numEquivDominoPairs(int[][] dominoes) {
		int count = 0;
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < dominoes.length; i++) {
			int max = dominoes[i][0] >= dominoes[i][1] ? dominoes[i][0] : dominoes[i][1];
			int min = dominoes[i][0] < dominoes[i][1] ? dominoes[i][0] : dominoes[i][1];
			String key = min + "," + max;
			int num = map.getOrDefault(key, 0);
			count += num;
			map.put(key, num + 1);
		}
		return count;
	}
}
