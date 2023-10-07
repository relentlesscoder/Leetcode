package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/03/2020.
 * #1570 https://leetcode.com/problems/dot-product-of-two-sparse-vectors/
 */
public class DotProductOfTwoSparseVectors {

	private Map<Integer, Integer> map;

	DotProductOfTwoSparseVectors(int[] nums) {
		map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			map.put(i, nums[i]);
		}
	}

	// Return the dotProduct of two sparse vectors
	public int dotProduct(DotProductOfTwoSparseVectors vec) {
		int res = 0;
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int val = vec.get(entry.getKey());
			if (val == 0) {
				continue;
			}
			res += val * entry.getValue();
		}
		return res;
	}

	public int get(int key) {
		return map.getOrDefault(key, 0);
	}
}
