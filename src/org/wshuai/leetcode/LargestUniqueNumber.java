package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 8/8/19.
 * #1133 https://leetcode.com/problems/largest-unique-number/
 */
public class LargestUniqueNumber {
	public int largestUniqueNumber(int[] A) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i : A) {
			map.put(i, map.getOrDefault(i, 0) + 1);
		}
		int max = -1;
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 1) {
				int key = entry.getKey();
				max = key > max ? key : max;
			}
		}
		return max;
	}
}
