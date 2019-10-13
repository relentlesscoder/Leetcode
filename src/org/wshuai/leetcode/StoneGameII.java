package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/12/2019.
 * #1140 https://leetcode.com/problems/stone-game-ii/
 */
public class StoneGameII {
	Map<Integer, Integer> map;

	public int stoneGameII(int[] piles) {
		map = new HashMap<>();
		int diff = play(piles, 0, 1);
		int sum = 0;
		for(int p: piles){
			sum += p;
		}
		return (diff + sum) / 2;
	}

	private int play(int[] nums, int s, int m){
		if(s >= nums.length){
			return 0;
		}
		int key = s * nums.length + m;
		if(map.containsKey(key)){
			return map.get(key);
		}
		int max = Integer.MIN_VALUE;
		int sum = 0;
		for(int i = s; i < s + 2 * m && i < nums.length; i++){
			sum += nums[i];
			max = Math.max(max, sum - play(nums, i + 1, Math.max(m, i - s + 1)));
		}
		map.put(key, max);
		return max;
	}
}
