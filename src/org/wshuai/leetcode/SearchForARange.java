package org.wshuai.leetcode;

/**
 * Created by Wei on 10/11/2016.
 * #34 https://leetcode.com/problems/search-for-a-range/
 */
public class SearchForARange {
	public int[] searchRange(int[] nums, int target) {
		int[] result = new int[2];
		result[0] = -1;
		result[1] = -1;
		if (nums == null || nums.length == 0) {
			return result;
		}

		int len = nums.length;
		int i = 0;
		int j = len - 1;
		while (i <= j) {
			int m = i + (j - i) / 2;
			int val = nums[m];
			if (val == target) {
				int l = m;
				while (l >= 0 && nums[l] == val) {
					l--;
				}
				int r = m;
				while (r < len && nums[r] == val) {
					r++;
				}
				result[0] = l + 1;
				result[1] = r - 1;
				break;
			} else if (val > target) {
				j = m - 1;
				while (j >= 0 && nums[i] == val) {
					j--;
				}
			} else {
				i = m + 1;
				while (i < len && nums[i] == val) {
					i++;
				}
			}
		}

		return result;
	}
}
