package org.wshuai.leetcode;

/**
 * Created by Wei on 09/27/2023.
 * #1909 https://leetcode.com/problems/remove-one-element-to-make-the-array-strictly-increasing/
 */
public class RemoveOneElementToMakeTheArrayStrictlyIncreasing {

	// time O(n), space O(1)
	public boolean canBeIncreasing(int[] nums) {
		int n = nums.length, last = nums[0], invalid = 0;
		for (int i = 1; i < n; i++) {
			if (last >= nums[i]) {
				if (invalid++ == 1) {
					return false;
				}
				if (i == 1 || nums[i] > nums[i - 2]) { // if [i - 2] is less than [i] we can remove nums[i - 1] to fix the current issue, for example [1,2,10,5,7].
					last = nums[i];
				} // if [i - 2] is greater than or equal to [i], the best we can do is try removing nums[i], for example it is working for case [105,924,32,968] but not for case [2,3,1,2].
			} else {
				last = nums[i];
			}
		}
		return true;
	}
}
