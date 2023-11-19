package org.wshuai.leetcode;

/**
 * Created by Wei on 09/12/2019.
 * #1004 https://leetcode.com/problems/max-consecutive-ones-iii/
 */
public class MaxConsecutiveOnesIII {

	// time O(n), space O(1)
	public int longestOnes(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int res = 0, n = nums.length;
		for(int i = 0, j = 0, zeros = 0; j < n; j++){
			zeros += nums[j] == 0 ? 1 : 0;
			while(zeros > k){
				zeros -= nums[i++] == 0 ? 1 : 0;
			}
			res = Math.max(res, j - i + 1);
		}
		return res;
	}
}
