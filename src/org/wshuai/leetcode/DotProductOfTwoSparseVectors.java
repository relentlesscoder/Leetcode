package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/03/2020.
 * #1570 https://leetcode.com/problems/dot-product-of-two-sparse-vectors/
 */
public class DotProductOfTwoSparseVectors {

	// time O(n), space O(n)
	private class SparseVector {

		private Map<Integer, Integer> map;

		SparseVector(int[] nums) {
			map = new HashMap<>();
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] == 0) {
					continue;
				}
				map.put(i, nums[i]);
			}
		}

		// Return the dotProduct of two sparse vectors
		public int dotProduct(SparseVector vec) {
			int dotProduct = 0;
			for (int key : map.keySet()) {
				int val = vec.get(key);
				if (val != 0) {
					dotProduct += val * map.get(key);
				}
			}
			return dotProduct;
		}

		public int get(int key) {
			return this.map.getOrDefault(key, 0);
		}
	}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);
}
