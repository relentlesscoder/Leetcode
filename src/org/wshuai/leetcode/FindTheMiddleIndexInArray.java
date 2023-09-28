package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/27/2023.
 * #1991 https://leetcode.com/problems/find-the-middle-index-in-array/
 */
public class FindTheMiddleIndexInArray {

	// time O(n), space O(1)
	public int findMiddleIndexTwoPasses(int[] nums) {
		int sum = 0, sumOfLeft = 0;
		for (int num : nums) {
			sum += num;
		}
		// sum - nums[i] = 2 * sumOfLeft
		for (int i = 0; i < nums.length; i++) {
			if ((sumOfLeft << 1) == sum - nums[i]) {
				return i;
			}
			sumOfLeft += nums[i];
		}
		return -1;
	}

	// time O(n), space O(n)
	public int findMiddleIndexOnePassWithHashMap(int[] nums) {
		int sumOfLeft = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			map.putIfAbsent((sumOfLeft << 1) + nums[i], i);
			sumOfLeft += nums[i];
		}
		return map.getOrDefault(sumOfLeft, -1);
	}
}
