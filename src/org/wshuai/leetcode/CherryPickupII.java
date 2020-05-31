package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 05/31/2020.
 * #1463 https://leetcode.com/problems/cherry-pickup-ii/
 */
public class CherryPickupII {

	// time O(9^m), space O(9^m)
	public int cherryPickup(int[][] grid) {
		int res = 0, m = grid.length, n = grid[0].length;
		Map<Integer, Map<Integer, Integer>> dp = new HashMap<>();
		dp.put(0, new HashMap<>());
		dp.get(0).put(n - 1, grid[0][0] + grid[0][n - 1]);
		for(int i = 1; i < m; i++){
			Map<Integer, Map<Integer, Integer>> next = new HashMap<>();
			for(Map.Entry<Integer, Map<Integer, Integer>> e1 : dp.entrySet()){
				int x = e1.getKey();
				for(Map.Entry<Integer, Integer> e2 : e1.getValue().entrySet()){
					int y = e2.getKey(), c = e2.getValue();
					for(int j = x - 1; j <= x + 1; j++){
						for(int k = y - 1; k <= y + 1; k++){
							if(j < 0 || j >= n || k < 0 || k >= n){
								continue;
							}
							next.putIfAbsent(j, new HashMap<>());
							Map<Integer, Integer> cur = next.get(j);
							cur.put(k, Math.max(c + (j == k ?
								grid[i][j] : grid[i][j] + grid[i][k]),
								cur.getOrDefault(k, 0)));
							res = Math.max(res, cur.get(k));
						}
					}
				}
			}
			dp = next;
		}
		return res;
	}
}
