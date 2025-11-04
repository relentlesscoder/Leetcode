package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/09/2019.
 * #0904 https://leetcode.com/problems/fruit-into-baskets/
 */
public class FruitIntoBaskets {

	// time O(n), space O(n)
	public int totalFruit(int[] fruits) {
		int res = 0, n = fruits.length;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0, j = 0; j < n; j++) {
			map.merge(fruits[j], 1, Integer::sum);
			while (map.size() > 2) {
				int count = map.get(fruits[i]);
				if (count == 1) {
					map.remove(fruits[i++]);
				} else {
					map.put(fruits[i++], count - 1);
				}
			}
			res = Math.max(res, j - i + 1);
		}
		return res;
	}
}
