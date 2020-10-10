package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/13/2019.
 * #1192 https://leetcode.com/problems/critical-connections-in-a-network/
 */
public class CriticalConnectionsInANetwork {

	private List<List<Integer>> bridges;
	private ArrayList<Integer>[] adj;
	private int time;

	// time O(V+E), space O(V+E)
	// Tarjan’s algorithm - https://www.geeksforgeeks.org/bridge-in-a-graph/
	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		time = 0;
		bridges = new ArrayList<>();
		adj = new ArrayList[n];
		for(int i = 0; i < n; i++){
			adj[i] = new ArrayList<>();
		}
		for(List<Integer> edge : connections){
			adj[edge.get(0)].add(edge.get(1));
			adj[edge.get(1)].add(edge.get(0));
		}
		boolean[] visited = new boolean[n];
		int[] discoveryTime = new int[n];
		int[] minSubNodesDiscTime = new int[n];
		int[] parent = new int[n];
		Arrays.fill(parent, -1);

		for(int i = 0; i < n; i++){
			if(!visited[i]){
				findBridge(i, visited, discoveryTime, minSubNodesDiscTime, parent);
			}
		}

		return bridges;
	}

	public void findBridge(int u, boolean[] visited, int[] discoveryTime, int[] minSubNodesDiscTime, int[] parent){
		// Mark the current node as visited
		visited[u] = true;
		// Initialize discovery time and low value
		discoveryTime[u] = minSubNodesDiscTime[u] = ++time;
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
				findBridge(v, visited, discoveryTime, minSubNodesDiscTime, parent);
				// Check if the subtree rooted with v has a
				// connection to one of the ancestors of u
				minSubNodesDiscTime[u] = Math.min(minSubNodesDiscTime[u], minSubNodesDiscTime[v]);
				// If the lowest vertex reachable from subtree
				// under v is below u in DFS tree, then u-v is
				// a bridge
				if(minSubNodesDiscTime[v] > discoveryTime[u]){
					bridges.add(Arrays.asList(u, v));
				}
			// Update low value of u for parent function calls.
			// v != parent[u] to avoid infinite loop for a undirected graph
			}else if(v != parent[u]){
				minSubNodesDiscTime[u] = Math.min(minSubNodesDiscTime[u], discoveryTime[v]);
			}
		}
	}
}
