package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/31/16.
 * #324 https://leetcode.com/problems/wiggle-sort-ii/
 */
public class WiggleSortII {
	//O(n) time, O(n) space
	public void wiggleSort(int[] nums) {
		if (nums == null || nums.length == 0) {
			return;
		}
		int len = nums.length;
		int mid = (len - 1) / 2;
		int right = len - 1;
		int[] tmp = Arrays.copyOf(nums, len);
		Arrays.sort(tmp);
		for (int i = 0; i < len; i++) {
			if (i % 2 == 0) {
				nums[i] = tmp[mid--];
			} else {
				nums[i] = tmp[right--];
			}
		}
	}
}
