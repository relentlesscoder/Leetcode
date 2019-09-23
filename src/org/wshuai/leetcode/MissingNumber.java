package org.wshuai.leetcode;

/**
 * Created by Wei on 10/8/16.
 */
public class MissingNumber {
	public int missingNumber(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int sum = 0;
		int len = nums.length;
		for (int i = 0; i < len; i++) {
			sum += nums[i];
		}
		int t = (int) (len * (len + 1) / 2.0);
		return t - sum;
	}
}
