package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/21/19.
 * #781 https://leetcode.com/problems/rabbits-in-forest/
 */
public class RabbitsInForest {
	public int numRabbits(int[] answers) {
		int res = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for(int a: answers){
			map.put(a, map.getOrDefault(a, 0) + 1);
		}
		for(Map.Entry<Integer, Integer> entry: map.entrySet()){
			// example: for value 3, each 4 of 3s (3 3 3 3)
			// could be in the same color to minimize the total
			int key = entry.getKey();
			int val = entry.getValue();
			int cnt = val / (key + 1);
			cnt += val % (key + 1) == 0 ? 0 : 1;
			res += cnt * (key + 1);
		}
		return res;
	}
}
