package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/21/2023.
 * #2007 https://leetcode.com/problems/find-original-array-from-doubled-array/
 */
public class FindOriginalArrayFromDoubledArray {

	// time O(n * log(n)), space O(n)
	public int[] findOriginalArray(int[] changed) {
		int n = changed.length;
		if (n % 2 == 1) {
			return new int[0];
		}
		int m = n / 2, index = 0;
		int[] res = new int[m];
		Arrays.sort(changed);
		Map<Integer, Integer> needed = new HashMap<>();
		for (int i = 0; i < n; i++) {
			if (needed.getOrDefault(changed[i], 0) > 0) {
				res[index++] = changed[i] >> 1;
				needed.put(changed[i], Math.max(needed.getOrDefault(changed[i], 0) - 1, 0));
			} else {
				needed.put(changed[i] << 1, needed.getOrDefault(changed[i] << 1, 0) + 1);
			}
		}
		return index == m ? res : new int[0];
	}
}
