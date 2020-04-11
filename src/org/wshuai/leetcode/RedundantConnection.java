package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 10/24/2019.
 * #0684 https://leetcode.com/problems/redundant-connection/
 */
public class RedundantConnection {
	// time O(n), space O(n)
	public int[] findRedundantConnection(int[][] edges) {
		int n = edges.length;
		int[] root = new int[n];
		for(int i = 0; i < n; i++){
			root[i] = i;
		}
		for(int[] e : edges){
			int r1 = findRoot(e[0] - 1, root);
			int r2 = findRoot(e[1] - 1, root);
			if(r1 == r2){
				return e;
			}
			root[r2] = r1;
		}
		return new int[0];
	}

	private int findRoot(int r, int[] root){
		if(root[r] != r){
			root[r] = findRoot(root[r], root);
		}
		return root[r];
	}

	// time O(n^2), space O(n^2)
	public int[] findRedundantConnectionDFS(int[][] edges) {
		int n = edges.length;
		Set<Integer>[] adj = new HashSet[n];
		for(int i = 0; i < n; i++){
			adj[i] = new HashSet<>();
		}
		return dfs(edges, adj);
	}

	private int[] dfs(int[][] edges, Set<Integer>[] adj){
		for(int[] e : edges){
			int u = e[0] - 1, v = e[1] - 1;
			if(hasCycle(adj, u, v, u)){
				return e;
			}
			adj[u].add(v);
			adj[v].add(u);
		}
		return new int[0];
	}

	private boolean hasCycle(Set<Integer>[] adj, int from, int to, int prev){
		if(adj[from].contains(to)){
			return true;
		}
		for(int next : adj[from]){
			if(next == prev){
				continue;
			}
			if(hasCycle(adj, next, to, from)){
				return true;
			}
		}
		return false;
	}
}
