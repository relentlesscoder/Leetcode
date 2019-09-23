package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/21/2016.
 * #16 https://leetcode.com/problems/3sum-closest/
 */
public class ThreeSumClosest {
	//O(n^2)
	public int threeSumClosest(int[] nums, int target) {
		int res = 0;
		int diff = Integer.MAX_VALUE;
		Arrays.sort(nums);
		int len = nums.length;
		for (int i = 0; i < len - 2; i++) {
			int left = i + 1;
			int right = len - 1;
			while (left < right) {
				int sum = nums[i] + nums[left] + nums[right];
				if (sum == target) {
					return sum;
				}
				int cdiff = Math.abs(sum - target);
				if (cdiff < diff) {
					diff = cdiff;
					res = sum;
				}
				if (sum < target) {
					left++;
				} else {
					right--;
				}
			}
		}
		return res;
	}

	//O(n^3)
	public int threeSumClosestBF(int[] nums, int target) {
		int cls = 0;
		int diff = Integer.MAX_VALUE;

		int len = nums.length;
		int len1 = len - 1;
		int len2 = len - 2;
		for (int i = 0; i < len2; i++) {
			for (int j = i + 1; j < len1; j++) {
				for (int k = j + 1; k < len; k++) {
					int sum = nums[i] + nums[j] + nums[k];
					int cDiff = Math.abs(sum - target);
					if (cDiff < diff) {
						diff = cDiff;
						cls = sum;
						if (diff == 0) {
							return cls;
						}
					}
				}
			}
		}

		return cls;
	}
}
