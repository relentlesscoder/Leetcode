package org.wshuai.leetcode;

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
}
