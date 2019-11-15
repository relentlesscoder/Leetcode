package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/13/19.
 * #1192 https://leetcode.com/problems/critical-connections-in-a-network/
 */
public class CriticalConnectionsInANetwork {
	private List<List<Integer>> bridges;
	private LinkedList<Integer>[] adj;
	private int time;

	// Tarjan’s algorithm - https://www.geeksforgeeks.org/bridge-in-a-graph/
	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		time = 0;
		bridges = new ArrayList<>();
		adj = new LinkedList[n];
		for(int i = 0; i < n; i++){
			adj[i] = new LinkedList<>();
		}
		for(List<Integer> edge : connections){
			adj[edge.get(0)].add(edge.get(1));
			adj[edge.get(1)].add(edge.get(0));
		}
		boolean[] visited = new boolean[n];
		int[] disc = new int[n];
		int[] low = new int[n];
		int[] parent = new int[n];
		Arrays.fill(parent, -1);

		for(int i = 0; i < n; i++){
			if(!visited[i]){
				findBridge(i, visited, disc, low, parent);
			}
		}

		return bridges;
	}

	public void findBridge(int u, boolean[] visited, int[] disc, int[] low, int[] parent){
		// Mark the current node as visited
		visited[u] = true;
		// Initialize discovery time and low value
		disc[u] = low[u] = ++time;
		// Go through all vertices adjacent to this
		Iterator<Integer> itr = adj[u].iterator();
		while(itr.hasNext()){
			// v is current adjacent of u
			int v = itr.next();
			// If v is not visited yet, then make it a child
			// of u in DFS tree and recur for it.
			// If v is not visited yet, then recur for it
			if(!visited[v]){
				parent[v] = u;
				findBridge(v, visited, disc, low, parent);
				// Check if the subtree rooted with v has a
				// connection to one of the ancestors of u
				low[u] = Math.min(low[u], low[v]);
				// If the lowest vertex reachable from subtree
				// under v is below u in DFS tree, then u-v is
				// a bridge
				if(low[v] > disc[u]){
					bridges.add(Arrays.asList(u, v));
				}
			// Update low value of u for parent function calls.
			// v != parent[u] to avoid infinite loop for a undirected graph
			}else if(v != parent[u]){
				low[u] = Math.min(low[u], disc[v]);
			}
		}
	}
}
