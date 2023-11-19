package org.wshuai.leetcode;

/**
 * Created by Wei on 09/25/2023.
 * #1871 https://leetcode.com/problems/jump-game-vii/
 */
public class JumpGameVII {

	// time O(n), space O(n)
	public boolean canReach(String s, int minJump, int maxJump) {
		int n = s.length(), zeroCountInSlidingWindow = 0;
		boolean[] dp = new boolean[n];
		dp[0] = true;
		for (int i = 1; i < n; i++) {
			// maintain a sliding window [ i - maxJump, i - minJump]
			if (i >= minJump && dp[i - minJump]) { // add current i - minJump to the end of the window
				zeroCountInSlidingWindow++;
			}
			if (i > maxJump && dp[i - maxJump - 1]) { // remove current i - minJump to the end of the window
				zeroCountInSlidingWindow--;
			}
			if (zeroCountInSlidingWindow > 0 && s.charAt(i) == '0') { // the current index is reachable only if there is any reachable zeros in the window and current char is '0'
				dp[i] = true;
			}
		}
		return dp[n - 1];
	}
}
