package org.wshuai.leetcode;

/**
 * Created by Wei on 11/14/16.
 * #300 https://leetcode.com/problems/longest-increasing-subsequence/
 */
public class LongestIncreasingSubsequence {
	//O(n^2), DP
	public int lengthOfLIS(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int max = 1;
		int len = nums.length;
		int[] aux = new int[len];
		aux[0] = 1;
		for (int i = 1; i < len; i++) {
			int cmax = 1;
			for (int j = i - 1; j >= 0; j--) {
				if (nums[i] > nums[j]) {
					int cl = aux[j] + 1;
					cmax = cl > cmax ? cl : cmax;
				}
				aux[i] = cmax;
				max = cmax > max ? cmax : max;
			}
		}
		return max;
	}
}
