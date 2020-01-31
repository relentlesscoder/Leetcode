package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 03/20/2017.
 * #0310 https://leetcode.com/problems/minimum-height-trees/
 */
public class MinimumHeightTrees {

	// time O(V + E), space O(V + E)
	// https://discuss.leetcode.com/topic/30572/share-some-thoughts/2
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		List<Integer> res = new ArrayList<>();
		if(n == 0){
			return res;
		}
		// only one vertex and no edges
		if(edges == null || edges.length == 0){
			res.add(0);
			return res;
		}
		Set<Integer>[] adj = new HashSet[n];
		for(int i = 0; i < n; i++){
			adj[i] = new HashSet<>();
		}
		for(int[] e : edges){
			adj[e[0]].add(e[1]);
			adj[e[1]].add(e[0]);
		}
		List<Integer> leaves = new ArrayList<>();
		for(int i = 0; i < n; i++){
			if(adj[i].size() == 1){
				leaves.add(i);
			}
		}
		while(n > 2){
			n -= leaves.size();
			List<Integer> newLeaves = new ArrayList<>();
			for(int cur : leaves){
				int next = adj[cur].iterator().next();
				adj[next].remove(cur);
				if(adj[next].size() == 1){
					newLeaves.add(next);
				}
			}
			leaves = newLeaves;
		}
		return leaves;
	}
}
