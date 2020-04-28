package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/04/2019.
 * #0797 https://leetcode.com/problems/all-paths-from-source-to-target/
 */
public class AllPathsFromSourceToTarget {
	// time O(2^n)
	public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
		List<List<Integer>> res = new ArrayList<>();
		dfs(0, graph, res, new ArrayList<Integer>(), new boolean[graph.length]);
		return res;
	}

	private void dfs(int i, int[][] graph, List<List<Integer>> res, List<Integer> cur, boolean[] visited){
		if(i == graph.length - 1){
			cur.add(i);
			res.add(new ArrayList<>(cur));
			cur.remove(cur.size() - 1);
			return;
		}
		if(visited[i]){
			return;
		}
		visited[i] = true;
		cur.add(i);
		for(int j : graph[i]){
			dfs(j, graph, res, cur, visited);
		}
		visited[i] = false;
		cur.remove(cur.size() - 1);
	}
}
