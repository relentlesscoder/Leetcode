package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/06/2020.
 * #1577 https://leetcode.com/problems/number-of-ways-where-square-of-number-is-equal-to-product-of-two-numbers/
 */
public class NumberOfWaysWhereSquareOfNumberIsEqualToProductOfTwoNumbers {

	// time O(m*n)
	public int numTriplets(int[] nums1, int[] nums2) {
		int res = 0;
		for(int i = 0; i < nums1.length; i++){
			res += countTriplets(1L * nums1[i] * nums1[i], nums2);
		}
		for(int i = 0; i < nums2.length; i++){
			res += countTriplets(1L * nums2[i] * nums2[i], nums1);
		}
		return res;
	}

	private int countTriplets(long product, int[] nums){
		int res = 0;
		Map<Long, Integer> map = new HashMap<>();
		for(int i = 0; i < nums.length; i++){
			long cur = (long)nums[i];
			if(product % cur == 0){
				res += map.getOrDefault(product / cur, 0);
			}
			map.put(cur, map.getOrDefault(cur, 0) + 1);
		}
		return res;
	}
}
