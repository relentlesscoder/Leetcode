package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 09/13/2019.
 * #0403 https://leetcode.com/problems/frog-jump/
 */
public class FrogJump {
	// time O(n^3) worst case
	public boolean canCross(int[] stones) {
		Set<Integer> set = new HashSet<>();
		Set<String> visited = new HashSet<>();
		for(int s : stones){
			set.add(s);
		}
		if(!set.contains(1)){
			return false;
		}
		return dfs(1, 1, stones[stones.length - 1], set, visited);
	}

	private boolean dfs(int cur, int unit, int target, Set<Integer> stones, Set<String> visited){
		if(cur >= target){
			return cur == target;
		}
		String key = cur + "," + unit;
		if(visited.contains(key)){
			return false;
		}
		for(int i = -1; i <= 1; i++){
			int next = unit + i;
			if(next > 0 && stones.contains(cur + next) && dfs(cur + next, next, target, stones, visited)){
				return true;
			}
		}
		visited.add(key);
		return false;
	}
}
