package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/08/2023.
 * #2065 https://leetcode.com/problems/maximum-path-quality-of-a-graph/
 */
public class MaximumPathQualityOfAGraph {

	private int res;

	// time O(E^10), space O(E + V)
	public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
		int n = values.length;
		Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
		for (int[] e : edges) {
			graph.putIfAbsent(e[0], new HashMap<>());
			graph.putIfAbsent(e[1], new HashMap<>());
			graph.get(e[0]).put(e[1], e[2]);
			graph.get(e[1]).put(e[0], e[2]);
		}
		res = 0;
		dfs(0, 0, 0, maxTime, values, new int[n], graph);
		return res;
	}

	private void dfs(int curr, int time, int score, int maxTime, int[] values, int[] visited, Map<Integer, Map<Integer, Integer>> graph) {
		if (time > maxTime) {
			return;
		}
		if (visited[curr] == 0) {
			score += values[curr];
		}
		if (curr == 0) {
			res = Math.max(res, score);
		}
		visited[curr]++;
		if (!graph.containsKey(curr)) {
			return;
		}
		for (Map.Entry<Integer, Integer> next : graph.get(curr).entrySet()) {
			dfs(next.getKey(), time + next.getValue(), score, maxTime, values, visited, graph);
		}
		visited[curr]--;
	}
}
