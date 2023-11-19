package org.wshuai.leetcode;

/**
 * Created by Wei on 10/02/2023.
 * #2057 https://leetcode.com/problems/smallest-index-with-equal-value/description/
 */
public class SmallestIndexWithEqualValue {

	// time O(n), space O(1)
	public int smallestEqual(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			if (i % 10 == nums[i]) {
				return i;
			}
		}
		return -1;
	}
}
