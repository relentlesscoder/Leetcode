package org.wshuai.leetcode;

/**
 * Created by Wei on 09/24/2016.
 * #0323 https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
 */
public class NumberOfConnectedComponentsInAnUndirectedGraph {
	// time O(V + E*log(V)), union find
	// see DFS & BFS at https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/discuss/77578/Java-concise-DFS
	public int countComponents(int n, int[][] edges) {
		int[] root = new int[n];
		for(int i = 0; i < n; i++){
			root[i] = i;
		}
		int res = n;
		for(int[] e : edges){
			int r1 = findRoot(e[0], root);
			int r2 = findRoot(e[1], root);
			if(r1 == r2){
				continue;
			}
			root[r1] = r2;
			res--;
		}
		return res;
	}

	private int findRoot(int i, int[] root){
		if(i != root[i]){
			root[i] = findRoot(root[i], root);
		}
		return root[i];
	}
}
