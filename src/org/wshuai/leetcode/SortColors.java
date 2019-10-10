package org.wshuai.leetcode;

/**
 * Created by Wei on 9/8/16.
 */
public class SortColors {
	public static void sortColors(int[] nums) {
		if (nums == null || nums.length == 0) {
			return;
		}
		int len = nums.length;
		int left = 0;
		int right = len - 1;
		int m = -1;
		int n = len;
		while (left < n) {
			int val = nums[left];
			if (val == 0) {
				m++;
				nums[left] = 1;
				nums[m] = 0;
				left++;
			}
			if (val == 1) {
				left++;
			}
			if (val == 2) {
				while (right >= 0 && nums[right] == 2) {
					if (left == right) {
						break;
					}
					right--;
					n--;
				}
				if (right >= 0) {
					n--;
					right--;
					nums[left] = nums[n];
					nums[n] = 2;
				}
			}
		}
	}
}
