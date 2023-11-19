package org.wshuai.leetcode;

import java.util.Collections;
import java.util.List;

/**
 * Created by Wei on 09/27/2023.
 * #2824 https://leetcode.com/problems/count-pairs-whose-sum-is-less-than-target/
 */
public class CountPairsWhoseSumIsLessThanTarget {

	// time O(n), space O(1)
	public int countPairs(List<Integer> nums, int target) {
		Collections.sort(nums);
		int res = 0, left = 0, right = nums.size() - 1;
		while (left < right) {
			if (nums.get(left) + nums.get(right) < target) {
				res += (right - left);
				left++;
			} else {
				right--;
			}
		}
		return res;
	}
}
