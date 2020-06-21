package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/27/2019.
 * #0802 https://leetcode.com/problems/find-eventual-safe-states/
 */
public class FindEventualSafeStates {

	// time O(V+E)
	// https://www.geeksforgeeks.org/detect-cycle-direct-graph-using-colors/
	public List<Integer> eventualSafeNodes(int[][] graph) {
		List<Integer> res = new ArrayList<>();
		int n = graph.length;
		// 0 - not visited yet
		// 1 - visited but not yet finished
		// 2 - visited
		int[] colors = new int[n];
		for(int i = 0; i < n; i++){
			if(acyclic(i, graph, colors)){
				res.add(i);
			}
		}
		return res;
	}

	private boolean acyclic(int i, int[][] graph, int[] colors){
		if(colors[i] != 0){
			// if we see visited nodes more than once
			// we've found a back edge
			return colors[i] == 2;
		}
		colors[i] = 1;
		for(int j : graph[i]){
			if(!acyclic(j, graph, colors)){
				return false;
			}
		}
		colors[i] = 2;
		return true;
	}

	// time O(V+E), space O(V+E)
	// Kahn’s algorithm
	public List<Integer> eventualSafeNodesTopologicalSort(int[][] graph) {
		TreeSet<Integer> temp = new TreeSet<>();
		int n = graph.length;
		int[] out = new int[n];
		LinkedList<Integer> queue = new LinkedList<>();
		Map<Integer, List<Integer>> adj = new HashMap<>();
		for(int i = 0; i < n; i++){
			for(int j : graph[i]){
				adj.putIfAbsent(j, new ArrayList<>());
				adj.get(j).add(i);
			}
			if(graph[i].length == 0){
				queue.offerLast(i);
			}
			out[i] = graph[i].length;
		}
		while(!queue.isEmpty()){
			int cur = queue.pollFirst();
			temp.add(cur);
			if(!adj.containsKey(cur)){
				continue;
			}
			for(int next : adj.get(cur)){
				// if out degree is 0, add it to queue
				if(--out[next] == 0){
					queue.offerLast(next);
				}
			}
		}
		return new ArrayList<>(temp);
	}
}
