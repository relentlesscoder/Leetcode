package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/02/2023.
 * #2184 https://leetcode.com/problems/number-of-ways-to-build-sturdy-brick-wall/
 */
public class NumberOfWaysToBuildSturdyBrickWall {

	private int height;
	private int width;
	private int[] bricks;
	private List<Integer> rows;
	private int mod = 1_000_000_007;

	// time O(h * r^2), space O(h * 1_024)
	public int buildWall(int height, int width, int[] bricks) {
		this.height = height;
		this.width = width;
		this.bricks = bricks;
		rows = new ArrayList<>();
		dfs(0, 0);

		int[][] dp = new int[height + 1][1_024];
		for (int row : rows) {
			dp[1][row] = 1;
		}
		for (int i = 2; i <= height; i++) {
			for (int curr : rows) {
				for (int pre : rows) {
					if ((curr & pre) == 0) {
						dp[i][curr] = (dp[i][curr] + dp[i - 1][pre]) % mod;
					}
				}
			}
		}
		int res = 0;
		for (int i = 0; i < 1_024; i++) {
			res = (res + dp[height][i]) % mod;
		}
		return res;
	}

	private void dfs(int curr, int mask) {
		if (curr == width) {
			rows.add(mask);
			return;
		}
		if (curr >= 1) {
			// we use bit mask to represent the join of bricks in the row
			// for example, we may have row composed of bricks 2, 4, 3, 1
			// then join of bricks (except two sides) are 2, 6, 9
			// we store it as a bit mask 1000100010 so that for two bit masks (rows)
			// if mask1 & mask2 == 0 then it means no same joins found
			mask |= (1 << (curr - 1));
		}
		for (int brick : bricks) {
			if (curr + brick <= width) {
				dfs(curr + brick, mask);
			}
		}
	}
}
