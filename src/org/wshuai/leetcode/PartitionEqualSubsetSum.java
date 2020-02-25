package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/11/2016.
 * #0416 https://leetcode.com/problems/partition-equal-subset-sum/
 */
public class PartitionEqualSubsetSum {
	// time O(n*m), space O(m)
	public boolean canPartition(int[] nums) {
		if(nums == null || nums.length == 0){
			return false;
		}
		int n = nums.length, m = 0;
		for(int num : nums){
			m += num;
		}
		if(m % 2 != 0){
			return false;
		}
		m >>= 1;
		boolean[] dp = new boolean[m + 1];
		dp[0] = true;
		for(int i = 1; i <= n; i++){
			for(int j = m; j >= nums[i - 1]; j--){
				dp[j] = dp[j] || dp[j - nums[i - 1]];
			}
		}
		return dp[m];
	}

	// time O(n*m), space O(n*m)
	public boolean canPartitionDP(int[] nums) {
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		if (sum % 2 != 0) {
			return false;
		}
		sum >>= 1;
		int n = nums.length;
		boolean[][] dp = new boolean[n+1][sum+1];
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], false);
		}
		dp[0][0] = true;
		for (int i = 1; i < n+1; i++) {
			for (int j = 1; j < sum+1; j++) {
				if (j >= nums[i-1]) {
					dp[i][j] = (dp[i-1][j] || dp[i-1][j-nums[i-1]]);
				}else{
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		return dp[n][sum];
	}
}
