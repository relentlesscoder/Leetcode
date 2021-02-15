package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/09/2019.
 * #0410 https://leetcode.com/problems/split-array-largest-sum/
 */
public class SplitArrayLargestSum {

	// time O(n*log(sum))
	public int splitArray(int[] nums, int m) {
		int min = Integer.MIN_VALUE, max = 0;
		for(int num : nums){
			min = Math.max(min, num);
			max += num;
		}

		while(min < max){
			int mid = min + (max - min) / 2;
			if(canSplit(mid, nums, m)){
				max = mid;
			}else{
				min = mid + 1;
			}
		}

		return min;
	}

	private boolean canSplit(int upperBoundSum, int[] nums, int m){
		int curSum = 0, countSubArray = 1;
		for(int num : nums){
			curSum += num;
			// if current sum is greater than the upper bound sum,
			// start a new subarray
			if(curSum > upperBoundSum){
				countSubArray++;
				curSum = num;
				// if we can't split the array to
				if(countSubArray > m){
					return false;
				}
			}
		}
		return true;
	}

	// time O(n^2*m), space O(n*m)
	public int splitArrayDP(int[] nums, int m) {
		int n = nums.length;
		int[][] dp = new int[n + 1][m + 1];
		for(int i = 0; i <= n; i++){
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		dp[0][0] = 0;
		for(int i = 1; i <= n; i++){
			int sum = 0;
			for(int j = i; j > 0; j--){
				sum += nums[j - 1];
				for(int k = 1; k <= m; k++){
					dp[i][k] = Math.min(dp[i][k], Math.max(sum, dp[j - 1][k - 1]));
				}
			}
		}
		return dp[n][m];
	}
}
