package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/10/2019.
 * #1066 https://leetcode.com/problems/campus-bikes-ii/
 */
public class CampusBikesII {
	private int[][] cost;
	private int[] used;
	private Map<Integer, Integer> map;

	// https://leetcode.com/problems/campus-bikes-ii/discuss/360037/Step-by-Step-solution-from-400ms-to-1ms-(beating-100)
	public int assignBikes(int[][] workers, int[][] bikes) {
		map = new HashMap<>();
		cost = new int[workers.length][bikes.length];
		used = new int[bikes.length];
		for(int i = 0; i < workers.length; i++){
			for(int j = 0; j < bikes.length; j++){
				cost[i][j] = distance(workers[i], bikes[j]);
			}
		}
		return dfs(0);
	}

	private int dfs(int i){
		int min = Integer.MAX_VALUE;
		if(i >= cost.length){
			return 0;
		}
		int key = createKey(used);
		if (map.containsKey(key)){
			return map.get(key);
		}
		for(int j = 0; j < cost[0].length; j++){
			if(used[j] != 1){
				used[j] = 1;
				int c = cost[i][j] + dfs(i + 1);
				if(c < min){
					min = c;
				}
				used[j] = 0;
			}
		}
		map.put(key, min);
		return min;
	}

	private int distance(int[] p, int[] q){
		return Math.abs(p[0] - q[0]) + Math.abs(p[1] - q[1]);
	}

	private int createKey(int[] used) {
		int key = 0;
		for (int i = 0; i < used.length; i++) {
			if (used[i] == 1) {
				key |= 1 << i;
			}
		}
		return key;
	}
}
