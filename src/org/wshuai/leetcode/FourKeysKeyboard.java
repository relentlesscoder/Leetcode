package org.wshuai.leetcode;

/**
 * Created by Wei on 10/29/2019.
 * #0651 https://leetcode.com/problems/4-keys-keyboard/
 */
public class FourKeysKeyboard {
	// time O(n^2), space O(n)
	public int maxA(int N) {
		if (N < 4) {
			return N;
		}
		int[] dp = new int[N + 1];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 3;
		for (int i = 4; i <= N; i++) {
			// key 1
			dp[i] = dp[i - 1] + 1;
			int k = 2;
			// key 2, 3, 4, 4 ...
			for (int j = i - 3; j >= 3; j--) {
				dp[i] = Math.max(dp[i], dp[j] * k);
				k++;
			}
		}
		return dp[N];
	}
}
