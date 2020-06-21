package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/21/2019.
 * #0781 https://leetcode.com/problems/rabbits-in-forest/
 */
public class RabbitsInForest {
	// time O(n), space O(n)
	public int numRabbits(int[] answers) {
		int res = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for(int num : answers){
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		for(Map.Entry<Integer, Integer> entry : map.entrySet()){
			// example: for value 3, each 4 of 3s (3 3 3 3)
			// could be in the same color to minimize the total
			int group = entry.getKey() + 1, total = entry.getValue(), count = total / group;
			// any extra can form another group
			count += total % group == 0 ? 0 : 1;
			res += count * group;
		}
		return res;
	}
}
