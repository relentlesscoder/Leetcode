package org.wshuai.leetcode;

/**
 * Created by Wei on 11/25/19.
 * #1262 https://leetcode.com/problems/greatest-sum-divisible-by-three/
 */
public class GreatestSumDivisibleByThree {
	public int maxSumDivThree(int[] nums) {
		int N = nums.length;
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
