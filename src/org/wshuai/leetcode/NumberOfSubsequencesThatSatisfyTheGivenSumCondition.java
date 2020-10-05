package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 07/25/2020.
 * #1498 https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/
 */
public class NumberOfSubsequencesThatSatisfyTheGivenSumCondition {

	private static final int MOD = 1_000_000_007;

	// time O(n), space O(n)
	public int numSubseq(int[] nums, int target) {
		int res = 0, n = nums.length, left = 0, right = n - 1;
		Arrays.sort(nums);
		int[] pow = new int[n];
		pow[0] = 1;
		for(int i = 1; i < n; i++){
			pow[i] = (pow[i - 1] << 1) % MOD;
		}
		while(left <= right){
			if(nums[left] + nums[right] > target){
				right--;
			}else{
				// fix the min element, the other elements have 2^(r-l)
				// possible combinations
				res = (res + pow[right - left]) % MOD;
				left++;
			}
		}
		return res;
	}
}
