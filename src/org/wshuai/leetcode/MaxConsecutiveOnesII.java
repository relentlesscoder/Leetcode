package org.wshuai.leetcode;

/**
 * Created by Wei on 02/15/2017.
 * #0487 https://leetcode.com/problems/max-consecutive-ones-ii/
 */
public class MaxConsecutiveOnesII {

	// time O(n), space O(1)
	public int findMaxConsecutiveOnes(int[] nums) {
		int res = 0, zeros = 0;
		for (int i = 0, j = 0; i < nums.length; i++) {
			zeros += 1 - nums[i];
			while (zeros > 1) {
				zeros -= 1 - nums[j++];
			}
			res = Math.max(res, i - j + 1);
		}
		return res;
	}

	// time O(n), space O(n)
	public int findMaxConsecutiveOnesCountLeftAndRight(int[] nums) {
		int res = 0, n = nums.length, count = 0;
		int[] onesFromLeft = new int[n];
		for (int i = 1; i < n; i++) {
			count = nums[i - 1] == 1 ? count + 1 : 0;
			onesFromLeft[i] = count;
		}
		count = 0;
		for (int i = n - 1; i >= 0; i--) {
			res = Math.max(res, onesFromLeft[i] + count + 1);
			count = nums[i] == 0 ? 0 : count + 1;
		}
		return res;
	}

}
