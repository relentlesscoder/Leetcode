package org.wshuai.leetcode;

/**
 * Created by Wei on 8/19/19.
 * #674 https://leetcode.com/problems/longest-continuous-increasing-subsequence/
 */
public class LongestContinuousIncreasingSubsequence {

	public int findLengthOfLCIS(int[] nums) {
		int ans = 0, anchor = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i > 0 && nums[i - 1] >= nums[i]) {
				anchor = i;
			}
			ans = Math.max(ans, i - anchor + 1);
		}
		return ans;
	}

	public int findLengthOfLCISDP1D(int[] nums) {
		int len = nums.length;
		if (len == 0 || len == 1) {
			return len;
		}
		int max = 0;
		for (int i = 0; i < len - 1; i++) {
			int[] aux = new int[len];
			for (int j = i; j < len; j++) {
				if (j == i) {
					aux[j] = 1;
				} else {
					aux[j] = aux[j - 1] > 0 && nums[j] > nums[j - 1] ? aux[j - 1] + 1 : 0;
				}
				max = aux[j] > max ? aux[j] : max;
			}
		}
		return max;
	}

	//MLE
	public int findLengthOfLCISDP2D(int[] nums) {
		int len = nums.length;
		if (len == 0 || len == 1) {
			return len;
		}
		int max = 0;
		int[][] aux = new int[len][len];
		for (int i = 0; i < len - 1; i++) {
			for (int j = i; j < len; j++) {
				if (j == i) {
					aux[i][j] = 1;
				} else {
					aux[i][j] = aux[i][j - 1] > 0 && nums[j] > nums[j - 1] ? aux[i][j - 1] + 1 : 0;
				}
				max = aux[i][j] > max ? aux[i][j] : max;
			}
		}
		return max;
	}


}
