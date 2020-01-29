package org.wshuai.leetcode;

/**
 * Created by Wei on 09/20/2016.
 * #0276 https://leetcode.com/problems/paint-fence/
 */
public class PaintFence {
	// time O(n, space O(1) (can be optimized to constant space)
	// https://leetcode.com/problems/paint-fence/discuss/178010/The-only-solution-you-need-to-read
	public int numWays(int n, int k) {
		if(n == 0){
			return 0;
		}
		if(n == 1){
			return k;
		}
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = k;
		dp[2] = k*k;
		for(int i = 3; i <= n; i++){
			dp[i] = (dp[i - 1] + dp[i - 2]) * (k - 1);
		}
		return dp[n];
	}
}
