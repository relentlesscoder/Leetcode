package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/23/2019.
 * #956 https://leetcode.com/problems/tallest-billboard/
 */
public class TallestBillboard {
	// https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-956-tallest-billboard/
	public int tallestBillboard(int[] rods) {
		int n = rods.length;
		int m = 0;
		for(int r : rods){
			m += r;
		}
		int[][] dp = new int[n + 1][m + 1];
		for(int i = 0; i <= n; i++){
			Arrays.fill(dp[i], -1);
		}
		dp[0][0] = 0;
		for(int i = 1; i <= n; i++){
			int h = rods[i - 1];
			for(int j = 0; j <= m - h; j++){
				if(dp[i - 1][j] < 0){
					continue;
				}
				// not used
				dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
				// put on the higher side
				dp[i][j + h] = Math.max(dp[i][j + h], dp[i - 1][j]);
				// put on the lower side
				int diff = Math.abs(j - h);
				dp[i][diff] = Math.max(dp[i][diff], dp[i - 1][j] + Math.min(j, h));
			}
		}
		return dp[n][0];
	}
}
