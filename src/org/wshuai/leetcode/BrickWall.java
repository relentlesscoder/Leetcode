package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 10/20/2019.
 * #554 https://leetcode.com/problems/brick-wall/
 */
public class BrickWall {
	public int leastBricks(List<List<Integer>> wall) {
		Map<Integer, Integer> map = new HashMap<>();
		for(List<Integer> lst: wall){
			int s = 0;
			for(int i = 0; i < lst.size() - 1; i++){
				s += lst.get(i);
				map.put(s, map.getOrDefault(s, 0) + 1);
			}
		}
		int max = 0;
		for(int cnt: map.values()){
			max = Math.max(max, cnt);
		}
		return wall.size() - max;
	}
}
