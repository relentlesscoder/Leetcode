package org.wshuai.leetcode;

/**
 * Created by Wei on 11/14/2019.
 * #0685 https://leetcode.com/problems/redundant-connection-ii/
 */
public class RedundantConnectionII {
	// https://leetcode.com/problems/redundant-connection-ii/discuss/278105/topic
	public int[] findRedundantDirectedConnection(int[][] edges) {
		int n = edges.length;
		boolean cycleDetected = false;
		int[] root = new int[n + 1], parent = new int[n + 1];
		int[] edge1 = null, edge2 = null;
		for(int i = 1; i <= n; i++){
			root[i] = i;
		}
		for(int[] edge : edges){
			int u = edge[0], v = edge[1];
			if(parent[v] > 0){
				// case 2 & case 3 will find one node has in-degree two
				// for case 3, we can skip any of the two edge
				// for case 2, if we skip the edge out of the cycle we
				// will still see cycle again in future so we need to remove edge1
				edge1 = new int[]{parent[v], v};
				edge2 = edge;
			}else{
				parent[v] = u;
				int ru = findRoot(u, root), rv = findRoot(v, root);
				if(ru != rv){
					root[rv] = ru;
				}else{
					cycleDetected = true;
					edge2 = edge;
				}
			}
		}
		if(edge1 != null && edge2 != null){
			return cycleDetected ? edge1 : edge2;
		}
		// for case 1, we always remove edge2
		return edge2;
	}

	private int findRoot(int r, int[] root){
		if(root[r] != r){
			root[r] = findRoot(root[r], root);
		}
		return root[r];
	}
}
