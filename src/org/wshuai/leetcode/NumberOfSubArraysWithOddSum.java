package org.wshuai.leetcode;

/**
 * Created by Wei on 07/28/2020.
 * #1524 https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/
 */
public class NumberOfSubArraysWithOddSum {

	private static final int MOD = 1_000_000_007;

	// time O(n), space O(1)
	public int numOfSubarrays(int[] arr) {
		int res = 0, n = arr.length, prev = 0;
		prev = arr[0] % 2 == 1 ? 1 : 0;
		res += prev;
		for(int i = 1; i < n; i++){
			int cur = 0;
			if(arr[i] % 2 == 1){
				cur = (i - prev) + 1;
			}else{
				cur = prev;
			}
			res = (res + cur) % MOD;
			prev = cur;
		}
		return res;
	}

	// time O(n), space O(n)
	public int numOfSubarrays1D(int[] arr) {
		int res = 0, n = arr.length;
		int[] dp = new int[n];
		dp[0] = arr[0] % 2 == 1 ? 1 : 0;
		res += dp[0];
		for(int i = 1; i < n; i++){
			if(arr[i] % 2 == 1){
				dp[i] = (i - dp[i - 1]) + 1;
			}else{
				dp[i] = dp[i - 1];
			}
			res = (res + dp[i]) % MOD;
		}
		return res;
	}
}
