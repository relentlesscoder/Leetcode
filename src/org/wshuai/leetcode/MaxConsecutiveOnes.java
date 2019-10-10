package org.wshuai.leetcode;

/**
 * Created by Wei on 2/15/17.
 * #485 https://leetcode.com/problems/max-consecutive-ones/
 */
public class MaxConsecutiveOnes {
	public int findMaxConsecutiveOnes(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int len = nums.length;
		int max = 0;
		for (int i = 0; i < len; i++) {
			if (nums[i] == 0) {
				continue;
			}
			int cnt = 0;
			int j = i;
			while (j < len && nums[j] == 1) {
				cnt++;
				j++;
			}
			max = cnt > max ? cnt : max;
			i = j;
		}
		return max;
	}
}
