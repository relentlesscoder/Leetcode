package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/18/2019.
 * #1049 https://leetcode.com/problems/last-stone-weight-ii/
 */
public class LastStoneWeightII {
	Map<String, Integer> map;

	public int lastStoneWeightII(int[] stones) {
		map = new HashMap<>();
		return minimumDiff(stones, 0, 0, 0);
	}

	private int minimumDiff(int[] nums, int n, int S1, int S2){
		if(n >= nums.length){
			return Math.abs(S1 - S2);
		}

		String key = n + "|" + S1;

		if(!map.containsKey(key)){

			int inc = minimumDiff(nums, n + 1, S1 + nums[n], S2);
			int exec = minimumDiff(nums, n + 1, S1, S2 + nums[n]);

			map.put(key, Math.min(inc, exec));
		}

		return map.get(key);
	}
}
