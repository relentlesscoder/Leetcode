package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 10/02/2023.
 * #2316 https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/
 */
public class CountUnreachablePairsOfNodesInAnUndirectedGraph {

	// time O(V + E), space O(V + E)
	public long countPairs(int n, int[][] edges) {
		long res = 0;
		Set<Integer> visited = new HashSet<>();
		ArrayList<Integer>[] adj = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int[] e : edges) {
			adj[e[0]].add(e[1]);
			adj[e[1]].add(e[0]);
		}
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (!visited.contains(i)) {
				long nodes = dfs(i, adj, visited);
				res += nodes * (n - nodes - count);
				count += nodes;
			}
		}
		return res;
	}

	private long dfs(int curr, ArrayList<Integer>[] adj, Set<Integer> visited) {
		visited.add(curr);
		long res = 1;
		for (int next : adj[curr]) {
			if (!visited.contains(next)) {
				res += dfs(next, adj, visited);
			}
		}
		return res;
	}
}
