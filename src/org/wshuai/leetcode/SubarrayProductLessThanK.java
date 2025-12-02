package org.wshuai.leetcode;

/**
 * Created by Wei on 09/04/2019.
 * #0713 https://leetcode.com/problems/subarray-product-less-than-k/
 */
public class SubarrayProductLessThanK {

    // time O(n), space O(1)
    public int numSubarrayProductLessThanK(int[] nums, int k) {
		if (k <= 1) {
			return 0;
		}
		int res = 0, n = nums.length;
		for (int i = 0, j = 0, prod = 1; i < n; i++) {
			prod *= nums[i];
			while (prod >= k) {
				prod /= nums[j++];
			}
			res += i - j + 1;
		}
		return res;
    }
}
