package org.wshuai.leetcode;

/**
 * Created by Wei on 11/28/2019.
 * #0689 https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/
 */
public class MaximumSumOf3NonOverlappingSubarrays {

	// time O(4*n), space O(4*n)
	public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
		int n = nums.length;
		int[] res = new int[3], prefix = new int[n + 1];
		//calculate prefix sum
		//prefix[j] - prefix[i] = sum of {i ... j - 1}
		for(int i = 1; i <= n; i++){
			prefix[i] = prefix[i - 1] + nums[i - 1];
		}
		//dp[i][j] denotes max sum of i non-overlapping
		//subarrays within range [0, j - 1]
		int[][] dp = new int[4][n + 1], pos = new int[4][n + 1];
		for(int i = 1; i <= 3; i++){
			for(int j = k*i; j <= n; j++){
				//sums[j] - sums[j - k] = sum of {j - k, j - k + 1 ... j - 1}
				//dp[i - 1][j - k] = max sum (of i - 1) in range {0 ... j - k - 1}
				int sum = prefix[j] - prefix[j - k] + dp[i - 1][j - k];
				if(sum > dp[i][j - 1]){ // update the values only if new max is found
					dp[i][j] = sum;
					pos[i][j] = j - k;
				}else{ // keep the current max
					dp[i][j] = dp[i][j - 1];
					pos[i][j] = pos[i][j - 1];
				}
			}
		}
		for(int i = 2, j = n; i >= 0; i--){
			res[i] = pos[i + 1][j];
			j = res[i];
		}
		return res;
	}
}
