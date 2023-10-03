package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/02/2023.
 * #2190 https://leetcode.com/problems/most-frequent-number-following-key-in-an-array/
 */
public class MostFrequentNumberFollowingKeyInAnArray {

	// time O(n), space O(n)
	public int mostFrequent(int[] nums, int key) {
		int res = -1, maxFreq = 0;
		Map<Integer, Integer> count = new HashMap<>();
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] == key) {
				count.put(nums[i + 1], count.getOrDefault(nums[i + 1], 0) + 1);
				if (count.get(nums[i + 1]) > maxFreq) {
					maxFreq = count.get(nums[i + 1]);
					res = nums[i + 1];
				}
			}
		}
		return res;
	}
}
