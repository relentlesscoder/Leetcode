package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 01/16/2020.
 * #0120 https://leetcode.com/problems/triangle/
 */
public class Triangle {
	// time O(n^2), space O(n)
	public int minimumTotal(List<List<Integer>> triangle) {
		if(triangle == null || triangle.size() == 0){
			return 0;
		}
		int res = Integer.MAX_VALUE, prev = 0, cur = 1, n = triangle.size();
		int[][] dp = new int[2][n];
		dp[prev][0] = triangle.get(0).get(0);
		for(int i = 1; i < n; i++){
			List<Integer> list = triangle.get(i);
			for(int j = 0; j < list.size(); j++){
				int left = j - 1 >= 0 ? dp[prev][j - 1] : Integer.MAX_VALUE;
				int right = j < i ? dp[prev][j] : Integer.MAX_VALUE;
				int min = Math.min(left, right) + list.get(j);
				dp[cur][j] = min;
			}
			prev = cur;
			cur = 1 - prev;
		}
		for(int i = 0; i < n; i++){
			res = Math.min(res, dp[prev][i]);
		}
		return res;
	}
}
