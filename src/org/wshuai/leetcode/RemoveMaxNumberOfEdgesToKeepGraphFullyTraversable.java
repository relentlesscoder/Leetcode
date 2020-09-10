package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/09/2020.
 * #1579 https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/
 */
public class RemoveMaxNumberOfEdgesToKeepGraphFullyTraversable {

	public int maxNumEdgesToRemove(int n, int[][] edges) {
		int edgeAdded = 0;
		DisjointSet alice = new DisjointSet(n);
		DisjointSet bob = new DisjointSet(n);
		Arrays.sort(edges, (a, b) -> b[0] - a[0]);
		for(int[] e : edges){
			int u = e[1] - 1, v = e[2] - 1;
			if(e[0] == 3){
				if(alice.union(u, v) | bob.union(u, v)){
					edgeAdded++;
				}
			}else if(e[0] == 2){
				if(bob.union(u, v)){
					edgeAdded++;
				}
			}else{
				if(alice.union(u, v)){
					edgeAdded++;
				}
			}
		}
		return (alice.connected() && bob.connected()) ? edges.length - edgeAdded : -1;
	}

	private class DisjointSet{

		private int[] root;
		private int disconnectedComponents;

		public DisjointSet(int n){
			root = new int[n];
			for(int i = 0; i < n; i++){
				root[i] = i;
			}
			disconnectedComponents = n;
		}

		private int find(int r){
			if(root[r] != r){
				root[r] = find(root[r]);
			}
			return root[r];
		}

		private boolean union(int a, int b){
			int ra = find(a);
			int rb = find(b);
			if(ra == rb){
				return false;
			}
			root[rb] = ra;
			disconnectedComponents--;
			return true;
		}

		private boolean connected(){
			return disconnectedComponents == 1;
		}
	}
}
