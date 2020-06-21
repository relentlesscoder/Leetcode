package org.wshuai.leetcode;

/**
 * Created by Wei on 04/20/2020.
 * #1416 https://leetcode.com/problems/restore-the-array/
 */
public class RestoreTheArray {
	private static final int MOD = 1_000_000_007;

	public int numberOfArrays(String s, int k) {
		return dfs(0, s.toCharArray(), k, new int[s.length()]);
	}

	private int dfs(int start, char[] digits, int k, int[] dp){
		if(start == digits.length){
			return 1;
		}
		if(digits[start] == '0'){
			return 0;
		}
		if(dp[start] > 0){
			return dp[start];
		}
		int res = 0;
		long num = 0;
		for(int i = start; i < digits.length; i++){
			num = num * 10 + (digits[i] - '0');
			if(num > k){
				break;
			}
			res = (res + dfs(i + 1, digits, k, dp) % MOD) % MOD;
		}
		dp[start] = res;
		return res;
	}
}
