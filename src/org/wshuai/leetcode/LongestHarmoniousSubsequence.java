package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 07/29/2017.
 * #0594 https://leetcode.com/problems/longest-harmonious-subsequence/
 */
public class LongestHarmoniousSubsequence {
	// time O(n), space O(n)
	public int findLHS(int[] nums) {
		int res = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for(int num : nums){
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		for(int key : map.keySet()){
			if(map.containsKey(key + 1)){
				res = Math.max(res, map.get(key) + map.get(key + 1));
			}
		}
		return res;
	}
}
