package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/28/2023.
 * #2342 https://leetcode.com/problems/max-sum-of-a-pair-with-equal-sum-of-digits/
 */
public class MaxSumOfAPairWithEqualSumOfDigits {

	// time O(n * log(MAX)), space O(n)
	public int maximumSum(int[] nums) {
		int res = -1, n = nums.length;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			// Calculate the digit sum for nums[i]
			int val = nums[i], sum = 0;
			while (val > 0) {
				sum += val % 10;
				val /= 10;
			}
			if (map.containsKey(sum)) {
				res = Math.max(res, nums[i] + map.get(sum));
			}
			// Update map[sum] to record the maximum value with digit sum = sum
			map.put(sum, Math.max(map.getOrDefault(sum, -1), nums[i]));
		}
		return res;
	}
}
