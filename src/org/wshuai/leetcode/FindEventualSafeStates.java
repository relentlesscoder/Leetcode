package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/27/19.
 * #802 https://leetcode.com/problems/find-eventual-safe-states/
 */
public class FindEventualSafeStates {

	// https://www.geeksforgeeks.org/detect-cycle-direct-graph-using-colors/
	public List<Integer> eventualSafeNodes(int[][] graph) {
		List<Integer> res = new ArrayList<>();
		if(graph == null || graph.length == 0){
			return res;
		}

		int N = graph.length;
		int[] color = new int[N];

		for(int i = 0; i < N; i++){
			if(dfs(graph, i, color)){
				res.add(i);
			}
		}

		return res;
	}

	private boolean dfs(int[][] graph, int start, int[] color){
		if(color[start] != 0){
			return color[start] == 1;
		}

		color[start] = 2;
		for(int v: graph[start]){
			if(!dfs(graph, v, color)){
				return false;
			}
		}
		color[start] = 1;

		return true;
	}
}
