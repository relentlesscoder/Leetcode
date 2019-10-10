package org.wshuai.leetcode;

import java.util.TreeSet;

/**
 * Created by Wei on 10/10/16.
 */
public class ContainsDuplicateIII {

	//Use TreeSet, 46ms
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		if (nums == null || nums.length <= 1 || k <= 0 || t < 0) {
			return false;
		}

		TreeSet<Integer> treeSet = new TreeSet<Integer>();

		for (int i = 0; i < nums.length; i++) {
			Integer floor = treeSet.floor(nums[i] + t);
			Integer ceil = treeSet.ceiling(nums[i] - t);

			if ((floor != null && floor >= nums[i])
					|| (ceil != null && ceil <= nums[i])) {
				return true;
			}

			treeSet.add(nums[i]);

			if (i >= k) {
				treeSet.remove(nums[i - k]);
			}
		}

		return false;
	}

	// ETL
	public boolean containsNearbyAlmostDuplicateNaive(int[] nums, int k, int t) {
		if (nums == null || nums.length == 0 || k < 0) {
			return false;
		}

		int len = nums.length;
		int left = 0;
		long x = (long) t;
		while (left < len) {
			int right = left + 1;
			while (right < len && right - left <= k) {
				if (Math.abs((long) (nums[left] - nums[right])) <= x) {
					return true;
				}
				right++;
			}
			left++;
		}

		return false;
	}
}
