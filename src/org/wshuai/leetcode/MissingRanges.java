package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/26/16.
 * #163 https://leetcode.com/problems/missing-ranges/
 */
public class MissingRanges {
	public List<String> findMissingRanges(int[] nums, int lower, int upper) {
		List<String> lst = new ArrayList<String>();
		//Corner case 1
		if (lower > upper) {
			return lst;
		}
		//Corner case 2
		if (nums == null || nums.length == 0) {
			if (lower == upper) {
				lst.add(Integer.toString(lower));
			} else {
				lst.add(Integer.toString(lower) + "->" + Integer.toString(upper));
			}
			return lst;
		}

		int len = nums.length;
		//If lower is the left boundary
		if (lower < nums[0]) {
			if (lower == nums[0] - 1) {
				lst.add(Integer.toString(lower));
			} else {
				lst.add(Integer.toString(lower) + "->" + Integer.toString(nums[0] - 1));
			}
		}

		for (int i = 0; i < len - 1; i++) {
			int curr = nums[i];
			int next = nums[i + 1];
			if (curr == next || curr == next - 1) {
				continue;
			}
			if (curr >= upper) {
				break;
			}
			if (next <= lower) {
				continue;
			}
			int left = curr + 1;
			int right = next - 1;
			if (curr < lower && lower < next) {
				left = lower;
			}
			if (curr < upper && upper < next) {
				right = upper;
			}
			if (left == right) {
				lst.add(Integer.toString(left));
			} else {
				lst.add(Integer.toString(left) + "->" + Integer.toString(right));
			}
		}

		//If upper is the right boundary
		if (upper > nums[len - 1]) {
			if (upper == nums[len - 1] + 1) {
				lst.add(Integer.toString(upper));
			} else {
				lst.add(Integer.toString(nums[len - 1] + 1) + "->" + Integer.toString(upper));
			}
		}

		return lst;
	}
}
