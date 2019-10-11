package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/11/2019.
 * #877 https://leetcode.com/problems/stone-game/
 */
public class StoneGame {
	Map<Integer, Integer> map;

	// same as #486
	public boolean stoneGame(int[] piles) {
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
