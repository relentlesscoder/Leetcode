package org.wshuai.leetcode;

/**
 * Created by Wei on 9/5/19.
 * #724 https://leetcode.com/problems/find-pivot-index/
 */
public class FindPivotIndex {
	public int pivotIndex(int[] nums) {
		if (nums.length == 0) {
			return -1;
		}
		int sum = 0;
		for (int i : nums) {
			sum += i;
		}
		int left = 0;
		for (int i = 0; i < nums.length; i++) {
			left += i > 0 ? nums[i - 1] : 0;
			int curr = sum - nums[i];
			if (curr % 2 == 0 && left == curr / 2) {
				return i;
			}
		}
		return -1;
	}
}
