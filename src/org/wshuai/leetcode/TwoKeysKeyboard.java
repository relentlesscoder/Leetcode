package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/29/2019.
 * #650 https://leetcode.com/problems/2-keys-keyboard/
 */
public class TwoKeysKeyboard {
	public int minSteps(int n) {
		if(n == 1){
			return 0;
		}
		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[1] = 0;
		for(int i = 2; i <= n; i++){
			for(int j = i/2; j >= 1; j--){
				if(i % j != 0){
					continue;
				}
				dp[i] = Math.min(dp[j] + i/j,  dp[i]);
			}
		}
		return dp[n];
	}
}
