package org.wshuai.leetcode;

/**
 * Created by Wei on 11/14/2016.
 * #0300 https://leetcode.com/problems/longest-increasing-subsequence/
 */
public class LongestIncreasingSubsequence {

	// time O(n*log(n))
	// https://github.com/RodneyShag/LeetCode_solutions/blob/master/Solutions/Longest%20Increasing%20Subsequence.md
	// https://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
	public int lengthOfLIS(int[] nums) {
		if(nums == null || nums.length == 0){
			return 0;
		}
		int res = 0, n = nums.length;
		int[] sorted = new int[n];
		for(int num : nums){
			int left = 0, right = res;
			while(left < right){
				int mid = left + (right - left) / 2;
				if(sorted[mid] < num){
					left = mid + 1;
				}else{
					right = mid;
				}
			}
			sorted[left] = num;
			if(left == res){
				res++;
			}
		}
		return res;
	}

    // time O(n^2)
	public int lengthOfLISDP(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int n = nums.length, res = 1;
		int[] dp = new int[n];
		for (int i = 0; i < n; i++) {
			dp[i] = 1;
			for (int j = i - 1; j >= 0; j--) {
				if (nums[i] > nums[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			res = Math.max(res, dp[i]);
		}
		return res;
	}
}
