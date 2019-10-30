package org.wshuai.leetcode;

/**
 * Created by Wei on 10/24/2019.
 * #684 https://leetcode.com/problems/redundant-connection/
 */
public class RedundantConnection {
	private int[] roots;

	// union find
	public int[] findRedundantConnection(int[][] edges) {
		int N = edges.length;
		roots = new int[N];
		for(int i = 0; i < N; i++){
			roots[i] = i;
		}
		for(int[] edge: edges){
			if(!union(edge[0] - 1, edge[1] - 1)){
				return edge;
			}
		}
		return new int[0];
	}

	private boolean union(int u, int v){
		int r1 = find(u);
		int r2 = find(v);
		if(r1 == r2){
			return false;
		}
		roots[r2] = r1;
		return true;
	}

	private int find(int n){
		if(roots[n] != n){
			// update root to boost future search
			roots[n] = find(roots[n]);
		}
		return roots[n];
	}
}
