package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/8/2019.
 * #486 https://leetcode.com/problems/predict-the-winner/
 */
public class PredictTheWinner {
	Map<Integer, Integer> map;

	public boolean PredictTheWinner(int[] nums) {
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

	public boolean PredictTheWinnerMiniMax(int[] nums) {
		return getScore(nums, 0, nums.length - 1) >= 0;
	}

	private int getScoreMiniMax(int[] nums, int l, int r){
		if(l == r){
			return nums[l];
		}
		return Math.max(nums[l] - getScore(nums, l + 1, r),
				nums[r] - getScore(nums, l, r - 1));
	}
}
