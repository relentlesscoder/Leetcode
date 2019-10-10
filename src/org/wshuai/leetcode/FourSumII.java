package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/18/16.
 * #454 https://leetcode.com/problems/4sum-ii/
 */
public class FourSumII {
	public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
		int len = A.length;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				int sum = A[i] + B[j];
				if (map.containsKey(sum)) {
					int cnt = map.get(sum);
					map.put(sum, cnt + 1);
				} else {
					map.put(sum, 1);
				}
			}
		}

		int res = 0;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				int diff = 0 - C[i] - D[j];
				if (map.containsKey(diff)) {
					res += map.get(diff);
				}
			}
		}
		return res;
	}
}
