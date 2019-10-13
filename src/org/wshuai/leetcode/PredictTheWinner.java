package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/8/2019.
 * #486 https://leetcode.com/problems/predict-the-winner/
 */
public class PredictTheWinner {

	// beats 100%
	public boolean predictTheWinner(int[] nums) {
		int n = nums.length;
		int[][] dp = new int[n][n];

		for(int i = 0; i < n; i++){
			dp[i][i] = nums[i];
		}

		for(int len = 1; len < n; len++){
			for(int i = 0; i + len < n; i++){
				dp[i][i + len] = Math.max(nums[i] - dp[i + 1][i + len],
					nums[i + len] - dp[i][i + len - 1]);
			}
		}
		return dp[0][n - 1] >= 0;
	}

	Map<Integer, Integer> map;

	public boolean predictTheWinnerMiniMax(int[] nums) {
		map = new HashMap<>();
		return getScore(nums, 0, nums.length - 1) >= 0;
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
