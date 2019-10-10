package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 3/14/2017.
 * #261 https://leetcode.com/problems/graph-valid-tree/
 */
public class GraphValidTree {
	public boolean validTree(int n, int[][] edges) {
		if (n <= 0) {
			return true;
		}
		if (edges == null || edges.length == 0) {
			return n == 1;
		}
		List<List<Integer>> adj = new ArrayList<List<Integer>>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < edges.length; i++) {
			adj.get(edges[i][0]).add(edges[i][1]);
			adj.get(edges[i][1]).add(edges[i][0]);
		}
		Set<Integer> visited = new HashSet<Integer>();
		if (hasCycle(adj, visited, 0, -1)) {
			return false;
		}
		return visited.size() == n;
	}

	private boolean hasCycle(List<List<Integer>> adj, Set<Integer> visited, int node, int parent) {
		visited.add(node);

		for (int i = 0; i < adj.get(node).size(); i++) {
			int nxt = adj.get(node).get(i);
			if ((visited.contains(nxt) && nxt != parent) || (!visited.contains(nxt) && hasCycle(adj, visited, nxt, node))) {
				return true;
			}
		}

		return false;
	}
}
