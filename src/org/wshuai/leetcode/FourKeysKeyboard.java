package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/29/19.
 * #651 https://leetcode.com/problems/4-keys-keyboard/
 */
public class FourKeysKeyboard {
	public int maxA(int N) {
		if(N < 4){
			return N;
		}
		int[] dp = new int[N + 1];
		Arrays.fill(dp, Integer.MIN_VALUE);
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 3;
		for(int i = 4; i <= N; i++){
			dp[i] = dp[i - 1] + 1;
			int k = 2;
			for(int j = i - 3; j >= 3; j--){
				dp[i] = Math.max(dp[i], dp[j] * k);
				k++;
			}
		}
		return dp[N];
	}
}
