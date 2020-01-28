package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Wei on 03/14/2017.
 * #0261 https://leetcode.com/problems/graph-valid-tree/
 */
public class GraphValidTree {
	// time O(V + E*log(V)), in practice O(V + E)
	// with path compression and union by rank
	public boolean validTree(int n, int[][] edges) {
		int[] root = new int[n];
		for(int i = 0; i < n; i++){
			root[i] = i;
		}
		for(int[] e : edges){
			int r1 = findRoot(e[0], root);
			int r2 = findRoot(e[1], root);
			if(r1 == r2){
				return false;
			}
			root[r1] = r2;
		}
		return edges.length == n - 1;
	}

	private int findRoot(int i, int[] root){
		if(root[i] != i){
			root[i] = findRoot(root[i], root);
		}
		return root[i];
	}

	// time O(V + E)
	public boolean validTreeDFS(int n, int[][] edges) {
		List<Integer>[] adj = new ArrayList[n];
		for(int i = 0; i < n; i++){
			adj[i] = new ArrayList<>();
		}
		for(int[] e : edges){
			adj[e[0]].add(e[1]);
			adj[e[1]].add(e[0]);
		}
		Set<Integer> visited = new HashSet<>();
		if(dfs(0, -1, adj, visited)){
			return false;
		}
		return visited.size() == n;
	}

	private boolean dfs(int cur, int prev, List<Integer>[] adj, Set<Integer> visited){
		visited.add(cur);
		for(int next : adj[cur]){
			if(next == prev){
				continue;
			}
			if(visited.contains(next) || dfs(next, cur, adj, visited)){
				return true;
			}
		}
		return false;
	}
}
