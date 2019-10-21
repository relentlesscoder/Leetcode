package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/11/2019.
 * #877 https://leetcode.com/problems/stone-game/
 */
public class StoneGame {

	// 6 ms
	public boolean stoneGame(int[] piles) {
		int n = piles.length;
		int[][] dp = new int[n][n];

		for(int i = 0; i < n; i++){
			dp[i][i] = piles[i];
		}

		for(int len = 1; len < n; len++){
			for(int i = 0; i + len < n; i++){
				dp[i][i + len] = Math.max(piles[i] - dp[i + 1][i + len],
					piles[i + len] - dp[i][i + len - 1]);
			}
		}
		return dp[0][n - 1] > 0;
	}

	Map<Integer, Integer> map;

	// 108 ms same as #486
	public boolean stoneGameMiniMax(int[] piles) {
		map = new HashMap<>();
		return getScore(piles, 0, piles.length - 1) >= 0;
	}

	private int getScore(int[] nums, int l, int r){
		if(l == r){
			return nums[l];
		}
		int key = l * nums.length + r;
		if(map.containsKey(key)){
			return map.get(key);
		}
		map.put(key, Math.max(nums[l] - getScore(nums, l + 1, r),
				nums[r] - getScore(nums, l, r - 1)));
		return map.get(key);
	}
}
