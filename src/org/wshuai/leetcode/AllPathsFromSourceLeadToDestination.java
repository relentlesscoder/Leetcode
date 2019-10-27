package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 10/27/19.
 * #1059 https://leetcode.com/problems/all-paths-from-source-lead-to-destination/
 */
public class AllPathsFromSourceLeadToDestination {
	public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
		LinkedList<Integer>[] adj = new LinkedList[n];
		for(int i = 0; i < n; i++){
			adj[i] = new LinkedList<>();
		}
		for(int[] edge: edges){
			adj[edge[0]].offerLast(edge[1]);
		}
		boolean[] visited = new boolean[n];
		visited[source] = true;
		return dfs(source, destination, visited, adj);
	}

	private boolean dfs(int u, int d, boolean[] visited, LinkedList<Integer>[] adj){
		LinkedList<Integer> next = adj[u];
		if(u != d && next.size() == 0){
			return false;
		}
		for(int v: next){
			if(visited[v]){
				return false;
			}
			visited[v] = true;
			if(!dfs(v, d, visited, adj)){
				return false;
			}
			visited[v] = false;
		}
		return true;
	}
}
