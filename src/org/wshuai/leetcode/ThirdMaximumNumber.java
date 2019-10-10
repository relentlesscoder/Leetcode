package org.wshuai.leetcode;

/**
 * Created by Wei on 11/2/2016.
 * #414 https://leetcode.com/problems/third-maximum-number/
 */
public class ThirdMaximumNumber {

	//4ms
	public int thirdMax(int[] nums) {
		long first = Long.MIN_VALUE;
		long second = Long.MIN_VALUE;
		long third = Long.MIN_VALUE;
		int len = nums.length;
		for (int i = 0; i < len; i++) {
			long val = nums[i];
			if (val > first) {
				third = second;
				second = first;
				first = val;
			} else if (val < first && val > second) {
				third = second;
				second = val;
			} else if (val < second && val > third) {
				third = val;
			}
		}
		int res = (int) (third == Long.MIN_VALUE ? first : third);
		return res;
	}
}
