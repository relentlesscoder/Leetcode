package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/25/19.
 * #1124 https://leetcode.com/problems/longest-well-performing-interval/
 */
public class LongestWellPerformingInterval {
	public int longestWPI(int[] hours) {
		int res = 0;
		int score = 0;
		int n = hours.length;
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < n; i++){
			score += hours[i] > 8 ? 1 : -1;
			if(score > 0){
				res = i + 1;
			}else{
				map.putIfAbsent(score, i);
				if (map.containsKey(score - 1)){
					res = Math.max(res, i - map.get(score - 1));
				}
			}
		}
		return res;
	}
}
