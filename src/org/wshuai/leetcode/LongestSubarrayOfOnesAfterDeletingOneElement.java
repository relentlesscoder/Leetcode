package org.wshuai.leetcode;

/**
 * Created by Wei on 06/28/2020.
 * #1493 https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/
 */
public class LongestSubarrayOfOnesAfterDeletingOneElement {

	// time O(n)
	public int longestSubarray(int[] nums) {
		int res = 0, n = nums.length, left = 0, right = 0;
		int[] fromLeft = new int[n];
		for(int i = 0; i < n; i++){
			fromLeft[i] = left;
			left = nums[i] == 1 ? left + 1 : 0;
		}
		for(int i = n - 1; i >= 0; i--){
			res = Math.max(res, right + fromLeft[i]);
			right = nums[i] == 1 ? right + 1 : 0;
		}
		return res;
	}
}
