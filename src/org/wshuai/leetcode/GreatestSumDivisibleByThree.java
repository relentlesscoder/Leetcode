package org.wshuai.leetcode;

/**
 * Created by Wei on 11/25/2019.
 * #1262 https://leetcode.com/problems/greatest-sum-divisible-by-three/
 */
public class GreatestSumDivisibleByThree {

	// time O(n)
	public int maxSumDivThree(int[] nums) {
		// dp[i] means the current maximum sum that sum % 3 = i
		int[] dp = new int[]{0, Integer.MIN_VALUE, Integer.MIN_VALUE};
		for(int n : nums){
			int[] next = new int[3];
			for(int i = 0; i < 3; i++){
				next[(i + n) % 3] = Math.max(dp[(i + n) % 3], dp[i] + n);
			}
			dp = next;
		}
		return dp[0];
	}
}
