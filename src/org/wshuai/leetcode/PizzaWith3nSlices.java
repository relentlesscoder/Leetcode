package org.wshuai.leetcode;

/**
 * Created by Wei on 03/23/2020.
 * #1388 https://leetcode.com/problems/pizza-with-3n-slices/
 */
public class PizzaWith3nSlices {

	// time O(n^2), space O(n^2)
	// same idea as #0213 House Robber II
	public int maxSizeSlices(int[] slices) {
		int n = slices.length;
		if(n == 3){
			return Math.max(slices[0], Math.max(slices[1], slices[2]));
		}
		int[][] dp1 = new int[n][n/3 + 1], dp2 = new int[n][n/3 + 1];

		// slices 0 -> n - 2
		dp1[1][1] = slices[0];
		for(int i = 2; i < n; i++){
			for(int j = 1; j <= n/3; j++){
				dp1[i][j] = Math.max(dp1[i - 1][j], dp1[i - 2][j - 1] + slices[i - 1]);
			}
		}

		// slices 1 -> n - 1
		dp2[1][1] = slices[1];
		for(int i = 2; i < n; i++){
			for(int j = 1; j <= n/3; j++){
				dp2[i][j] = Math.max(dp2[i - 1][j], dp2[i - 2][j - 1] + slices[i]);
			}
		}

		return Math.max(dp1[n - 1][n/3], dp2[n - 1][n/3]);
	}
}
