package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 12/18/2019.
 * #0834 https://leetcode.com/problems/sum-of-distances-in-tree/
 */
public class SumOfDistancesInTree {

	// time O(n), space O(n)
	// https://leetcode.com/problems/sum-of-distances-in-tree/discuss/130567/Two-traversals-O(N)-python-solution-with-Explanation
	public int[] sumOfDistancesInTree(int N, int[][] edges) {
		int[] dist = new int[N], subtree = new int[N];
		LinkedList<Integer>[] adj = new LinkedList[N];
		for(int i = 0; i < N; i++){
			adj[i] = new LinkedList<>();
		}
		for(int[] e : edges){
			adj[e[0]].offerLast(e[1]);
			adj[e[1]].offerLast(e[0]);
		}
		dfs1(-1, 0, adj, dist, subtree);
		dfs2(0, 0, N, adj, dist, subtree);
		return dist;
	}

	private int[] dfs1(int prev, int cur, LinkedList<Integer>[] adj, int[] dist, int[] subtree){
		// sum denotes the sum of the subtree rooted at cur
		// nodes denotes the count of nodes of the subtree rooted at cur
		int sum = 0, nodes = 0;
		for(int next : adj[cur]){
			if(next == prev){
				continue;
			}
			int[] res = dfs1(cur, next, adj, dist, subtree);
			// since each nodes from child to the current adds one edge to reach the current
			sum += res[0] + res[1];
			nodes += res[1];
		}
		dist[cur] = sum;
		subtree[cur] = nodes + 1;
		return new int[]{dist[cur], subtree[cur]};
	}

	private void dfs2(int prev, int cur, int N, LinkedList<Integer>[] adj, int[] dist, int[] subtree){
		// nodes at 0 already set
		if(cur != 0){
			dist[cur] = dist[prev] + N - (subtree[cur] << 1);
		}
		for(int next : adj[cur]){
			if(next == prev){
				continue;
			}
			dfs2(cur, next, N, adj, dist, subtree);
		}
	}
}
