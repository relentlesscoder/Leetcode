package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 9/4/19.
 * #797 https://leetcode.com/problems/all-paths-from-source-to-target/
 */
public class AllPathsFromSourceToTarget {
	// DFS
	public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> path = new ArrayList<>();
		path.add(0);
		dfs(0, graph, path, res);
		return res;
	}

	private void dfs(int v, int[][] graph, List<Integer> path, List<List<Integer>> res) {
		int[] adj = graph[v];
		if (adj.length == 0) {
			return;
		}
		for (int i : adj) {
			path.add(i);
			if (i == graph.length - 1) {
				res.add(copyList(path));
			} else {
				dfs(i, graph, path, res);
			}
			path.remove(path.size() - 1);
		}
	}

	private List<Integer> copyList(List<Integer> path) {
		List<Integer> res = new ArrayList<>();
		for (int i : path) {
			res.add(i);
		}
		return res;
	}
}
