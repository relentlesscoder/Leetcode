package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 02/27/2021.
 * #1743 https://leetcode.com/problems/restore-the-array-from-adjacent-pairs/
 */
public class RestoreTheArrayFromAdjacentPairs {

	// time O(n), space O(n)
	public int[] restoreArray(int[][] adjacentPairs) {
		Map<Integer, List<Integer>> adj = new HashMap<>();
		for(int[] ap : adjacentPairs){
			adj.putIfAbsent(ap[0], new ArrayList<>());
			adj.putIfAbsent(ap[1], new ArrayList<>());
			adj.get(ap[0]).add(ap[1]);
			adj.get(ap[1]).add(ap[0]);
		}
		int n = adjacentPairs.length, start = 0;
		int[] res = new int[n + 1];
		for(int key : adj.keySet()){
			if(adj.get(key).size() == 1){
				start = key;
				break;
			}
		}
		dfs(start, Integer.MIN_VALUE, adj, res, 0);
		return res;
	}

	private void dfs(int cur, int prev, Map<Integer, List<Integer>> adj, int[] res, int index){
		res[index] = cur;
		for(int next : adj.get(cur)){
			if(next == prev){
				continue;
			}
			dfs(next, cur, adj, res, index + 1);
		}
	}
}
