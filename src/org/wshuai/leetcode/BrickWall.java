package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 10/20/2019.
 * #0554 https://leetcode.com/problems/brick-wall/
 */
public class BrickWall {
	// time O(n), space O(n)
	public int leastBricks(List<List<Integer>> wall) {
		// map saves ending positions for each bricks
		// the optimal cut position is the ending position
		// at which the most bricks end
		Map<Integer, Integer> map = new HashMap<>();
		for(List<Integer> row : wall){
			int sum = 0;
			for(int i = 0; i < row.size() - 1; i++){
				sum += row.get(i);
				map.put(sum, map.getOrDefault(sum, 0) + 1);
			}
		}
		int max = 0;
		for(int count : map.values()){
			max = Math.max(max, count);
		}
		return wall.size() - max;
	}
}
