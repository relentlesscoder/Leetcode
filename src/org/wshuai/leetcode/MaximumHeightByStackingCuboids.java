package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 03/06/2021.
 * #1691 https://leetcode.com/problems/maximum-height-by-stacking-cuboids/
 */
public class MaximumHeightByStackingCuboids {

	// time O(n^2), space O(n)
	// https://leetcode.com/problems/maximum-height-by-stacking-cuboids/discuss/970589/Proof%3A-why-only-consider-longest-edge-as-height
	public int maxHeight(int[][] cuboids) {
		int n = cuboids.length, res = 0;
		for(int[] c : cuboids){
			Arrays.sort(c);
		}
		Arrays.sort(cuboids, (a, b) -> {
			if(a[0] != b[0]){
				return b[0] - a[0];
			}
			if(a[1] != b[1]){
				return b[1] - a[1];
			}
			return b[2] - a[2];
		});
		int[] dp = new int[n];
		for(int j = 0; j < n; j++){
			dp[j] = cuboids[j][2];
			for(int i = 0; i < j; i++){
				if(cuboids[i][0] >= cuboids[j][0]
					&& cuboids[i][1] >= cuboids[j][1]
					&& cuboids[i][2] >= cuboids[j][2]){
					dp[j] = Math.max(dp[j], dp[i] + cuboids[j][2]);
				}
			}
			res = Math.max(res, dp[j]);
		}
		return res;
	}
}
